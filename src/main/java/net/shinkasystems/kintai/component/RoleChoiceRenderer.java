package net.shinkasystems.kintai.component;

import org.apache.wicket.markup.html.form.IChoiceRenderer;

/**
 * 
 * @author Aogiri
 * 
 */
public class RoleChoiceRenderer implements IChoiceRenderer<RoleOption> {

	@Override
	public Object getDisplayValue(RoleOption roleOption) {
		return roleOption.getDisplay();
	}

	@Override
	public String getIdValue(RoleOption roleOption, int index) {
		return roleOption.getId();
	}

}