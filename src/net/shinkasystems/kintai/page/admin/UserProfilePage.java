package net.shinkasystems.kintai.page.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.shinkasystems.kintai.KintaiRole;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.value.ValueMap;

/**
 * 
 * @author Aogiri
 * 
 */
@AuthorizeInstantiation({ KintaiRole.ADMIN })
public class UserProfilePage extends AdminLayoutPage {

	/**
	 * 
	 */
	private final Form<ValueMap> userProfileForm = new Form<ValueMap>(
			"user-profile-form") {

		@Override
		protected void onSubmit() {

		}

	};

	/**
	 * 
	 */
	private final TextField<String> userNameTextField = new TextField<String>(
			"user-name");

	/**
	 * 
	 */
	private final PasswordTextField passwordTextField = new PasswordTextField(
			"password");

	/**
	 * 
	 */
	private final PasswordTextField passwordConfirmTextField = new PasswordTextField(
			"password-confirm");

	/**
	 * 
	 */
	private final TextField<String> displayNameTextField = new TextField<String>(
			"display-name");

	/**
	 * 
	 */
	private final DropDownChoice<?> authorityChoice = new DropDownChoice<>(
			"authority");

	/**
	 * 
	 */
	private final List<RoleOption> roleOptions = new ArrayList<RoleOption>();

	/**
	 * 
	 */
	private final DropDownChoice<?> roleChoice = new DropDownChoice<>("role",
			new Model<RoleOption>(), roleOptions, new RoleChoiceRenderer());

	/**
	 * 
	 */
	private final List<ActivatedOption> activatedOptions = new ArrayList<ActivatedOption>();

	/**
	 * 
	 */
	private final DropDownChoice<ActivatedOption> activatedChoice = new DropDownChoice<ActivatedOption>(
			"activated", new Model<ActivatedOption>(), activatedOptions,
			new AcitivatedChoiceRenderer());

	/**
	 * 
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
		authorityChoice.setRequired(true);
		roleChoice.setRequired(true);
		activatedChoice.setRequired(true);

		/*
		 * コンポーネントの組立
		 */
		userProfileForm.add(userNameTextField);
		userProfileForm.add(passwordTextField);
		userProfileForm.add(passwordConfirmTextField);
		userProfileForm.add(displayNameTextField);
		userProfileForm.add(authorityChoice);
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
