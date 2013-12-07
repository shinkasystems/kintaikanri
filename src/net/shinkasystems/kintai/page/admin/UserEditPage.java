package net.shinkasystems.kintai.page.admin;

import java.util.ArrayList;
import java.util.List;

import net.shinkasystems.kintai.KintaiDB;
import net.shinkasystems.kintai.KintaiRole;
import net.shinkasystems.kintai.KintaiSession;
import net.shinkasystems.kintai.component.PasswordConfirmValidator;
import net.shinkasystems.kintai.component.RoleChoiceRenderer;
import net.shinkasystems.kintai.component.RoleOption;
import net.shinkasystems.kintai.component.UserChoiceRenderer;
import net.shinkasystems.kintai.component.UserOption;
import net.shinkasystems.kintai.component.UserOptionUtility;
import net.shinkasystems.kintai.entity.ApplicationDao;
import net.shinkasystems.kintai.entity.ApplicationDaoImpl;
import net.shinkasystems.kintai.entity.User;
import net.shinkasystems.kintai.entity.UserDao;
import net.shinkasystems.kintai.entity.UserDaoImpl;
import net.shinkasystems.kintai.panel.AlertPanel;
import net.shinkasystems.kintai.util.Authentication;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.IRequestParameters;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.util.value.ValueMap;
import org.seasar.doma.jdbc.tx.LocalTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Aogiri
 *
 */
@AuthorizeInstantiation({ KintaiRole.ADMIN })
public class UserEditPage extends AdminLayoutPage {

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
	};

	/**
	 * ユーザーIDの入力フィールドです。
	 */
	private final TextField<Integer> userIdTextField = new TextField<Integer>("user-id", new Model<Integer>());

	/**
	 * ユーザー名のラベルです。
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
	 * 更新ボタンです。
	 */
	private final Button updateButton = new Button("update-button") {

		@Override
		public void onSubmit() {

			LocalTransaction transaction = KintaiDB.getLocalTransaction();
			try {
				transaction.begin();

				final UserDao dao = new UserDaoImpl();

				final User user = dao.selectById(userIdTextField.getModelObject());

				if (passwordTextField.getModelObject() != null
						&& passwordTextField.getModelObject().isEmpty() == false) {
					user.setPassword(new Authentication(user.getUserName(), passwordTextField.getModelObject())
							.getPasswordHash());
				}
				if (displayNameTextField.getModelObject() != null
						&& displayNameTextField.getModelObject().isEmpty() == false) {
					user.setDisplayName(displayNameTextField.getModelObject());
				}
				if (authorityDropDownChoice.getModelObject() != null) {
					user.setAuthorityId(authorityDropDownChoice.getModelObject().getId());
				}
				if (roleChoice.getModelObject() != null) {
					user.setRole(roleChoice.getModelObject().getId());
				}

				dao.update(user);

				transaction.commit();

				log.info("ユーザーを編集しました。");

			} finally {
				transaction.rollback();
			}
		}

	};

	/**
	 * 有効化ボタンです。
	 */
	private final Button activateButton = new Button("activate-button") {

		@Override
		public void onSubmit() {

			LocalTransaction transaction = KintaiDB.getLocalTransaction();
			try {
				transaction.begin();

				final UserDao dao = new UserDaoImpl();

				final User user = dao.selectById(userIdTextField.getModelObject());

				user.setActivated(true);

				dao.update(user);

				transaction.commit();

				log.info("ユーザーを有効化しました。");

			} finally {
				transaction.rollback();
			}
		}

	};

	/**
	 * 無効化ボタンです。
	 */
	private final Button invalidateButton = new Button("invalidate-button") {

		@Override
		public void onSubmit() {

			LocalTransaction transaction = KintaiDB.getLocalTransaction();
			try {
				transaction.begin();

				final UserDao dao = new UserDaoImpl();

				final User user = dao.selectById(userIdTextField.getModelObject());

				user.setActivated(false);

				dao.update(user);

				transaction.commit();

				log.info("ユーザーを無効化しました。");

			} finally {
				transaction.rollback();
			}
		}

	};

	/**
	 * 削除ボタンです。
	 */
	private final Button deleteButton = new Button("delete-button") {

		@Override
		public void onSubmit() {

			LocalTransaction transaction = KintaiDB.getLocalTransaction();
			try {
				transaction.begin();

				final UserDao dao = new UserDaoImpl();

				final User user = dao.selectById(userIdTextField.getModelObject());

				dao.delete(user);

				transaction.commit();

				log.info("ユーザーを削除しました。");

			} finally {
				transaction.rollback();
			}
		}

	};

	/**
	 * コンストラクタです。
	 */
	public UserEditPage() {

		/*
		 * ログインユーザー情報の取得
		 */
		final User loginUser = ((KintaiSession) KintaiSession.get()).getUser();
		
		/*
		 * 申請者情報の取得
		 */
		IRequestParameters parameters = RequestCycle.get().getRequest().getQueryParameters();

		final int id = parameters.getParameterValue(UsersPage.PARAMETER_ID).toInt();

		final User user = getUser(id);

		/*
		 * コンポーネントの生成
		 */
		authorityOptions.addAll(UserOptionUtility.getUserOptions());

		UserOption currentAuthority = null;
		for (UserOption userOption : authorityOptions) {
			if (userOption.getId() == user.getAuthorityId()) {
				currentAuthority = userOption;
			}
		}

		roleOptions.add(new RoleOption(KintaiRole.ADMIN, "管理"));
		roleOptions.add(new RoleOption(KintaiRole.USER, "一般"));

		RoleOption currentRole = null;
		for (RoleOption roleOption : roleOptions) {
			if (roleOption.getId().equals(user.getRole())) {
				currentRole = roleOption;
			}
		}

		/*
		 * コンポーネントの編集
		 */
		alertPanel.setVisible(false);

		userIdTextField.setDefaultModelObject(user.getId());
		userIdTextField.setEnabled(false);
		userNameTextField.setDefaultModelObject(user.getUserName());
		userNameTextField.setEnabled(false);
		passwordTextField.setRequired(false);
		passwordConfirmTextField.setRequired(false);
		displayNameTextField.setDefaultModelObject(user.getDisplayName());
		authorityDropDownChoice.setDefaultModelObject(currentAuthority);
		roleChoice.setDefaultModelObject(currentRole);
		roleChoice.setSuffix("&nbsp;");
		roleChoice.setEnabled(user.getId() != loginUser.getId());

		activateButton.setVisible(!user.getActivated());
		invalidateButton.setVisible(user.getActivated() && user.getId() != loginUser.getId());
		deleteButton.setVisible(!isHistoryExists(user.getId()) && user.getId() != loginUser.getId());

		/*
		 * コンポーネントの組立
		 */
		add(alertPanel);

		userProfileForm.add(userIdTextField);
		userProfileForm.add(userNameTextField);
		userProfileForm.add(passwordTextField);
		userProfileForm.add(passwordConfirmTextField);
		userProfileForm.add(displayNameTextField);
		userProfileForm.add(authorityDropDownChoice);
		userProfileForm.add(roleChoice);
		userProfileForm.add(updateButton);
		userProfileForm.add(activateButton);
		userProfileForm.add(invalidateButton);
		userProfileForm.add(deleteButton);
		userProfileForm.add(new PasswordConfirmValidator(passwordTextField, passwordConfirmTextField));

		add(userProfileForm);

	}

	/**
	 * 指定IDのユーザーを取得します。
	 * 
	 * @param id
	 * @return
	 */
	private static User getUser(int id) {

		LocalTransaction transaction = KintaiDB.getLocalTransaction();
		try {
			transaction.begin();

			final UserDao dao = new UserDaoImpl();

			return dao.selectById(id);

		} finally {
			transaction.rollback();
		}
	}

	private static boolean isHistoryExists(int id) {

		LocalTransaction transaction = KintaiDB.getLocalTransaction();
		try {
			transaction.begin();

			final ApplicationDao dao = new ApplicationDaoImpl();

			return dao.selectHistoryExists(id);

		} finally {
			transaction.rollback();
		}
	}
}
