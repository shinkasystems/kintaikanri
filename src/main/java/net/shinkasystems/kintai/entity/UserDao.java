package net.shinkasystems.kintai.entity;

import java.util.List;

import net.shinkasystems.kintai.KintaiDB;
import net.shinkasystems.kintai.entity.sub.UserData;

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
public interface UserDao {

	/**
	 * テーブルを作成します。
	 */
	@Script
	void createTable();

	/**
	 * アプリケーションで使用するテーブルの件数を取得します。
	 * @return
	 */
	@Select
	int selectCountTable();

	/**
	 * @param id
	 * @return the User entity
	 */
	@Select
	User selectById(Integer id);

	/**
	 * 
	 * @param authorityId
	 * @return
	 */
	@Select
	List<User> selectByAuthorityId(Integer authorityId);

	/**
	 * 
	 * @param userName
	 * @return
	 */
	@Select
	User selectByUserName(String userName);

	@Select
	User selectByDisplayName(String displayName);

	/**
	 * 
	 * @return
	 */
	@Select
	long selectCountUser();

	/**
	 * 
	 * @return
	 */
	@Select
	List<UserData> selectUserData(SelectOptions options);

	/**
	 * 
	 * @param userId
	 * @return
	 */
	@Select
	boolean selectAuthorityExists(Integer userID);

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