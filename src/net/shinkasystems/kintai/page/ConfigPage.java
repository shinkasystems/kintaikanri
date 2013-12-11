package net.shinkasystems.kintai.page;

import java.sql.Date;
import java.util.Calendar;

import net.shinkasystems.kintai.KintaiDB;
import net.shinkasystems.kintai.KintaiRole;
import net.shinkasystems.kintai.KintaiSession;
import net.shinkasystems.kintai.component.PasswordConfirmValidator;
import net.shinkasystems.kintai.component.PasswordDuplicateValidator;
import net.shinkasystems.kintai.entity.User;
import net.shinkasystems.kintai.entity.UserDao;
import net.shinkasystems.kintai.entity.UserDaoImpl;
import net.shinkasystems.kintai.panel.AlertPanel;
import net.shinkasystems.kintai.util.Authentication;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.value.ValueMap;
import org.apache.wicket.validation.validator.PatternValidator;
import org.apache.wicket.validation.validator.StringValidator;
import org.seasar.doma.jdbc.tx.LocalTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
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
	 * 設定フォームです。
	 */
	private final Form<ValueMap> configForm = new Form<ValueMap>("config-form") {

		@Override
		protected void onSubmit() {

			alertPanel.setVisible(false);

			Calendar expireCalendar = Calendar.getInstance();
			expireCalendar.add(Calendar.MONTH, 3);

			final int userID = ((KintaiSession) KintaiSession.get()).getUser().getId();
			final String password = passwordTextField.getModelObject();

			LocalTransaction transaction = KintaiDB.getLocalTransaction();
			try {
				transaction.begin();

				final UserDao dao = new UserDaoImpl();

				final User user = dao.selectById(userID);

				user.setPassword(new Authentication(user.getUserName(), password).getPasswordHash());
				user.setExpireDate(new Date(expireCalendar.getTimeInMillis()));

				dao.update(user);

				transaction.commit();

				log.info("ユーザー設定を変更しました。" + user);

			} finally {
				transaction.rollback();
			}
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
	 * コンストラクタです。
	 */
	public ConfigPage() {
		super();

		/*
		 * コンポーネントの編集
		 */
		alertPanel.setVisible(false);

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

		add(alertPanel);
		add(configForm);
	}

}
