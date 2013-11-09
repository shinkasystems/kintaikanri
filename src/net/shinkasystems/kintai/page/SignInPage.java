package net.shinkasystems.kintai.page;

import net.shinkasystems.kintai.KintaiSession;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.AbstractFormValidator;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.value.ValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Aogiri
 * 
 */
public class SignInPage extends DefaultLayoutPage {

	/** ロガー */
	private static final Logger log = LoggerFactory.getLogger(SignInPage.class);

	/**
	 * サインイン用のフォームです。
	 */
	private final Form<ValueMap> signInForm = new Form<ValueMap>("signin-form") {

		@Override
		protected void onSubmit() {

			log.info("ユーザーがログインしました。ユーザー名："
					+ ((KintaiSession) KintaiSession.get()).getUser()
							.getUserName() + " ロール："
					+ KintaiSession.get().getRoles());

			continueToOriginalDestination();
			setResponsePage(IndexPage.class);
		}

	};

	/**
	 * ユーザー名を入力するテキストフィールドです。
	 */
	private final TextField<String> usernameTextField = new TextField<String>(
			"user-name", new Model<String>());

	/**
	 * パスワードを入力するテキストフィールドです。
	 */
	private final PasswordTextField passwordTextField = new PasswordTextField(
			"plane-password", new Model<String>());

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
