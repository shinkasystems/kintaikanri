package net.shinkasystems.kintai;

import java.util.Optional;

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
	 * DB設定を初期化します。
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
	}
}
