package net.shinkasystems.kintai.page;

import net.shinkasystems.kintai.KintaiRole;
import net.shinkasystems.kintai.page.layout.DefaultLayoutPage;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;

/**
 * 
 * @author Aogiri
 *
 */
@AuthorizeInstantiation({KintaiRole.CONST_ADMIN, KintaiRole.CONST_USER})
public class IndexPage extends DefaultLayoutPage {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
