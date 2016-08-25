package net.shinkasystems.kintai.mail;


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

	/**
	 * メール設定のキーです。
	 */
	private final String key;

	/**
	 * コンストラクタです。
	 * @param key メール設定のキー
	 */
	private MailerProperty(String key) {
		this.key = key;
	}

	/**
	 * @return key
	 */
	public String getKey() {
		return key;
	}
}
