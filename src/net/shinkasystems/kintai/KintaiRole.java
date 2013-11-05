package net.shinkasystems.kintai;

import org.apache.wicket.authroles.authorization.strategies.role.Roles;

/**
 * 勤怠管理ツールのロール（権限）を管理するクラスです。 
 * 
 * @author Aogiri
 * 
 */
public enum KintaiRole {

	ADMIN("管理者"), USER("一般"), EXPIRED_USER("期限切れ");
	
	/**
	 * ロール（権限）に対する定数です。
	 * 
	 * @author Aogiri
	 *
	 */
	public static class Constatnts {
		
		/**
		 * 管理者を表す定数です。
		 */
		public static final String ADMIN = "ADMIN";
		
		/**
		 * 一般ユーザを表す定数です。
		 */
		public static final String USER = "USER";
		
		/**
		 * 期限切れユーザを表す定数です
		 */
		public static final String EXPIRED_USER = "EXPIRED_USER";
	}

	/**
	 * 権限の表示名称です。
	 */
	public final String displayName;

	/**
	 * 
	 * @param displayName
	 */
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
