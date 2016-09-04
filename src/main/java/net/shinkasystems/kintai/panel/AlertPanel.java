package net.shinkasystems.kintai.panel;

import org.apache.wicket.feedback.ErrorLevelFeedbackMessageFilter;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * errrorレベルの情報を表示するパネルです。
 * 
 * @author Aogiri
 *
 */
public class AlertPanel extends Panel {

	/**
	 * コンストラクタです。
	 * @param id コンポーネントのwicket:id
	 */
	public AlertPanel(String id) {
		super(id);

		final FeedbackPanel feedbackPanel = new FeedbackPanel(
				"alert-feedback",
				new ErrorLevelFeedbackMessageFilter(FeedbackMessage.ERROR));

		add(feedbackPanel);
	}

}
