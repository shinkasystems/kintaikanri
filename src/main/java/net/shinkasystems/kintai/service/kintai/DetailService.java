package net.shinkasystems.kintai.service.kintai;

import java.util.Date;

import net.shinkasystems.kintai.KintaiDB;
import net.shinkasystems.kintai.KintaiStatus;
import net.shinkasystems.kintai.entity.Application;
import net.shinkasystems.kintai.entity.ApplicationDao;
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

	public void approve(Application application) {

		TransactionManager transactionManager = KintaiDB.singleton().getTransactionManager();

		transactionManager.required(() -> {

			final ApplicationDao dao = DaoFactory.createDaoImplements(ApplicationDao.class);

			application.setStatus(KintaiStatus.APPROVED);
			application.setUpdateDate(new java.sql.Date(new Date().getTime()));
			dao.update(application);

			log.info("承認しました");
		});

	}

	public void reject(Application application) {

		TransactionManager transactionManager = KintaiDB.singleton().getTransactionManager();

		transactionManager.required(() -> {

			final ApplicationDao dao = DaoFactory.createDaoImplements(ApplicationDao.class);

			application.setStatus(KintaiStatus.REJECTED);
			application.setUpdateDate(new java.sql.Date(new Date().getTime()));
			dao.update(application);

			log.info("却下しました");
		});

	}

	public void withdraw(Application application) {

		TransactionManager transactionManager = KintaiDB.singleton().getTransactionManager();

		transactionManager.required(() -> {

			final ApplicationDao dao = DaoFactory.createDaoImplements(ApplicationDao.class);

			application.setStatus(KintaiStatus.WITHDRAWN);
			application.setUpdateDate(new java.sql.Date(new Date().getTime()));
			dao.update(application);

			log.info("取消しました");
		});

	}

	/**
	 * 指定IDの申請情報を取得します。
	 * 
	 * @param id 勤怠情報ID
	 * @return 勤怠情報
	 */
	public Application getApplication(int id) {

		TransactionManager transactionManager = KintaiDB.singleton().getTransactionManager();

		return transactionManager.required(() -> {

			final ApplicationDao dao = DaoFactory.createDaoImplements(ApplicationDao.class);

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
