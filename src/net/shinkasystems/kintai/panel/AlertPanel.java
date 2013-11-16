package net.shinkasystems.kintai.panel;

import java.io.Serializable;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

public class AlertPanel extends Panel {

	public AlertPanel(String id) {
		super(id);

		final FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		final Button dismiss = new Button("dismiss-button", new Model<String>("&times;"));

		dismiss.add(new AttributeModifier("class", new Model<Serializable>("close")));
		dismiss.add(new AttributeModifier("data-dismiss", new Model<Serializable>("alert")));

		add(dismiss);
		add(feedbackPanel);
	}

}
