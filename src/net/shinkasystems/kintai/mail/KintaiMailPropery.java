package net.shinkasystems.kintai.mail;

import java.io.IOException;
import java.util.Properties;

public enum KintaiMailPropery {

	PORT("mail.smtp.port"),
	USER("mail.smtp.user"),
	PASSWORD("mail.smtp.password");
	
	/**
	 * メール設定を管理するプロパティファイルです。
	 */
	public static final String PROPERTIES_FILE_PATH = "META-INF/properties/smtp.properties";
	
	public static final Properties PROPERTIES;
	
	private final String key;
	
	static {
		PROPERTIES = new Properties();
		try {
			PROPERTIES.load(KintaiMailPropery.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE_PATH));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private KintaiMailPropery(String key) {
		this.key = key;
	}
	
	public String getValue() {
		return PROPERTIES.getProperty(key);
	}
}
