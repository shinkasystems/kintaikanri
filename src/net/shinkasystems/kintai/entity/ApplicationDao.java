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
public interface ApplicationDao {

    /**
     * @param id
     * @return the Application entity
     */
    @Select
    Application selectById(Integer id);

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