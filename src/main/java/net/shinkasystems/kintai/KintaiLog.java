/**
 * 
 */
package net.shinkasystems.kintai;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import net.shinkasystems.kintai.util.ResourceBundleControlWithUtf8;

/**
 * @author Aogiri
 *
 */
public enum KintaiLog {

	ERROR_001,
	ERROR_002,
	WARNING_001,
	WARNING_002,
	INFOMATION_001,
	INFOMATION_002;

	/**
	 * ログメッセージを管理するプロパティファイルのパスです。
	 */
	private static final String LOG_MESSAGE_FILE_PATH = "META-INF/properties/log-message";

	/**
	 * ログメッセージを管理するリソースバンドルです。
	 */
	private static final ResourceBundle BUNDLE =
			ResourceBundle.getBundle(LOG_MESSAGE_FILE_PATH, new ResourceBundleControlWithUtf8());

	/**
	 * ログメッセージを取得します。
	 * @param objects ログメッセージのプレースホルダの値です。
	 * @return ログメッセージ
	 */
	public String get(Object... objects) {

		try {

			String pattern = BUNDLE.getString(this.name());

			return MessageFormat.format(pattern, objects);

		} catch (Throwable throwable) {

			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);

			throwable.printStackTrace(pw);

			return "[ERROR_000] Failed to get a message because of following error : " + sw;
		}
	}
}
