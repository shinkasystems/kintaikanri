package net.shinkasystems.kintai.entity;

import net.shinkasystems.kintai.KintaiDB;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;

/**
 */
@Dao(config = KintaiDB.class)
public interface UserDao {

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