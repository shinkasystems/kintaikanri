package net.shinkasystems.kintai;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 勤怠管理ツールの起動クラスです。
 * 
 * @author Aogiri
 * 
 */
public class Start {

	/** ロガー */
	private static final Logger log = LoggerFactory.getLogger(Start.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			initAppDataDir();
			initDB();
			startApplicationServer();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * サーバーを起動します。
	 * 
	 * @throws Exception
	 */
	private static void startApplicationServer() throws Exception {

		Server server = new Server(KintaiConstants.SERVER_PORT);

		WebAppContext context = new WebAppContext();
		context.setContextPath("/");
		context.setWar("war");
		context.setParentLoaderPriority(true);

		server.setHandler(context);
		server.start();
	}

	/**
	 * アプリケーションデータディレクトリを初期化します。 アプリケーションデータを格納するディレクトリが存在しない場合、ディレクトリを作成します。
	 * 存在する場合は、このメソッドは何も処理しません。
	 */
	private static void initAppDataDir() {

		if (KintaiConstants.APP_DATA_DIR.exists()) {
			return;
		} else {
			KintaiConstants.APP_DATA_DIR.mkdir();
		}
	}

	/**
	 * DBファイルが存在しない場合、DBファイルを生成します。 テーブルを作成し、スタティックデータ（マスタ）を追加します。
	 */
	private static void initDB() {

		if (KintaiConstants.APP_DB_FILE_WITH_EXTENSION.exists()) {
			
			log.info(KintaiLog.INFOMATION_001.get());
			
		} else {
			
			log.info(KintaiLog.INFOMATION_002.get());
			
			KintaiDB.createDB();
		}
	}
}
