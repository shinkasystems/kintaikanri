package net.shinkasystems.kintai.service.kintai;

import java.sql.Date;

import net.shinkasystems.kintai.KintaiDB;
import net.shinkasystems.kintai.domain.KintaiStatus;
import net.shinkasystems.kintai.entity.Application;
import net.shinkasystems.kintai.entity.ApplicationDao;
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
	 * @param application 勤怠情報
	 */
	public void entry(Application application) {

		TransactionManager transactionManager = KintaiDB.singleton().getTransactionManager();

		transactionManager.required(() -> {

			final ApplicationDao dao = DaoFactory.createDaoImplements(ApplicationDao.class);

			application.setCreateDate(new Date(new java.util.Date().getTime()));
			application.setStatus(KintaiStatus.PENDING);

			dao.insert(application);

			log.info("勤怠情報を申請しました。" + application);
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
