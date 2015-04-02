package net.shinkasystems.kintai.service.notification;

import java.time.LocalDate;

import net.shinkasystems.kintai.KintaiDB;
import net.shinkasystems.kintai.domain.NotificationStatus;
import net.shinkasystems.kintai.entity.Notification;
import net.shinkasystems.kintai.entity.NotificationDao;
import net.shinkasystems.kintai.entity.User;
import net.shinkasystems.kintai.entity.UserDao;
import net.shinkasystems.kintai.util.DaoFactory;

import org.seasar.doma.jdbc.tx.TransactionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 申請画面用サービスです。
 * 
 * @author Aogiri
 *
 */
public class EntryService {

	/** ロガー */
	private static final Logger log = LoggerFactory.getLogger(EntryService.class);

	/**
	 * 勤怠情報を申請します。
	 * 
	 * @param notification 勤怠情報
	 */
	public void entry(Notification notification) {

		TransactionManager transactionManager = KintaiDB.singleton().getTransactionManager();

		transactionManager.required(() -> {

			final NotificationDao dao = DaoFactory.createDaoImplements(NotificationDao.class);

			notification.setCreateDate(LocalDate.now());
			notification.setStatus(NotificationStatus.PENDING);

			dao.insert(notification);

			log.info("勤怠情報を申請しました。" + notification);
		});

	}

	/**
	 * 指定IDのユーザーを取得します。
	 * 
	 * @param id ユーザーID
	 * @return ユーザー情報
	 */
	public User getUser(int id) {

		TransactionManager transactionManager = KintaiDB.singleton().getTransactionManager();

		return transactionManager.required(() -> {

			final UserDao dao = (UserDao) DaoFactory.createDaoImplements(UserDao.class);

			return dao.selectById(id);
		});
	}
}
