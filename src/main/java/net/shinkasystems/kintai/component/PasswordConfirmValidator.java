package net.shinkasystems.kintai.component;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.validation.AbstractFormValidator;

/**
 * パスワードの同値チェックを行うバリデータです。
 * 
 * @author Aogiri
 *
 */
public class PasswordConfirmValidator extends AbstractFormValidator {
	
	private final FormComponent<?> component1;
	
	private final FormComponent<?> component2;

	public PasswordConfirmValidator(FormComponent<?> component1, FormComponent<?> component2) {
		super();
		this.component1 = component1;
		this.component2 = component2;
	}

	@Override
	public FormComponent<?>[] getDependentFormComponents() {
		return new FormComponent<?>[] {component1, component2};
	}

	@Override
	public void validate(Form<?> form) {
		
		if (component1.getInput().equals(component2.getInput()) == false) {
			error(component1);
		}
	}

}
