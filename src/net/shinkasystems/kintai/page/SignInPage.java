package net.shinkasystems.kintai.page;

import net.shinkasystems.kintai.KintaiSession;
import net.shinkasystems.kintai.page.layout.DefaultLayoutPage;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.AbstractFormValidator;

/**
 * 
 * @author Aogiri
 * 
 */
public class SignInPage extends DefaultLayoutPage {

	/**
	 * サインイン用のフォームです。
	 */
	private final Form signInForm = new Form("signin-form") {

		@Override
		protected void onSubmit() {
			continueToOriginalDestination();
			setResponsePage(IndexPage.class);
		}

	};

	/**
	 * ユーザー名を入力するテキストフィールドです。
	 */
	private final TextField<String> usernameTextField = new TextField<String>(
			"user-name");

	/**
	 * パスワードを入力するテキストフィールドです。
	 */
	private final PasswordTextField passwordTextField = new PasswordTextField(
			"plane-password");

	/**
	 * コンストラクタです。
	 */
	public SignInPage() {
		super();

		/*
		 * コンポーネントの編集
		 */
		usernameTextField.setRequired(true);
		passwordTextField.setRequired(true);

		signInForm.add(new AbstractFormValidator() {

			@Override
			public void validate(Form<?> form) {
				if (!KintaiSession.get().signIn(usernameTextField.getInput(),
						passwordTextField.getInput())) {
					error(passwordTextField);
				}
			}

			@Override
			public FormComponent<?>[] getDependentFormComponents() {
				return new FormComponent<?>[] { usernameTextField,
						passwordTextField };
			}
		});

		/*
		 * コンポーネントの組立
		 */
		signInForm.add(usernameTextField);
		signInForm.add(passwordTextField);
		add(signInForm);
	}

}
