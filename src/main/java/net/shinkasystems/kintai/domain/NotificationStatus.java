package net.shinkasystems.kintai.domain;

import org.seasar.doma.Domain;

/**
 * 申請情報のステータスを管理します。
 * @author Aogiri
 *
 */
@Domain(valueType = String.class, factoryMethod = "of")
public enum NotificationStatus {

	/**
	 * 「未決」のステータスです。
	 */
	PENDING("未決"),

	/**
	 * 「承認」のステータスです。
	 */
	APPROVED("承認"),

	/**
	 * 「却下」のステータスです。
	 */
	REJECTED("却下"),

	/**
	 * 「取下げ」のステータスです。
	 */
	WITHDRAWN("取下げ");

	/**
	 * 勤怠申請の表示用文字列です。
	 */
	public final String display;

	/**
	 * コンストラクタです。
	 * @param display
	 */
	private NotificationStatus(String display) {
		this.display = display;
	}

	/**
	 * ドメイン文字列から NotificationStatus へ変換します。
	 * @param value ドメイン文字列
	 * @return 申請情報のステータス
	 */
	public static NotificationStatus of(String value) {

		for (NotificationStatus notificationStatus : NotificationStatus.values()) {
			if (notificationStatus.name().equals(value)) {
				return notificationStatus;
			}
		}
		throw new IllegalArgumentException(value);

	}

	/**
	 * ドメイン文字列を返します。
	 * @return ドメイン文字列
	 */
	public String getValue() {
		return this.name();
	}

	/**
	 * 申請ステータスの表示用文字列です。
	 */
	public String toString() {
		return display;
	}
}
