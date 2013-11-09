package net.shinkasystems.kintai.page.admin;

import net.shinkasystems.kintai.KintaiRole;
import net.shinkasystems.kintai.page.DefaultLayoutPage;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;

/**
 * 
 * @author Aogiri
 *
 */
@AuthorizeInstantiation({ KintaiRole.ADMIN })
public class UserNewPage extends DefaultLayoutPage {

}
