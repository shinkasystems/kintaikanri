package net.shinkasystems.kintai.component;

import net.shinkasystems.kintai.domain.NotificationStatus;

import org.apache.wicket.markup.html.form.IChoiceRenderer;

/**
 * ステータス選択コンポーネントのレンダラーです。
 * 
 * @author Aogiri
 *
 */
public class StatusChoiceRenderer implements IChoiceRenderer<NotificationStatus> {

	@Override
	public Object getDisplayValue(NotificationStatus status) {
		return status.display;
	}

	@Override
	public String getIdValue(NotificationStatus status, int index) {
		return status.name();
	}

}