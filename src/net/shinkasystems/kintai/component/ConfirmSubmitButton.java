package net.shinkasystems.kintai.component;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.Button;

/**
 * 押下時に確認ダイアログを表示するボタンです。
 * 
 * @author Aogiri
 *
 */
public class ConfirmSubmitButton extends Button {
	
	private final String message;

	public ConfirmSubmitButton(String id, String message) {
		super(id);
		
		this.message = message;
	}

	@Override
	protected void onComponentTag(ComponentTag tag) {
		super.onComponentTag(tag);

		String confirm = " if (!confirm('" + message + "')) {return false;} ";
		tag.put("onclick", confirm);
	}

}
