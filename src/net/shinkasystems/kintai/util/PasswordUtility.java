package net.shinkasystems.kintai.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * パスワードのハッシュ生成に関するユーティリティクラスです。
 * @author Aogiri
 *
 */
public class PasswordUtility {

	/**
	 * ユーザーIDです。
	 */
	private final String userID;

	/**
	 * パスワードです。
	 */
	private final String password;

	/**
	 * ストレッチングの回数です。
	 */
	private final int STRETCH_COUNT = 1000;

	/**
	 * コンストラクタです。
	 * @param userID
	 * @param password
	 */
	public PasswordUtility(String userID, String password) {
		super();
		this.userID = userID;
		this.password = password;
	}

	/**
	 * ソルトを生成します。
	 * @return
	 */
	public String getSalt() {

		return DigestUtils.sha256Hex(userID);
	}

	/**
	 * パスワードのハッシュを生成します。
	 * @return
	 */
	public String getPasswordHash() {

		String passwordHash = getSalt() + password;

		for (int i = 0; i < STRETCH_COUNT; i++) {
			passwordHash = DigestUtils.sha512Hex(passwordHash);
		}

		return passwordHash;
	}
}
