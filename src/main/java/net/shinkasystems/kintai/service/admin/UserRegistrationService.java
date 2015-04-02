package net.shinkasystems.kintai.service.admin;

import java.time.LocalDate;

import net.shinkasystems.kintai.KintaiDB;
import net.shinkasystems.kintai.entity.User;
import net.shinkasystems.kintai.entity.UserDao;
import net.shinkasystems.kintai.util.Authentication;
import net.shinkasystems.kintai.util.DaoFactory;

import org.seasar.doma.jdbc.tx.TransactionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ユーザー登録画面用サービスです。
 * @author Aogiri
 *
 */
public class UserRegistrationService {

	/** ロガー */
	private static final Logger log = LoggerFactory.getLogger(UserRegistrationService.class);

	/**
	 * ユーザーを登録します。
	 * @param userName ユーザー名
	 * @param password パスワード
	 * @param displayName 表示名
	 * @param emailAddress メールアドレス
	 * @param authorityId 決裁者ID
	 * @param activated ユーザーが有効の場合 true、それ以外は false
	 * @param role ロール
	 */
	public void registUser(
			String userName,
			String password,
			String displayName,
			String emailAddress,
			int authorityId,
			boolean activated,
			String role) {

		LocalDate expiredLocalDate = LocalDate.now().plusMonths(3);

		TransactionManager transactionManager = KintaiDB.singleton().getTransactionManager();

		transactionManager.required(() -> {
			final UserDao dao = DaoFactory.createDaoImplements(UserDao.class);

			final User user = new User();
			user.setUserName(userName);
			user.setPassword(new Authentication(userName, password).getPasswordHash());
			user.setDisplayName(displayName);
			user.setEmailAddress(emailAddress);

			if (authorityId > 0) {
				user.setAuthorityId(authorityId);
			} else {
				user.setAuthorityId(null);
			}
			user.setActivated(activated);
			user.setExpireDate(expiredLocalDate);
			user.setRole(role);

			dao.insert(user);

			if (user.getAuthorityId() == null) {
				user.setAuthorityId(user.getId());

				dao.update(user);
			}

			log.info("ユーザーを新規登録しました。" + user);

		});
	}
}
