package net.shinkasystems.kintai;

/**
 * 勤怠の申請種類を管理します。
 * 
 * @author Aogiri
 *
 */
public enum KintaiType {

	/**
	 * 終日の有給休暇です。
	 */
	PAID_VACATION("有給休暇"),
	
	/**
	 * 午前の有給休暇です。
	 */
	AM_VACATION("午前半休"),
	
	/**
	 * 午後の有給休暇です。
	 */
	PM_VACATION("午後半休"),
	
	/**
	 * 会社が定める休暇です。
	 */
	PUBLIC("公休"),
	
	/**
	 * 欠勤です。
	 */
	ABSENCE("欠勤");
	
	/**
	 * 勤怠申請の表示用文字列です。
	 */
	public final String display;

	/**
	 * 
	 * @param display
	 */
	private KintaiType(String display) {
		this.display = display;
	}
	
	public String toString() {
		return display;
	}
}
