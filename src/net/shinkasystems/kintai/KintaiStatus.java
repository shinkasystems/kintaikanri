package net.shinkasystems.kintai;

/**
 * 申請情報のステータスを管理します。
 * @author Aogiri
 *
 */
public enum KintaiStatus {

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
	WITHDRAWN("取下げ"),
	
	/**
	 * 「差戻し」のステータスです。
	 */
	PASSBACK("差戻し");
	
	/**
	 * 勤怠申請の表示用文字列です。
	 */
	public final String display;

	/**
	 * 
	 * @param display
	 */
	private KintaiStatus(String display) {
		this.display = display;
	}
	
	public String toString() {
		return display;
	}
}
