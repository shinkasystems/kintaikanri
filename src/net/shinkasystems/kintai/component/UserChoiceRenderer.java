package net.shinkasystems.kintai.component;

import org.apache.wicket.markup.html.form.IChoiceRenderer;

/**
 * 
 * @author Aogiri
 *
 */
public class UserChoiceRenderer implements IChoiceRenderer<UserOption> {

	@Override
	public Object getDisplayValue(UserOption userOption) {
		return userOption.getDisplay();
	}

	@Override
	public String getIdValue(UserOption userOption, int index) {
		return String.valueOf(userOption.getId());
	}

}