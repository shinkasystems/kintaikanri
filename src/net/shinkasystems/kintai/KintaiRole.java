package net.shinkasystems.kintai;


/**
 * 勤怠管理ツールのロール（権限）を管理するクラスです。 
 * 
 * @author Aogiri
 * 
 */
public class KintaiRole {

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

	/**
	 * 
	 */
	private KintaiRole() {
		super();
		// TODO 自動生成されたコンストラクター・スタブ
	}
	
}
