package net.shinkasystems.kintai.mail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import net.shinkasystems.kintai.KintaiConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum KintaiMailPropery {

	PORT("mail.smtp.port"),
	USER("mail.smtp.user"),
	PASSWORD("mail.smtp.password");

	/** ロガー */
	private static final Logger log = LoggerFactory.getLogger(KintaiMailPropery.class);

	/**
	 * メール設定を管理するプロパティファイルです。
	 */
	public static final File DEPLOYMENT_PROPERTIES_FILE =
			new File(KintaiConstants.APP_DATA_DIR, "smtp.properties");

	/**
	 * メール設定を管理するプロパティファイルです。
	 */
	public static final String DEVELOPMENT_PROPERTIES_FILE_PATH =
			"META-INF/properties/smtp.properties";

	/**
	 * 
	 */
	public static final Properties PROPERTIES;

	/**
	 * 
	 */
	private final String key;

	/**
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
				PROPERTIES.load(KintaiMailPropery.class.getClassLoader().getResourceAsStream(
						DEVELOPMENT_PROPERTIES_FILE_PATH));

				log.info("【開発】環境用のメール設定ファイルを読み込みました");

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private KintaiMailPropery(String key) {
		this.key = key;
	}

	public String getValue() {
		return PROPERTIES.getProperty(key);
	}
}
