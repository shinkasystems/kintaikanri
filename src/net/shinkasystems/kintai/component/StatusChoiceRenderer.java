package net.shinkasystems.kintai.component;

import net.shinkasystems.kintai.KintaiStatus;

import org.apache.wicket.markup.html.form.IChoiceRenderer;

/**
 * ステータス選択コンポーネントのレンダラーです。
 * 
 * @author Aogiri
 *
 */
public class StatusChoiceRenderer implements IChoiceRenderer<KintaiStatus> {

	@Override
	public Object getDisplayValue(KintaiStatus status) {
		return status.display;
	}

	@Override
	public String getIdValue(KintaiStatus status, int index) {
		return status.name();
	}

}