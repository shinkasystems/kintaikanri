package net.shinkasystems.kintai.mail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import net.shinkasystems.kintai.KintaiConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * メール設定を管理します。
 * @author Aogiri
 *
 */
public enum MailerProperty {

	/**
	 * SMTP のポート番号です。
	 */
	PORT("mail.smtp.port"),

	/**
	 * SMTP のユーザーです
	 */
	USER("mail.smtp.user"),

	/**
	 * SMTP のパスワードです。
	 */
	PASSWORD("mail.smtp.password");

	/** ロガー */
	private static final Logger log = LoggerFactory.getLogger(MailerProperty.class);

	/**
	 * 運用環境用のメール設定を管理するプロパティファイルです。
	 */
	public static final File DEPLOYMENT_PROPERTIES_FILE =
			new File(KintaiConstants.APP_DATA_DIR, "smtp.properties");

	/**
	 * 開発環境用のメール設定を管理するプロパティファイルです。
	 */
	public static final String DEVELOPMENT_PROPERTIES_FILE_PATH =
			"META-INF/properties/smtp.properties";

	/**
	 * メール設定を管理するプロパティファイルです。
	 */
	public static final Properties PROPERTIES;

	/**
	 * メール設定のキーです。
	 */
	private final String key;

	/**
	 * プロパティファイルを読み込みます。
	 * 
	 */
	static {
		PROPERTIES = new Properties();

		if (DEPLOYMENT_PROPERTIES_FILE.exists() && DEPLOYMENT_PROPERTIES_FILE.isFile()) {
			try {
				PROPERTIES.load(new FileReader(DEPLOYMENT_PROPERTIES_FILE));

				log.info("【運用】環境用のメール設定ファイルを読み込みました");

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				PROPERTIES.load(MailerProperty.class.getClassLoader().getResourceAsStream(
						DEVELOPMENT_PROPERTIES_FILE_PATH));

				log.info("【開発】環境用のメール設定ファイルを読み込みました");

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * コンストラクタです。
	 * @param key メール設定のキー
	 */
	private MailerProperty(String key) {
		this.key = key;
	}

	/**
	 * メール設定値を返します。
	 * @return メール設定値
	 */
	public String getValue() {
		return PROPERTIES.getProperty(key);
	}
}
