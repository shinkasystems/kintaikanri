package net.shinkasystems.kintai.panel;

import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * 
 * @author Aogiri
 *
 */
public class InfomationPanel extends Panel {
	
	private final IModel<String> messageModel = new Model<String>();
	
	private final MultiLineLabel messsageLabel;

	/**
	 * 
	 * @param id
	 */
	public InfomationPanel(String id) {
		super(id);

		messsageLabel = new MultiLineLabel("info-message", messageModel);

		add(messsageLabel);
	}

	public void setMessage(String message) {
		messageModel.setObject(message);
	}

}
