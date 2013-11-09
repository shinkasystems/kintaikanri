package net.shinkasystems.kintai.page;

import net.shinkasystems.kintai.KintaiRole;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;

/**
 * 申請情報の一覧画面です。 一覧画面は勤怠管理ツールのホーム画面でもあります。
 * 
 * アクセス可能な、ユーザー権限は以下の通りです。
 * <ul>
 * <li>管理ユーザー</li>
 * <li>一般ユーザー</li>
 * <li>期限切れ一般ユーザー</li>
 * </ul>
 * 
 * 期限切れ一般ユーザーには、パスワードの有効期限が切れている旨のメッセージが表示されます。
 * 
 * @author Aogiri
 * 
 */
@AuthorizeInstantiation({ KintaiRole.ADMIN, KintaiRole.USER,
		KintaiRole.EXPIRED_USER })
public class IndexPage extends DefaultLayoutPage {

}
