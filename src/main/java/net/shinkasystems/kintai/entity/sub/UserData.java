package net.shinkasystems.kintai.entity.sub;

import java.io.Serializable;

import net.shinkasystems.kintai.entity.User;
import net.shinkasystems.kintai.entity.UserListener;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;

/**
 * ユーザー一覧の表示用データを管理します。
 * 
 * @author Aogiri
 *
 */
@Entity(listener = UserListener.class)
public class UserData extends User implements Serializable {

	/**
	 * 決裁者の表示名です。
	 */
	@Column(name = "AUTHORITY_DISPLAY_NAME")
	private String authorityDisplayName;

	/**
	 * 
	 * @return
	 */
	public String getAuthorityDisplayName() {
		return authorityDisplayName;
	}

	/**
	 * 
	 * @param authorityDisplayName
	 */
	public void setAuthorityDisplayName(String authorityDisplayName) {
		this.authorityDisplayName = authorityDisplayName;
	}

}
