package net.shinkasystems.kintai;

import java.util.Optional;

import net.shinkasystems.kintai.entity.UserDao;
import net.shinkasystems.kintai.util.DaoFactory;

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
	 * DBファイルが存在しない場合、DBファイルを生成します。 テーブルを作成し、スタティックデータ（マスタ）を追加します。
	 */
	private static void initDB() {

		/*
		 * モード設定
		 */
		Optional<String> modeOptional = Optional.ofNullable(System.getProperty("wicket.configuration"));
		String mode = modeOptional.orElse("DEVELOPMENT");

		if (mode.toUpperCase().equals("DEPLOYMENT")) {
			KintaiDB.setMode(KintaiDB.DEPLOYMENT);
		} else {
			KintaiDB.setMode(KintaiDB.DEVELOPMENT);
		}

		/*
		 * テーブル件数を取得し、0件だった場合、テーブルを再作成する
		 */
		int numberOfTable = KintaiDB.singleton().getTransactionManager().required(() -> {

			UserDao systemDao = DaoFactory.createDaoImplements(UserDao.class);
			return systemDao.selectCountTable();
		});

		if (numberOfTable == 0) {

			log.info(KintaiLog.INFOMATION_001.get());

		} else {

			log.info(KintaiLog.INFOMATION_002.get());

			KintaiDB.createDB();
		}
	}
}
