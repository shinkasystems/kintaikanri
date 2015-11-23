package net.shinkasystems.kintai.page;

import net.shinkasystems.kintai.KintaiRole;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@AuthorizeInstantiation({ KintaiRole.ADMIN, KintaiRole.USER, KintaiRole.EXPIRED_USER })
public class SupportPage extends DefaultLayoutPage {

	/** ロガー */
	private static final Logger log = LoggerFactory.getLogger(SignInPage.class);

	public SupportPage() {
		super();

	}

}
