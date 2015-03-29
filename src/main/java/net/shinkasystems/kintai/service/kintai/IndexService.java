package net.shinkasystems.kintai.service.kintai;

import java.io.Serializable;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;

import net.shinkasystems.kintai.KintaiDB;
import net.shinkasystems.kintai.domain.KintaiStatus;
import net.shinkasystems.kintai.entity.ApplicationDao;
import net.shinkasystems.kintai.entity.sub.ApplicationData;
import net.shinkasystems.kintai.util.DaoFactory;

import org.seasar.doma.jdbc.SelectOptions;
import org.seasar.doma.jdbc.tx.TransactionManager;

/**
 * 一覧画面用のサービスです。
 * 
 * @author Aogiri
 *
 */
public class IndexService implements Serializable {

	/**
	 * 勤怠情報の一覧表示用データを返します。
	 * 
	 * @param from 期間
	 * @param to 期間
	 * @param applicantId 申請者ID
	 * @param status 勤怠情報のステータス
	 * @param first オフセット
	 * @param count 件数
	 * @param sortProperty ソート項目
	 * @param ascending ソート順
	 * @return
	 */
	public Iterator<ApplicationData> getApplivcationDataIterator(
			Date from, Date to, Integer applicantId, KintaiStatus status,
			long first, long count, String sortProperty, boolean ascending) {

		SelectOptions options = SelectOptions.get().offset((int) first).limit((int) count);
		String orderBy = "order by " + sortProperty + (ascending ? " ASC" : " DESC" + ", ID");

		TransactionManager transactionManager = KintaiDB.singleton().getTransactionManager();

		List<ApplicationData> applicationDatas = transactionManager.required(() -> {

			final ApplicationDao dao = DaoFactory.createDaoImplements(ApplicationDao.class);

			return dao.selectApplicationData(
					options,
					from,
					to,
					applicantId,
					status != null ? status.name() : null,
					orderBy);
		});

		return applicationDatas.iterator();
	}

	/**
	 * 勤怠情報の申請件数を返します。
	 * 
	 * @return 勤怠情報の申請件数
	 */
	public long countApplication() {

		TransactionManager transactionManager = KintaiDB.singleton().getTransactionManager();

		return transactionManager.required(() -> {

			final ApplicationDao dao = DaoFactory.createDaoImplements(ApplicationDao.class);

			return dao.selectCountApplication();
		});
	}
}
