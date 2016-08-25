package net.shinkasystems.kintai;


/**
 * 勤怠管理ツールの定数値を管理します。
 * 
 * @author Aogiri
 * 
 */
public class KintaiConstants {

	/**
	 * アプリケーションのポートです。
	 */
	public static final int SERVER_PORT = 8080;

	/**
	 * 標準的な日付のパターンです。
	 */
	public static final String DATE_PATTERN = "yyyy-MM-dd";

	/**
	 * 標準的な時間のパターンです。
	 */
	public static final String TIME_PATTERN = "HH:mm:ss";


	/**
	 * 標準的な日付と時間のパターンです。
	 */
	public static final String DATE_TIME_PATTERN = DATE_PATTERN + " "
			+ TIME_PATTERN;

	/**
	 * このクラスのインスタンス生成は制限されています。 パブリックなコンストラクタは作成しないでください。
	 */
	private KintaiConstants() {

	}
}
