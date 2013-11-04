package net.shinkasystems.kintai;

import org.apache.wicket.authroles.authorization.strategies.role.Roles;

/**
 * 勤怠管理ツールのロール（権限）を管理するクラスです。 列挙型のクラス名がデータベースに保存され、判定に使用されるため、
 * ロールの列挙型名称（クラス名）を変える場合、データベースのロールも書き換える必要があります。 同様に、createRoles メソッドや、
 * toString メソッドのの実装を変更した場合、 データベースのロールも書き換えなければなりません。
 * 権限の表示名称はシステムに影響を与えることなく変更することができます。
 * 
 * @author Aogiri
 * 
 */
public enum KintaiRole {

	ADMIN("管理者"), USER("一般"), EXPIRED_USER("期限切れ");
	
	public static final String CONST_ADMIN = "ADMIN";

	public static final String CONST_USER = "USER";

	public static final String CONST_EXPIRED_USER = "EXPIRED_USER";

	/**
	 * 権限の表示名称です。
	 */
	public final String displayName;

	private KintaiRole(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * Wicket Auth Role が使用する Roles を生成します。
	 * 
	 * @return
	 */
	public Roles getWicketRoles() {
		return new Roles(this.name());
	}

	@Override
	public String toString() {
		return this.name();
	}
}
