package net.shinkasystems.kintai.panel;

import org.apache.wicket.feedback.ErrorLevelFeedbackMessageFilter;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * successレベルの情報を表示するパネルです。
 * 
 * @author Aogiri
 *
 */
public class InfomationPanel extends Panel {

	/**
	 * コンストラクタです。
	 * @param id コンポーネントのwicket:id
	 */
	public InfomationPanel(String id) {
		super(id);

		final FeedbackPanel feedbackPanel = new FeedbackPanel(
				"info-feedback",
				new ErrorLevelFeedbackMessageFilter(FeedbackMessage.INFO));

		add(feedbackPanel);
	}

}
