package net.shinkasystems.kintai.service.admin;

import net.shinkasystems.kintai.KintaiDB;
import net.shinkasystems.kintai.entity.NotificationDao;
import net.shinkasystems.kintai.entity.User;
import net.shinkasystems.kintai.entity.UserDao;
import net.shinkasystems.kintai.util.Authentication;
import net.shinkasystems.kintai.util.DaoFactory;

import org.seasar.doma.jdbc.tx.TransactionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;

/**
 * ユーザー更新画面用のサービスです。
 * @author Aogiri
 *
 */
public class UserEditService {

	/** ロガー */
	private static final Logger log = LoggerFactory.getLogger(UserEditService.class);

	/**
	 * ユーザーを更新します。
	 * null でない項目を変更し、Null の項目は変更しません。
	 * 
	 * @param userId 更新対象のユーザーID
	 * @param password パスワード（任意）
	 * @param displayName 表示名（任意）
	 * @param emailAddress メールアドレス（任意）
	 * @param authorityId 決裁者ID（任意）（更新対象外の場合は0指定）
	 * @param role 役割（任意）
	 */
	public void updateUser(
			int userId,
			String password,
			String displayName,
			String emailAddress,
			int authorityId,
			String role) {

		TransactionManager transactionManager = KintaiDB.singleton().getTransactionManager();

		transactionManager.required(() -> {

			final UserDao dao = DaoFactory.createDaoImplements(UserDao.class);

			final User user = dao.selectById(userId);

			if (!Strings.isNullOrEmpty(password)) {
				user.setPassword(new Authentication(user.getUserName(), password).getPasswordHash());
			}
			if (!Strings.isNullOrEmpty(displayName)) {
				user.setDisplayName(displayName);
			}
			if (!Strings.isNullOrEmpty(emailAddress)) {
				user.setEmailAddress(emailAddress);
			}
			if (authorityId > 0) {
				user.setAuthorityId(authorityId);
			}
			if (!Strings.isNullOrEmpty(role)) {
				user.setRole(role);
			}

			dao.update(user);

			log.info("ユーザーを編集しました。");
		});

	}

	/**
	 * ユーザーを有効化します。
	 * 
	 * @param userId ユーザーID
	 */
	public void activateUser(int userId) {

		TransactionManager transactionManager = KintaiDB.singleton().getTransactionManager();

		transactionManager.required(() -> {

			final UserDao dao = DaoFactory.createDaoImplements(UserDao.class);

			final User user = dao.selectById(userId);

			user.setActivated(true);

			dao.update(user);

			log.info("ユーザーを有効化しました。");
		});

	}

	/**
	 * ユーザーを無効化します。
	 * 
	 * @param userId ユーザーID
	 */
	public void disableUser(int userId) {

		TransactionManager transactionManager = KintaiDB.singleton().getTransactionManager();

		transactionManager.required(() -> {

			final UserDao dao = DaoFactory.createDaoImplements(UserDao.class);

			final User user = dao.selectById(userId);

			user.setActivated(false);

			dao.update(user);

			log.info("ユーザーを無効化しました。");
		});
	}

	/**
	 * ユーザーを削除します。
	 * 
	 * @param userId ユーザーID
	 */
	public void deleteUser(int userId) {

		TransactionManager transactionManager = KintaiDB.singleton().getTransactionManager();

		transactionManager.required(() -> {

			final UserDao dao = DaoFactory.createDaoImplements(UserDao.class);

			final User user = dao.selectById(userId);

			dao.delete(user);

			log.info("ユーザーを削除しました。");

		});
	}

	/**
	 * 指定IDのユーザーを取得します。
	 * 
	 * @param id
	 * @return
	 */
	public User getUser(int id) {

		TransactionManager transactionManager = KintaiDB.singleton().getTransactionManager();

		return transactionManager.required(() -> {

			final UserDao dao = DaoFactory.createDaoImplements(UserDao.class);

			return dao.selectById(id);
		});

	}

	/**
	 * 指定のIDのユーザーに申請履歴が存在するか返します。
	 * 
	 * @param id ユーザーID
	 * @return 申請履歴が存在すれば true、しなければ false
	 */
	public boolean isHistoryExists(int id) {

		TransactionManager transactionManager = KintaiDB.singleton().getTransactionManager();

		return transactionManager.required(() -> {

			final NotificationDao dao = DaoFactory.createDaoImplements(NotificationDao.class);

			return dao.selectHistoryExists(id);
		});
	}
}
