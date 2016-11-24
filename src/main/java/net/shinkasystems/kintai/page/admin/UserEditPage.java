package net.shinkasystems.kintai.page.admin;

import java.util.ArrayList;
import java.util.List;

import net.shinkasystems.kintai.KintaiRole;
import net.shinkasystems.kintai.KintaiSession;
import net.shinkasystems.kintai.component.ConfirmSubmitButton;
import net.shinkasystems.kintai.component.DisplayNameDuplicateEditValidator;
import net.shinkasystems.kintai.component.PasswordConfirmValidator;
import net.shinkasystems.kintai.component.RoleChoiceRenderer;
import net.shinkasystems.kintai.component.RoleOption;
import net.shinkasystems.kintai.component.UserChoiceRenderer;
import net.shinkasystems.kintai.component.UserOption;
import net.shinkasystems.kintai.component.UserOptionUtility;
import net.shinkasystems.kintai.entity.User;
import net.shinkasystems.kintai.panel.AlertPanel;
import net.shinkasystems.kintai.panel.InfomationPanel;
import net.shinkasystems.kintai.service.admin.UserEditService;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;
import com.google.inject.Inject;

/**
 * ユーザーの編集を行います。
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
	 * 情報パネルです。
	 */
	private final InfomationPanel infomationPanel = new InfomationPanel("infomation-panel");

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
	 * 更新ボタンです。
	 */
	private final Button updateButton = new ConfirmSubmitButton("update-button", getString("confirm-update-message")) {

		@Override
		public void onSubmit() {

			int userId = userIdTextField.getModelObject();
			String password = null;
			String displayName = null;
			String emailAddress = null;
			int authorityId = 0;
			String role = null;

			if (!Strings.isNullOrEmpty(passwordTextField.getModelObject())) {
				password = passwordTextField.getModelObject();
			}
			if (!Strings.isNullOrEmpty(displayNameTextField.getModelObject())) {
				displayName = displayNameTextField.getModelObject();
			}
			if (!Strings.isNullOrEmpty(emailAddressTextField.getModelObject())) {
				emailAddress = emailAddressTextField.getModelObject();
			}
			if (authorityDropDownChoice.getModelObject() != null) {
				authorityId = authorityDropDownChoice.getModelObject().getId();
			}
			if (!Strings.isNullOrEmpty(roleChoice.getModelObject().getId())) {
				role = roleChoice.getModelObject().getId();
			}

			userEditService.updateUser(userId, password, displayName, emailAddress, authorityId, role);

			info(getString("update-message"));
			infomationPanel.setVisible(true);
			alertPanel.setVisible(false);
		}

		/* (非 Javadoc)
		 * @see org.apache.wicket.markup.html.form.Button#onError()
		 */
		@Override
		public void onError() {

			infomationPanel.setVisible(false);
			alertPanel.setVisible(true);
		}

	};

	/**
	 * 有効化ボタンです。
	 */
	private final Button activateButton = new ConfirmSubmitButton("activate-button",
			getString("confirm-activate-message")) {

		@Override
		public void onSubmit() {

			userEditService.activateUser(userIdTextField.getModelObject());

			info(getString("activate-message"));
			infomationPanel.setVisible(true);
			alertPanel.setVisible(false);

			activateButton.setVisible(false);
			invalidateButton.setVisible(true);
		}

	};

	/**
	 * 無効化ボタンです。
	 */
	private final Button invalidateButton = new ConfirmSubmitButton("invalidate-button",
			getString("confirm-invalidate-message")) {

		@Override
		public void onSubmit() {

			userEditService.disableUser(userIdTextField.getModelObject());

			info(getString("invalidate-message"));
			infomationPanel.setVisible(true);
			alertPanel.setVisible(false);

			activateButton.setVisible(true);
			invalidateButton.setVisible(false);
		}

	};

	/**
	 * 削除ボタンです。
	 */
	private final Button deleteButton = new ConfirmSubmitButton("delete-button", getString("confirm-delete-message")) {

		@Override
		public void onSubmit() {

			userEditService.deleteUser(userIdTextField.getModelObject());

			info(getString("delete-message"));
			infomationPanel.setVisible(true);
			alertPanel.setVisible(false);

			updateButton.setVisible(false);
			activateButton.setVisible(false);
			invalidateButton.setVisible(false);
			deleteButton.setVisible(false);
		}

	};

	@Inject
	private UserEditService userEditService;

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

		final User user = userEditService.getUser(id);

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
		infomationPanel.setVisible(false);

		userIdTextField.setDefaultModelObject(user.getId());
		userIdTextField.setEnabled(false);
		userNameTextField.setDefaultModelObject(user.getUserName());
		userNameTextField.setEnabled(false);
		passwordTextField.setRequired(false);
		passwordConfirmTextField.setRequired(false);
		displayNameTextField.setDefaultModelObject(user.getDisplayName());
		emailAddressTextField.setDefaultModelObject(user.getEmailAddress());
		authorityDropDownChoice.setDefaultModelObject(currentAuthority);
		roleChoice.setDefaultModelObject(currentRole);
		roleChoice.setSuffix("&nbsp;");
		roleChoice.setEnabled(user.getId() != loginUser.getId());

		userProfileForm.add(new DisplayNameDuplicateEditValidator(userIdTextField, displayNameTextField));

		activateButton.setVisible(!user.getActivated());
		invalidateButton.setVisible(user.getActivated() && user.getId() != loginUser.getId());
		deleteButton.setVisible(userEditService.canDelete(user.getId()) && user.getId() != loginUser.getId());

		/*
		 * コンポーネントの組立
		 */
		add(alertPanel);
		add(infomationPanel);

		userProfileForm.add(userIdTextField);
		userProfileForm.add(userNameTextField);
		userProfileForm.add(passwordTextField);
		userProfileForm.add(passwordConfirmTextField);
		userProfileForm.add(displayNameTextField);
		userProfileForm.add(emailAddressTextField);
		userProfileForm.add(authorityDropDownChoice);
		userProfileForm.add(roleChoice);
		userProfileForm.add(updateButton);
		userProfileForm.add(activateButton);
		userProfileForm.add(invalidateButton);
		userProfileForm.add(deleteButton);
		userProfileForm.add(new PasswordConfirmValidator(passwordTextField, passwordConfirmTextField));

		add(userProfileForm);

	}
}
