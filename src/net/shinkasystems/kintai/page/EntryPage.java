package net.shinkasystems.kintai.page;

import net.shinkasystems.kintai.KintaiRole;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;

/**
 * 申請情報の登録画面です。
 * 
 * @author Aogiri
 * 
 */
@AuthorizeInstantiation({ KintaiRole.USER})
public class EntryPage extends DefaultLayoutPage {

}
