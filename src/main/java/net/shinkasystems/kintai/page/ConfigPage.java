package net.shinkasystems.kintai.page;

import net.shinkasystems.kintai.KintaiRole;
import net.shinkasystems.kintai.KintaiSession;
import net.shinkasystems.kintai.component.PasswordConfirmValidator;
import net.shinkasystems.kintai.component.PasswordDuplicateValidator;
import net.shinkasystems.kintai.panel.AlertPanel;
import net.shinkasystems.kintai.panel.InfomationPanel;
import net.shinkasystems.kintai.service.ConfigService;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.value.ValueMap;
import org.apache.wicket.validation.validator.PatternValidator;
import org.apache.wicket.validation.validator.StringValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

/**
 * ユーザーの各種設定ページです。
 * 
 * @author Aogiri
 *
 */
@AuthorizeInstantiation({ KintaiRole.ADMIN, KintaiRole.USER, KintaiRole.EXPIRED_USER })
public class ConfigPage extends DefaultLayoutPage {

	/** ロガー */
	private static final Logger log = LoggerFactory.getLogger(ConfigPage.class);

	/**
	 * アラートパネルです。
	 */
	private final Panel alertPanel = new AlertPanel("alert-panel");

	/**
	 * 情報パネルです。
	 */
	private final InfomationPanel infomationPanel = new InfomationPanel("infomation-panel");

	/**
	 * 設定フォームです。
	 */
	private final Form<ValueMap> configForm = new Form<ValueMap>("config-form") {

		@Override
		protected void onSubmit() {

			alertPanel.setVisible(false);

			final int userId = ((KintaiSession) KintaiSession.get()).getUser().getId();
			final String password = passwordTextField.getModelObject();

			configService.changePassword(userId, password);

			/*
			 * ロール（期限切れ）をリセットするために、 新しいパスワードでサインインしなおす。
			 */
			KintaiSession.get().signIn(((KintaiSession) KintaiSession.get()).getUser().getUserName(), password);

			infomationPanel.setMessage(getString("pasword-changed"));
			infomationPanel.setVisible(true);
		}

		@Override
		protected void onError() {

			alertPanel.setVisible(true);
		}

	};

	/**
	 * パスワードの入力フィールドです。
	 */
	private final PasswordTextField passwordTextField = new PasswordTextField("password", new Model<String>());

	/**
	 * 確認用パスワードの入力フィールドです。
	 */
	private final PasswordTextField confirmTextField = new PasswordTextField("password-confirm", new Model<String>());

	/**
	 * 設定ページのサービスです。
	 */
	@Inject
	private ConfigService configService;

	/**
	 * コンストラクタです。
	 */
	public ConfigPage() {
		super();

		/*
		 * コンポーネントの編集
		 */
		alertPanel.setVisible(false);
		infomationPanel.setVisible(false);

		passwordTextField.setRequired(true);
		passwordTextField.add(StringValidator.minimumLength(8));
		passwordTextField.add(new PatternValidator("^[\\u0020-\\u007E]+$"));
		passwordTextField.add(new PasswordDuplicateValidator(((KintaiSession) KintaiSession.get()).getUser().getId()));
		confirmTextField.setRequired(true);

		configForm.add(new PasswordConfirmValidator(passwordTextField, confirmTextField));

		/*
		 * コンポーネントの組立
		 */
		configForm.add(passwordTextField);
		configForm.add(confirmTextField);

		add(infomationPanel);
		add(alertPanel);
		add(configForm);
	}

}
