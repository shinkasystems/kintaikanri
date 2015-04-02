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
 * 勤怠情報の詳細画面用サービスです。
 * 
 * @author Aogiri
 *
 */
public class DetailService {

	/** ロガー */
	private static final Logger log = LoggerFactory.getLogger(DetailService.class);

	public void approve(Notification notification) {

		TransactionManager transactionManager = KintaiDB.singleton().getTransactionManager();

		transactionManager.required(() -> {

			final NotificationDao dao = DaoFactory.createDaoImplements(NotificationDao.class);

			notification.setStatus(NotificationStatus.APPROVED);
			notification.setUpdateDate(LocalDate.now());
			dao.update(notification);

			log.info("承認しました");
		});

	}

	public void reject(Notification notification) {

		TransactionManager transactionManager = KintaiDB.singleton().getTransactionManager();

		transactionManager.required(() -> {

			final NotificationDao dao = DaoFactory.createDaoImplements(NotificationDao.class);

			notification.setStatus(NotificationStatus.REJECTED);
			notification.setUpdateDate(LocalDate.now());
			dao.update(notification);

			log.info("却下しました");
		});

	}

	public void withdraw(Notification notification) {

		TransactionManager transactionManager = KintaiDB.singleton().getTransactionManager();

		transactionManager.required(() -> {

			final NotificationDao dao = DaoFactory.createDaoImplements(NotificationDao.class);

			notification.setStatus(NotificationStatus.WITHDRAWN);
			notification.setUpdateDate(LocalDate.now());
			dao.update(notification);

			log.info("取消しました");
		});

	}

	/**
	 * 指定IDの申請情報を取得します。
	 * 
	 * @param id 勤怠情報ID
	 * @return 勤怠情報
	 */
	public Notification getNotification(int id) {

		TransactionManager transactionManager = KintaiDB.singleton().getTransactionManager();

		return transactionManager.required(() -> {

			final NotificationDao dao = DaoFactory.createDaoImplements(NotificationDao.class);

			return dao.selectById(id);
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

			final UserDao dao = DaoFactory.createDaoImplements(UserDao.class);

			return dao.selectById(id);
		});

	}
}
