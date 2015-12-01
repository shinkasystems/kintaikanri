package net.shinkasystems.kintai.entity;

import java.sql.Date;
import java.util.List;

import net.shinkasystems.kintai.KintaiDB;
import net.shinkasystems.kintai.entity.sub.NotificationData;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Script;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.jdbc.SelectOptions;

/**
 * 
 */
@Dao(config = KintaiDB.class)
public interface NotificationDao {

	/**
	 * テーブルを作成します。
	 */
	@Script
	void createTable();

	/**
	 * 
	 * @param id
	 * @return the Notification entity
	 */
	@Select
	Notification selectById(Integer id);

	/**
	 * 
	 * @param options
	 * @return
	 */
	@Select
	List<NotificationData> selectNotificationData(SelectOptions options, Date from, Date to, Integer applicantID,
			String status, String orderBy);

	/**
	 * 
	 * @return
	 */
	@Select
	long selectCountNotification(Date from, Date to, Integer applicantID, String status);

	/**
	 * 
	 * @param userID
	 * @return
	 */
	@Select
	boolean selectHistoryExists(Integer userID);

	/**
	 * @param entity
	 * @return affected rows
	 */
	@Insert
	int insert(Notification entity);

	/**
	 * @param entity
	 * @return affected rows
	 */
	@Update
	int update(Notification entity);

	/**
	 * @param entity
	 * @return affected rows
	 */
	@Delete
	int delete(Notification entity);
}