package net.shinkasystems.kintai;

import java.io.File;

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
	public static final int SERVER_PORT = 8081;

    /**
     * アプリケーションのユーザーデータを管理するディレクトリです。
     */
    public static final File APP_DATA_DIR = new File(System.getProperty("user.home"), "kintai_data");
    
	/**
     * アプリケーションのデータベースファイル（拡張子なし）です。
     */
    public static final File APP_DB_FILE = new File(APP_DATA_DIR, "kintai");
    
    /**
     * アプリケーションのデータベースファイル（拡張子つき）です。
     */
    public static final File APP_DB_FILE_WITH_EXTENSION = new File(APP_DB_FILE.getAbsolutePath() + ".h2.db");
    
	/**
	 * このクラスのインスタンス生成は制限されています。
	 * パブリックなコンストラクタは作成しないでください。
	 */
	private KintaiConstants() {
		
	}
}
