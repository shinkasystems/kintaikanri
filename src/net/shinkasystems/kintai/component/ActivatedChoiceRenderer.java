package net.shinkasystems.kintai.component;

import org.apache.wicket.markup.html.form.IChoiceRenderer;

/**
 * 
 * @author Aogiri
 * 
 */
public class ActivatedChoiceRenderer implements IChoiceRenderer<ActivatedOption> {

	@Override
	public Object getDisplayValue(ActivatedOption activatedOption) {
		return activatedOption.getDisplay();
	}

	@Override
	public String getIdValue(ActivatedOption activatedOption, int index) {
		return Boolean.toString(activatedOption.isActivated());
	}

}