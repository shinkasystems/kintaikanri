package net.shinkasystems.kintai.panel;

import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;

public class AlertPanel extends Panel {

	public AlertPanel(String id) {
		super(id);

		final FeedbackPanel feedbackPanel = new FeedbackPanel("alert-feedback");

		add(feedbackPanel);
	}

}
