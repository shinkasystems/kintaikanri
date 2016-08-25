package net.shinkasystems.kintai.mail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MailerPropertyFactory {

	/** ロガー */
	private static final Logger log = LoggerFactory.getLogger(MailerPropertyFactory.class);

	/**
	 * 運用環境用のメール設定を管理するプロパティファイルです。
	 */
	private static final File DEPLOYMENT_PROPERTIES_FILE =
			new File("properties", "smtp.properties");

	/**
	 * 開発環境用のメール設定を管理するプロパティファイルです。
	 */
	private static final String DEVELOPMENT_PROPERTIES_FILE_PATH =
			"META-INF/properties/smtp.properties";

	private MailerPropertyFactory() {

	}

	public static Properties getProperties() {

		Properties properties = new Properties();

		if (DEPLOYMENT_PROPERTIES_FILE.exists() && DEPLOYMENT_PROPERTIES_FILE.isFile()) {
			try {
				properties.load(new FileReader(DEPLOYMENT_PROPERTIES_FILE));

				log.info("【運用】環境用のメール設定ファイルを読み込みました");

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				properties.load(MailerProperty.class.getClassLoader().getResourceAsStream(
						DEVELOPMENT_PROPERTIES_FILE_PATH));

				log.info("【開発】環境用のメール設定ファイルを読み込みました");

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return properties;
	}
}
