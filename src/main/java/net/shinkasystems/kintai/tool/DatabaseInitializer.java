package net.shinkasystems.kintai.tool;

import net.shinkasystems.kintai.KintaiDB;

/**
 * データベースの初期化ツールです。
 * 
 * @author Aogiri
 *
 */
public class DatabaseInitializer {

	public static void main(String[] args) {

		KintaiDB.setMode(KintaiDB.DEVELOPMENT);
		KintaiDB.createDB();

	}

}
