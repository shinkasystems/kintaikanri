package net.shinkasystems.kintai.component;

import java.util.ArrayList;
import java.util.List;

import net.shinkasystems.kintai.KintaiDB;
import net.shinkasystems.kintai.entity.User;
import net.shinkasystems.kintai.entity.UserDao;
import net.shinkasystems.kintai.entity.UserDaoImpl;
import net.shinkasystems.kintai.entity.sub.UserData;

import org.seasar.doma.jdbc.SelectOptions;
import org.seasar.doma.jdbc.tx.LocalTransaction;

/**
 * 
 * @author Aogiri
 *
 */
public class UserOptionUtility {

	private UserOptionUtility() {
		super();
		// TODO 自動生成されたコンストラクター・スタブ
	}

	/**
	 * 
	 * @return
	 */
	public static List<UserOption> getUserOptions() {
		
		List<UserOption> userOptions = new ArrayList<UserOption>();
		
		LocalTransaction transaction = KintaiDB.getLocalTransaction();
		try {
			transaction.begin();

			final UserDao dao = new UserDaoImpl();

			for (UserData userData: dao.selectUserData(SelectOptions.get())) {
				userOptions.add(new UserOption(userData.getId(), userData.getDisplayName()));
			}

		} finally {
			transaction.rollback();
		}
		

		
		return userOptions;
	}

	/**
	 * 代理申請が可能な申請者を取得します。
	 * ログインユーザーが決裁者となっている申請者の申請を代理することができます。
	 * 
	 * @param authorityId
	 * @return
	 */
	public static List<UserOption> getUserOptions(int authorityId) {
		
		final List<UserOption> applicantOptions = new ArrayList<UserOption>();

		LocalTransaction transaction = KintaiDB.getLocalTransaction();
		try {
			transaction.begin();

			final UserDao dao = new UserDaoImpl();
			
			for (User user : dao.selectByAuthorityID(authorityId)) {
				applicantOptions.add(new UserOption(user.getId(), user.getDisplayName()));
			}

		} finally {
			transaction.rollback();
		}
		
		return applicantOptions;
	}
}
