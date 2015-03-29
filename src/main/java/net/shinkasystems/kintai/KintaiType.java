package net.shinkasystems.kintai;

import org.seasar.doma.Domain;

/**
 * 勤怠の申請種類を管理します。
 * 
 * @author Aogiri
 *
 */
@Domain(valueType = String.class, factoryMethod = "of")
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
	 * 冠婚葬祭や出産等に認められる休暇です。有給無給両方存在します。
	 */
	SP_VACATION("特別休暇"),

	/**
	 * 遅刻です。
	 */
	LATE("遅刻"),

	/**
	 * 早退です。
	 */
	EARLY("早退"),

	/**
	 * 振替休日です。
	 */
	SUBSTITUTION("振替休日"),

	/**
	 * 代休です。
	 */
	COMPENSATORY("代休"),

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
	 * コンストラクタです。
	 * @param display
	 */
	private KintaiType(String display) {
		this.display = display;
	}

	/**
	 * ドメイン文字列から KintaiType へ変換します。
	 * @param value ドメイン文字列
	 * @return 勤怠の申請種類
	 */
	public static KintaiType of(String value) {

		for (KintaiType kintaiType : KintaiType.values()) {
			if (kintaiType.name().equals(value)) {
				return kintaiType;
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
	 * 勤怠申請の表示用文字列を返します。
	 */
	public String toString() {
		return display;
	}

}
