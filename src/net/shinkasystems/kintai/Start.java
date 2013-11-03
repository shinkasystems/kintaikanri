package net.shinkasystems.kintai;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * 勤怠管理ツールの起動クラスです。
 * 
 * @author Aogiri
 *
 */
public class Start {
	
	/**
	 * アプリケーションのポートです。
	 */
	private static final int SERVER_PORT = 8081;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			
			startServer();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * サーバーを起動します。
	 * @throws Exception
	 */
	private static void startServer() throws Exception {

		Server server = new Server(SERVER_PORT);

		WebAppContext context = new WebAppContext();
		context.setContextPath("/");
		context.setWar("war");
		context.setParentLoaderPriority(true);
		
		server.setHandler(context);
		server.start();
	}
	
}
