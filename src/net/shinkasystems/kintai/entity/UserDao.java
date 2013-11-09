package net.shinkasystems.kintai.entity;

import java.util.List;
import java.util.Map;

import net.shinkasystems.kintai.KintaiDB;

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
public interface UserDao {

	/**
	 * テーブルを作成します。
	 */
	@Script
	void createTable();

	/**
	 * @param id
	 * @return the User entity
	 */
	@Select
	User selectById(Integer id);

	/**
	 * 
	 * @param userName
	 * @return
	 */
	@Select
	User selectByUserName(String userName);

	/**
	 * 
	 * @return
	 */
	@Select
	List<Map<String, Object>> selectUserData(SelectOptions options);
	
	/**
	 * @param entity
	 * @return affected rows
	 */
	@Insert
	int insert(User entity);

	/**
	 * @param entity
	 * @return affected rows
	 */
	@Update
	int update(User entity);

	/**
	 * @param entity
	 * @return affected rows
	 */
	@Delete
	int delete(User entity);
}