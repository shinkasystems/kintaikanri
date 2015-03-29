package net.shinkasystems.kintai.component;

import net.shinkasystems.kintai.domain.KintaiType;

import org.apache.wicket.markup.html.form.IChoiceRenderer;

/**
 * 
 * @author Aogiri
 *
 */
public class KintaiTypeChoiceRendere implements IChoiceRenderer<KintaiType> {

	@Override
	public Object getDisplayValue(KintaiType type) {
		return type.display;
	}

	@Override
	public String getIdValue(KintaiType type, int index) {
		return type.name();
	}

}