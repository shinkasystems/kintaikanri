package net.shinkasystems.kintai.page;

import net.shinkasystems.kintai.KintaiRole;
import net.shinkasystems.kintai.KintaiSession;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;

/**
 * 
 * @author Aogiri
 *
 */
public class DefaultLayoutPage extends WebPage {

	public DefaultLayoutPage() {

		KintaiSession session = (KintaiSession) KintaiSession.get();

		final Label userNameLabel = new Label("user-name", new Model<String>());
		final Label adminLinkLabel = new Label("admin-link-label", "管理");
		
		if (session.isSignedIn()) {
			userNameLabel.setDefaultModelObject(session.getUser().getUserName());
		}
		userNameLabel.setVisible(session.isSignedIn());
		
		adminLinkLabel.setVisible(session.isSignedIn() && session.getUser().getRole().equals(KintaiRole.ADMIN));

		add(userNameLabel);
		add(adminLinkLabel);
	}

}
