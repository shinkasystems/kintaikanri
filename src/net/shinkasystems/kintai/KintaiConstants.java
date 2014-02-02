package net.shinkasystems.kintai;

import java.io.File;

import org.apache.commons.lang3.time.FastDateFormat;

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
	public static final int SERVER_PORT = 80;

	/**
	 * アプリケーションのユーザーデータを管理するディレクトリです。
	 */
	public static final File APP_DATA_DIR = new File(
			System.getProperty("user.home"), "kintai_data");

	/**
	 * アプリケーションのデータベースファイル（拡張子なし）です。
	 */
	public static final File APP_DB_FILE = new File(APP_DATA_DIR, "kintai");

	/**
	 * アプリケーションのデータベースファイル（拡張子つき）です。
	 */
	public static final File APP_DB_FILE_WITH_EXTENSION = new File(
			APP_DB_FILE.getAbsolutePath() + ".h2.db");

	/**
	 * 標準的な日付のパターンです。
	 */
	public static final String DATE_PATTERN = "yyyy-MM-dd";

	/**
	 * 標準的な日付のフォーマットです。
	 */
	public static final FastDateFormat DATE_FORMAT = FastDateFormat
			.getInstance(DATE_PATTERN);

	/**
	 * 標準的な時間のパターンです。
	 */
	public static final String TIME_PATTERN = "HH:mm:ss";

	/**
	 * 標準的な時間のフォーマットです。 
	 */
	public static final FastDateFormat TIME_FORMAT = FastDateFormat
			.getInstance(TIME_PATTERN);

	/**
	 * 標準的な日付と時間のパターンです。
	 */
	public static final String DATE_TIME_PATTERN = DATE_PATTERN + " "
			+ TIME_PATTERN;

	/**
	 * 標準的な日付と時間のフォーマットです。
	 */
	public static final FastDateFormat DATE_TIME_FORMAT = FastDateFormat
			.getInstance(DATE_TIME_PATTERN);

	/**
	 * このクラスのインスタンス生成は制限されています。 パブリックなコンストラクタは作成しないでください。
	 */
	private KintaiConstants() {

	}
}
