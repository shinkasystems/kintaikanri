package net.shinkasystems.kintai;

import net.shinkasystems.kintai.page.AdminPage;
import net.shinkasystems.kintai.page.ApplyPage;
import net.shinkasystems.kintai.page.ConfigPage;
import net.shinkasystems.kintai.page.DetailPage;
import net.shinkasystems.kintai.page.IndexPage;
import net.shinkasystems.kintai.page.SignInPage;

import org.apache.wicket.Page;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;

/**
 * 勤怠管理ツールのウェブアプリケーションクラスです。
 * 
 * @author Aogiri
 *
 */
public class KintaiWebApplication extends AuthenticatedWebApplication {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.wicket.protocol.http.WebApplication#init()
	 */
	@Override
	protected void init() {
		super.init();

		getRequestCycleSettings().setResponseRequestEncoding("UTF-8");
		getMarkupSettings().setDefaultMarkupEncoding("UTF-8");
		
		mountPage("/index", IndexPage.class);
		mountPage("/apply", ApplyPage.class);
		mountPage("/detail", DetailPage.class);
		mountPage("/config", ConfigPage.class);
		mountPage("/admin", AdminPage.class);
		mountPage("/signin", SignInPage.class);
		
	}

	@Override
	public Class<? extends Page> getHomePage() {
		return IndexPage.class;
	}

	@Override
	protected Class<? extends WebPage> getSignInPageClass() {
		return SignInPage.class;
	}

	@Override
	protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
		return KintaiSession.class;
	}

}
