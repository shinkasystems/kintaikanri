package net.shinkasystems.kintai.page.admin;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import net.shinkasystems.kintai.KintaiDB;
import net.shinkasystems.kintai.KintaiRole;
import net.shinkasystems.kintai.component.Authority;
import net.shinkasystems.kintai.component.AuthorityAutoCompleteTextField;
import net.shinkasystems.kintai.entity.User;
import net.shinkasystems.kintai.entity.UserDao;
import net.shinkasystems.kintai.entity.UserDaoImpl;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.value.ValueMap;
import org.seasar.doma.jdbc.tx.LocalTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ユーザーの登録／編集を行います。
 * 
 * @author Aogiri
 * 
 */
@AuthorizeInstantiation({ KintaiRole.ADMIN })
public class UserProfilePage extends AdminLayoutPage {

	/** ロガー */
	private static final Logger log = LoggerFactory.getLogger(UserProfilePage.class);

	/**
	 * ユーザーを登録／編集するフォームです。
	 */
	private final Form<ValueMap> userProfileForm = new Form<ValueMap>("user-profile-form") {

		@Override
		protected void onSubmit() {

			Calendar expireCalendar = Calendar.getInstance();
			expireCalendar.add(Calendar.MONTH, 3);

			LocalTransaction transaction = KintaiDB.getLocalTransaction();
			try {
				transaction.begin();

				final UserDao dao = new UserDaoImpl();

				final User user = new User();
				user.setUserName(userNameTextField.getValue());
				user.setPassword(DigestUtils.sha512Hex(passwordTextField.getValue()));
				user.setDisplayName(displayNameTextField.getValue());
				user.setAuthorityId(Integer.valueOf(authorityTextField.getId()));
				user.setActivated(Boolean.valueOf(activatedChoice.getId()));
				user.setExpireDate(new Date(expireCalendar.getTimeInMillis()));
				user.setRole(KintaiRole.ADMIN);

				dao.insert(user);

				if (StringUtils.isEmpty(authorityTextField.getValue())) {
					user.setAuthorityId(user.getId());

					dao.update(user);
				}

				transaction.commit();

				log.info("ユーザーを新規登録しました。" + user);

			} finally {
				transaction.rollback();
			}
		}

	};

	/**
	 * ユーザー名の入力フィールドです。
	 */
	private final TextField<String> userNameTextField = new TextField<String>(
			"user-name");

	/**
	 * パスワードの入力フィールドです。
	 */
	private final PasswordTextField passwordTextField = new PasswordTextField(
			"password");

	/**
	 * パスワード（確認）の入力フィールドです。
	 */
	private final PasswordTextField passwordConfirmTextField = new PasswordTextField(
			"password-confirm");

	/**
	 * ユーザーの表示名の入力フィールドです。
	 */
	private final TextField<String> displayNameTextField = new TextField<String>(
			"display-name");

	/**
	 * 決裁者のドロップダウンです。
	 */
	private final AutoCompleteTextField<Authority> authorityTextField =
			new AuthorityAutoCompleteTextField<>("authority");

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
	public UserProfilePage() {
		super();

		/*
		 * コンポーネントの生成
		 */
		roleOptions.add(new RoleOption(KintaiRole.ADMIN, "管理"));
		roleOptions.add(new RoleOption(KintaiRole.USER, "一般"));
		activatedOptions.add(new ActivatedOption("true", "有効"));
		activatedOptions.add(new ActivatedOption("false", "無効"));

		/*
		 * コンポーネントの編集
		 */
		userNameTextField.setRequired(true);
		passwordTextField.setRequired(true);
		passwordConfirmTextField.setRequired(true);
		displayNameTextField.setRequired(true);
		authorityTextField.setRequired(true);
		roleChoice.setRequired(true);
		roleChoice.setSuffix("&nbsp;");
		activatedChoice.setRequired(true);
		activatedChoice.setSuffix("&nbsp;");

		/*
		 * コンポーネントの組立
		 */
		userProfileForm.add(userNameTextField);
		userProfileForm.add(passwordTextField);
		userProfileForm.add(passwordConfirmTextField);
		userProfileForm.add(displayNameTextField);
		userProfileForm.add(authorityTextField);
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
class RoleChoiceRenderer implements IChoiceRenderer<RoleOption> {

	@Override
	public Object getDisplayValue(RoleOption roleOption) {
		return roleOption.getDisplay();
	}

	@Override
	public String getIdValue(RoleOption roleOption, int index) {
		return roleOption.getId();
	}

}

/**
 * 
 * @author Aogiri
 * 
 */
class RoleOption implements Serializable {

	private final String id;

	private final String display;

	public RoleOption(String id, String display) {
		super();
		this.id = id;
		this.display = display;
	}

	public String getId() {
		return id;
	}

	public String getDisplay() {
		return display;
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
		return activatedOption.getId();
	}

}

/**
 * 
 * @author Aogiri
 * 
 */
class ActivatedOption implements Serializable {

	private final String id;

	private final String display;

	public ActivatedOption(String id, String display) {
		super();
		this.id = id;
		this.display = display;
	}

	public String getId() {
		return id;
	}

	public String getDisplay() {
		return display;
	}
}
