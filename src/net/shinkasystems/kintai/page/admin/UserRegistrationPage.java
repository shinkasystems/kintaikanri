package net.shinkasystems.kintai.page.admin;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import net.shinkasystems.kintai.KintaiDB;
import net.shinkasystems.kintai.KintaiRole;
import net.shinkasystems.kintai.component.PasswordConfirmValidator;
import net.shinkasystems.kintai.component.RoleChoiceRenderer;
import net.shinkasystems.kintai.component.RoleOption;
import net.shinkasystems.kintai.component.UserChoiceRenderer;
import net.shinkasystems.kintai.component.UserOption;
import net.shinkasystems.kintai.component.UserOptionUtility;
import net.shinkasystems.kintai.entity.User;
import net.shinkasystems.kintai.entity.UserDao;
import net.shinkasystems.kintai.panel.AlertPanel;
import net.shinkasystems.kintai.util.Authentication;
import net.shinkasystems.kintai.util.DaoFactory;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.value.ValueMap;
import org.seasar.doma.jdbc.tx.TransactionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ユーザーの登録を行います。
 * 
 * @author Aogiri
 * 
 */
@AuthorizeInstantiation({ KintaiRole.ADMIN })
public class UserRegistrationPage extends AdminLayoutPage {

	/** ロガー */
	private static final Logger log = LoggerFactory.getLogger(UserRegistrationPage.class);

	/**
	 * アラートパネルです。
	 */
	private final Panel alertPanel = new AlertPanel("alert-panel");

	/**
	 * ユーザーを登録／編集するフォームです。
	 */
	private final Form<ValueMap> userProfileForm = new Form<ValueMap>("user-profile-form") {

		@Override
		protected void onError() {
			alertPanel.setVisible(true);
		}

		@Override
		protected void onValidate() {
			// TODO 自動生成されたメソッド・スタブ
			super.onValidate();
		}

		@Override
		protected void onSubmit() {

			Calendar expireCalendar = Calendar.getInstance();
			expireCalendar.add(Calendar.MONTH, 3);

			TransactionManager transactionManager = KintaiDB.singleton()
					.getTransactionManager();

			transactionManager.required(() -> {
				final UserDao dao = DaoFactory.createDaoImplements(UserDao.class);

				final User user = new User();
				user.setUserName(userNameTextField.getValue());
				user.setPassword(new Authentication(userNameTextField.getValue(), passwordTextField.getValue())
						.getPasswordHash());
				user.setDisplayName(displayNameTextField.getValue());
				user.setEmailAddress(emailAddressTextField.getValue());

				if (authorityDropDownChoice.getModel().getObject() != null) {
					user.setAuthorityId(authorityDropDownChoice.getModel().getObject().getId());
				} else {
					user.setAuthorityId(null);
				}
				user.setActivated(activatedChoice.getModel().getObject().isActivated());
				user.setExpireDate(new Date(expireCalendar.getTimeInMillis()));
				user.setRole(roleChoice.getModel().getObject().getId());

				dao.insert(user);

				if (user.getAuthorityId() == null) {
					user.setAuthorityId(user.getId());

					dao.update(user);
				}

				log.info("ユーザーを新規登録しました。" + user);

				setResponsePage(UsersPage.class);

			});

		}

	};

	/**
	 * ユーザー名の入力フィールドです。
	 */
	private final TextField<String> userNameTextField = new TextField<String>("user-name", new Model<String>());

	/**
	 * パスワードの入力フィールドです。
	 */
	private final PasswordTextField passwordTextField = new PasswordTextField(
			"password", new Model<String>());

	/**
	 * パスワード（確認）の入力フィールドです。
	 */
	private final PasswordTextField passwordConfirmTextField = new PasswordTextField(
			"password-confirm", new Model<String>());

	/**
	 * ユーザーの表示名の入力フィールドです。
	 */
	private final TextField<String> displayNameTextField = new TextField<String>(
			"display-name", new Model<String>());

	/**
	 * メールアドレスの入力フィールドです。
	 */
	private final TextField<String> emailAddressTextField = new TextField<>("email-address", new Model<String>());

	/**
	 * 決裁者のリストです。
	 */
	private final List<UserOption> authorityOptions = new ArrayList<UserOption>();

	/**
	 * 決裁者のドロップダウンです。
	 */
	private final DropDownChoice<UserOption> authorityDropDownChoice = new DropDownChoice<>("authority",
			new Model<UserOption>(), authorityOptions, new UserChoiceRenderer());

	/**
	 * 権限のリストです。
	 */
	private final List<RoleOption> roleOptions = new ArrayList<RoleOption>();

	/**
	 * 権限のドロップダウンです。
	 */
	private final RadioChoice<RoleOption> roleChoice = new RadioChoice<RoleOption>("role",
			new Model<RoleOption>(), roleOptions, new RoleChoiceRenderer());

	/**
	 * 有効／無効のリストです。
	 */
	private final List<ActivatedOption> activatedOptions = new ArrayList<ActivatedOption>();

	/**
	 * 有効／無効のドロップダウンです。
	 */
	private final RadioChoice<ActivatedOption> activatedChoice = new RadioChoice<ActivatedOption>(
			"activated", new Model<ActivatedOption>(), activatedOptions,
			new AcitivatedChoiceRenderer());

	/**
	 * コンストラクタです。
	 */
	public UserRegistrationPage() {
		super();

		/*
		 * コンポーネントの生成
		 */
		authorityOptions.addAll(UserOptionUtility.getUserOptions());

		roleOptions.add(new RoleOption(KintaiRole.ADMIN, "管理"));
		roleOptions.add(new RoleOption(KintaiRole.USER, "一般"));
		activatedOptions.add(new ActivatedOption(true, "有効"));
		activatedOptions.add(new ActivatedOption(false, "無効"));

		/*
		 * コンポーネントの編集
		 */
		alertPanel.setVisible(false);

		userNameTextField.setRequired(true);
		passwordTextField.setRequired(true);
		passwordConfirmTextField.setRequired(true);
		displayNameTextField.setRequired(true);
		emailAddressTextField.setRequired(true);
		roleChoice.setRequired(true);
		roleChoice.setSuffix("&nbsp;");
		activatedChoice.setRequired(true);
		activatedChoice.setSuffix("&nbsp;");

		userProfileForm.add(new PasswordConfirmValidator(passwordTextField, passwordConfirmTextField));

		/*
		 * コンポーネントの組立
		 */
		add(alertPanel);

		userProfileForm.add(userNameTextField);
		userProfileForm.add(passwordTextField);
		userProfileForm.add(passwordConfirmTextField);
		userProfileForm.add(displayNameTextField);
		userProfileForm.add(emailAddressTextField);
		userProfileForm.add(authorityDropDownChoice);
		userProfileForm.add(roleChoice);
		userProfileForm.add(activatedChoice);
		add(userProfileForm);
	}
}

/**
 * 
 * @author Aogiri
 * 
 */
class AcitivatedChoiceRenderer implements IChoiceRenderer<ActivatedOption> {

	@Override
	public Object getDisplayValue(ActivatedOption activatedOption) {
		return activatedOption.getDisplay();
	}

	@Override
	public String getIdValue(ActivatedOption activatedOption, int index) {
		return Boolean.toString(activatedOption.isActivated());
	}

}

/**
 * 
 * @author Aogiri
 * 
 */
class ActivatedOption implements Serializable {

	private final boolean activated;

	private final String display;

	public ActivatedOption(boolean activated, String display) {
		super();
		this.activated = activated;
		this.display = display;
	}

	public boolean isActivated() {
		return activated;
	}

	public String getDisplay() {
		return display;
	}
}
