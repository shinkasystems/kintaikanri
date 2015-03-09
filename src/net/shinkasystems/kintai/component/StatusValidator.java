package net.shinkasystems.kintai.component;

import net.shinkasystems.kintai.KintaiStatus;
import net.shinkasystems.kintai.entity.Application;
import net.shinkasystems.kintai.service.kintai.DetailService;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.validation.AbstractFormValidator;

import com.google.inject.Inject;

/**
 * 申請情報のステータスが表示時から変更されていないかチェックします。
 * 
 * @author Aogiri
 *
 */
public class StatusValidator extends AbstractFormValidator {

	private final FormComponent<Integer> idComponent;

	private final FormComponent<KintaiStatus> statusComponent;

	private final DetailService detailService;

	@Inject
	public StatusValidator(
			FormComponent<Integer> idComponent,
			FormComponent<KintaiStatus> statusComponent,
			DetailService detailService) {
		
		super();
		this.idComponent = idComponent;
		this.statusComponent = statusComponent;
		this.detailService = detailService;
	}

	@Override
	public FormComponent<?>[] getDependentFormComponents() {
		return new FormComponent<?>[] { idComponent, statusComponent };
	}

	@Override
	public void validate(Form<?> form) {

		final int id = idComponent.getModelObject();
		final KintaiStatus status = statusComponent.getModelObject();

		Application current = detailService.getApplication(id);

		if (status != KintaiStatus.valueOf(current.getStatus())) {
			error(null);
		}
	}
}