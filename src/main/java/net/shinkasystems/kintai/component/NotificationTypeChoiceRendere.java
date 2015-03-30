package net.shinkasystems.kintai.component;

import net.shinkasystems.kintai.domain.NotificationType;

import org.apache.wicket.markup.html.form.IChoiceRenderer;

/**
 * 
 * @author Aogiri
 *
 */
public class NotificationTypeChoiceRendere implements IChoiceRenderer<NotificationType> {

	@Override
	public Object getDisplayValue(NotificationType type) {
		return type.display;
	}

	@Override
	public String getIdValue(NotificationType type, int index) {
		return type.name();
	}

}