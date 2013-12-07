package net.shinkasystems.kintai.entity;

import java.sql.Date;
import java.util.List;

import net.shinkasystems.kintai.KintaiDB;
import net.shinkasystems.kintai.entity.sub.ApplicationData;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Script;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.jdbc.SelectOptions;

/**
 */
@Dao(config = KintaiDB.class)
public interface ApplicationDao {

	/**
	 * テーブルを作成します。
	 */
	@Script
	void createTable();

	/**
	 * @param id
	 * @return the Application entity
	 */
	@Select
	Application selectById(Integer id);
	
	/**
	 * 
	 * @param options
	 * @return
	 */
	@Select
	List<ApplicationData> selectApplicationData(SelectOptions options, Date from, Date to, Integer applicantID, String status, String orderBy);
	
	/**
	 * 
	 * @return
	 */
	@Select
	int selectCountApplication();
	
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
	int insert(Application entity);

	/**
	 * @param entity
	 * @return affected rows
	 */
	@Update
	int update(Application entity);

	/**
	 * @param entity
	 * @return affected rows
	 */
	@Delete
	int delete(Application entity);
}