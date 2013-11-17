package net.shinkasystems.kintai.entity;

/** */
@javax.annotation.Generated(value = { "Doma", "1.31.0" }, date = "2013-11-17T17:55:20.952+0900")
public class UserDaoImpl extends org.seasar.doma.internal.jdbc.dao.AbstractDao implements net.shinkasystems.kintai.entity.UserDao {

    static {
        org.seasar.doma.internal.Artifact.validateVersion("1.31.0");
    }

    private static final java.lang.reflect.Method __method1 = org.seasar.doma.internal.jdbc.dao.AbstractDao.__getDeclaredMethod(net.shinkasystems.kintai.entity.UserDao.class, "delete", net.shinkasystems.kintai.entity.User.class);

    private static final java.lang.reflect.Method __method2 = org.seasar.doma.internal.jdbc.dao.AbstractDao.__getDeclaredMethod(net.shinkasystems.kintai.entity.UserDao.class, "insert", net.shinkasystems.kintai.entity.User.class);

    private static final java.lang.reflect.Method __method8 = org.seasar.doma.internal.jdbc.dao.AbstractDao.__getDeclaredMethod(net.shinkasystems.kintai.entity.UserDao.class, "update", net.shinkasystems.kintai.entity.User.class);

    /** */
    public UserDaoImpl() {
        super(new net.shinkasystems.kintai.KintaiDB());
    }

    /**
     * @param connection the connection
     */
    public UserDaoImpl(java.sql.Connection connection) {
        super(new net.shinkasystems.kintai.KintaiDB(), connection);
    }

    /**
     * @param dataSource the dataSource
     */
    public UserDaoImpl(javax.sql.DataSource dataSource) {
        super(new net.shinkasystems.kintai.KintaiDB(), dataSource);
    }

    /**
     * @param config the configuration
     */
    protected UserDaoImpl(org.seasar.doma.jdbc.Config config) {
        super(config);
    }

    /**
     * @param config the configuration
     * @param connection the connection
     */
    protected UserDaoImpl(org.seasar.doma.jdbc.Config config, java.sql.Connection connection) {
        super(config, connection);
    }

    /**
     * @param config the configuration
     * @param dataSource the dataSource
     */
    protected UserDaoImpl(org.seasar.doma.jdbc.Config config, javax.sql.DataSource dataSource) {
        super(config, dataSource);
    }

    @Override
    public void createTable() {
        entering("net.shinkasystems.kintai.entity.UserDaoImpl", "createTable");
        try {
            org.seasar.doma.internal.jdbc.query.SqlFileScriptQuery __query = new org.seasar.doma.internal.jdbc.query.SqlFileScriptQuery();
            __query.setConfig(config);
            __query.setScriptFilePath("META-INF/net/shinkasystems/kintai/entity/UserDao/createTable.script");
            __query.setCallerClassName("net.shinkasystems.kintai.entity.UserDaoImpl");
            __query.setCallerMethodName("createTable");
            __query.setBlockDelimiter("");
            __query.setHaltOnError(true);
            __query.prepare();
            org.seasar.doma.internal.jdbc.command.ScriptCommand __command = new org.seasar.doma.internal.jdbc.command.ScriptCommand(__query);
            __command.execute();
            __query.complete();
            exiting("net.shinkasystems.kintai.entity.UserDaoImpl", "createTable", null);
        } catch (java.lang.RuntimeException __e) {
            throwing("net.shinkasystems.kintai.entity.UserDaoImpl", "createTable", __e);
            throw __e;
        }
    }

    @Override
    public int delete(net.shinkasystems.kintai.entity.User entity) {
        entering("net.shinkasystems.kintai.entity.UserDaoImpl", "delete", entity);
        try {
            if (entity == null) {
                throw new org.seasar.doma.DomaNullPointerException("entity");
            }
            org.seasar.doma.internal.jdbc.query.AutoDeleteQuery<net.shinkasystems.kintai.entity.User> __query = new org.seasar.doma.internal.jdbc.query.AutoDeleteQuery<net.shinkasystems.kintai.entity.User>(net.shinkasystems.kintai.entity._User.getSingletonInternal());
            __query.setMethod(__method1);
            __query.setConfig(config);
            __query.setEntity(entity);
            __query.setCallerClassName("net.shinkasystems.kintai.entity.UserDaoImpl");
            __query.setCallerMethodName("delete");
            __query.setQueryTimeout(-1);
            __query.setVersionIgnored(false);
            __query.setOptimisticLockExceptionSuppressed(false);
            __query.prepare();
            org.seasar.doma.internal.jdbc.command.DeleteCommand __command = new org.seasar.doma.internal.jdbc.command.DeleteCommand(__query);
            int __result = __command.execute();
            __query.complete();
            exiting("net.shinkasystems.kintai.entity.UserDaoImpl", "delete", __result);
            return __result;
        } catch (java.lang.RuntimeException __e) {
            throwing("net.shinkasystems.kintai.entity.UserDaoImpl", "delete", __e);
            throw __e;
        }
    }

    @Override
    public int insert(net.shinkasystems.kintai.entity.User entity) {
        entering("net.shinkasystems.kintai.entity.UserDaoImpl", "insert", entity);
        try {
            if (entity == null) {
                throw new org.seasar.doma.DomaNullPointerException("entity");
            }
            org.seasar.doma.internal.jdbc.query.AutoInsertQuery<net.shinkasystems.kintai.entity.User> __query = new org.seasar.doma.internal.jdbc.query.AutoInsertQuery<net.shinkasystems.kintai.entity.User>(net.shinkasystems.kintai.entity._User.getSingletonInternal());
            __query.setMethod(__method2);
            __query.setConfig(config);
            __query.setEntity(entity);
            __query.setCallerClassName("net.shinkasystems.kintai.entity.UserDaoImpl");
            __query.setCallerMethodName("insert");
            __query.setQueryTimeout(-1);
            __query.setNullExcluded(false);
            __query.setIncludedPropertyNames();
            __query.setExcludedPropertyNames();
            __query.prepare();
            org.seasar.doma.internal.jdbc.command.InsertCommand __command = new org.seasar.doma.internal.jdbc.command.InsertCommand(__query);
            int __result = __command.execute();
            __query.complete();
            exiting("net.shinkasystems.kintai.entity.UserDaoImpl", "insert", __result);
            return __result;
        } catch (java.lang.RuntimeException __e) {
            throwing("net.shinkasystems.kintai.entity.UserDaoImpl", "insert", __e);
            throw __e;
        }
    }

    @Override
    public net.shinkasystems.kintai.entity.User selectById(java.lang.Integer id) {
        entering("net.shinkasystems.kintai.entity.UserDaoImpl", "selectById", id);
        try {
            org.seasar.doma.internal.jdbc.query.SqlFileSelectQuery __query = new org.seasar.doma.internal.jdbc.query.SqlFileSelectQuery();
            __query.setConfig(config);
            __query.setSqlFilePath("META-INF/net/shinkasystems/kintai/entity/UserDao/selectById.sql");
            __query.addParameter("id", java.lang.Integer.class, id);
            __query.setCallerClassName("net.shinkasystems.kintai.entity.UserDaoImpl");
            __query.setCallerMethodName("selectById");
            __query.setResultEnsured(false);
            __query.setQueryTimeout(-1);
            __query.setMaxRows(-1);
            __query.setFetchSize(-1);
            __query.prepare();
            org.seasar.doma.internal.jdbc.command.SelectCommand<net.shinkasystems.kintai.entity.User> __command = new org.seasar.doma.internal.jdbc.command.SelectCommand<net.shinkasystems.kintai.entity.User>(__query, new org.seasar.doma.internal.jdbc.command.EntitySingleResultHandler<net.shinkasystems.kintai.entity.User>(net.shinkasystems.kintai.entity._User.getSingletonInternal()));
            net.shinkasystems.kintai.entity.User __result = __command.execute();
            __query.complete();
            exiting("net.shinkasystems.kintai.entity.UserDaoImpl", "selectById", __result);
            return __result;
        } catch (java.lang.RuntimeException __e) {
            throwing("net.shinkasystems.kintai.entity.UserDaoImpl", "selectById", __e);
            throw __e;
        }
    }

    @Override
    public java.util.List<net.shinkasystems.kintai.entity.User> selectByPartialUserName(java.lang.String userName) {
        entering("net.shinkasystems.kintai.entity.UserDaoImpl", "selectByPartialUserName", userName);
        try {
            org.seasar.doma.internal.jdbc.query.SqlFileSelectQuery __query = new org.seasar.doma.internal.jdbc.query.SqlFileSelectQuery();
            __query.setConfig(config);
            __query.setSqlFilePath("META-INF/net/shinkasystems/kintai/entity/UserDao/selectByPartialUserName.sql");
            __query.addParameter("userName", java.lang.String.class, userName);
            __query.setCallerClassName("net.shinkasystems.kintai.entity.UserDaoImpl");
            __query.setCallerMethodName("selectByPartialUserName");
            __query.setResultEnsured(false);
            __query.setQueryTimeout(-1);
            __query.setMaxRows(-1);
            __query.setFetchSize(-1);
            __query.prepare();
            org.seasar.doma.internal.jdbc.command.SelectCommand<java.util.List<net.shinkasystems.kintai.entity.User>> __command = new org.seasar.doma.internal.jdbc.command.SelectCommand<java.util.List<net.shinkasystems.kintai.entity.User>>(__query, new org.seasar.doma.internal.jdbc.command.EntityResultListHandler<net.shinkasystems.kintai.entity.User>(net.shinkasystems.kintai.entity._User.getSingletonInternal()));
            java.util.List<net.shinkasystems.kintai.entity.User> __result = __command.execute();
            __query.complete();
            exiting("net.shinkasystems.kintai.entity.UserDaoImpl", "selectByPartialUserName", __result);
            return __result;
        } catch (java.lang.RuntimeException __e) {
            throwing("net.shinkasystems.kintai.entity.UserDaoImpl", "selectByPartialUserName", __e);
            throw __e;
        }
    }

    @Override
    public net.shinkasystems.kintai.entity.User selectByUserName(java.lang.String userName) {
        entering("net.shinkasystems.kintai.entity.UserDaoImpl", "selectByUserName", userName);
        try {
            org.seasar.doma.internal.jdbc.query.SqlFileSelectQuery __query = new org.seasar.doma.internal.jdbc.query.SqlFileSelectQuery();
            __query.setConfig(config);
            __query.setSqlFilePath("META-INF/net/shinkasystems/kintai/entity/UserDao/selectByUserName.sql");
            __query.addParameter("userName", java.lang.String.class, userName);
            __query.setCallerClassName("net.shinkasystems.kintai.entity.UserDaoImpl");
            __query.setCallerMethodName("selectByUserName");
            __query.setResultEnsured(false);
            __query.setQueryTimeout(-1);
            __query.setMaxRows(-1);
            __query.setFetchSize(-1);
            __query.prepare();
            org.seasar.doma.internal.jdbc.command.SelectCommand<net.shinkasystems.kintai.entity.User> __command = new org.seasar.doma.internal.jdbc.command.SelectCommand<net.shinkasystems.kintai.entity.User>(__query, new org.seasar.doma.internal.jdbc.command.EntitySingleResultHandler<net.shinkasystems.kintai.entity.User>(net.shinkasystems.kintai.entity._User.getSingletonInternal()));
            net.shinkasystems.kintai.entity.User __result = __command.execute();
            __query.complete();
            exiting("net.shinkasystems.kintai.entity.UserDaoImpl", "selectByUserName", __result);
            return __result;
        } catch (java.lang.RuntimeException __e) {
            throwing("net.shinkasystems.kintai.entity.UserDaoImpl", "selectByUserName", __e);
            throw __e;
        }
    }

    @Override
    public long selectCountUser() {
        entering("net.shinkasystems.kintai.entity.UserDaoImpl", "selectCountUser");
        try {
            org.seasar.doma.internal.jdbc.query.SqlFileSelectQuery __query = new org.seasar.doma.internal.jdbc.query.SqlFileSelectQuery();
            __query.setConfig(config);
            __query.setSqlFilePath("META-INF/net/shinkasystems/kintai/entity/UserDao/selectCountUser.sql");
            __query.setCallerClassName("net.shinkasystems.kintai.entity.UserDaoImpl");
            __query.setCallerMethodName("selectCountUser");
            __query.setResultEnsured(false);
            __query.setQueryTimeout(-1);
            __query.setMaxRows(-1);
            __query.setFetchSize(-1);
            __query.prepare();
            org.seasar.doma.internal.jdbc.command.SelectCommand<java.lang.Long> __command = new org.seasar.doma.internal.jdbc.command.SelectCommand<java.lang.Long>(__query, new org.seasar.doma.internal.jdbc.command.BasicSingleResultHandler<java.lang.Long>(new org.seasar.doma.wrapper.LongWrapper(), true));
            long __result = __command.execute();
            __query.complete();
            exiting("net.shinkasystems.kintai.entity.UserDaoImpl", "selectCountUser", __result);
            return __result;
        } catch (java.lang.RuntimeException __e) {
            throwing("net.shinkasystems.kintai.entity.UserDaoImpl", "selectCountUser", __e);
            throw __e;
        }
    }

    @Override
    public java.util.List<net.shinkasystems.kintai.entity.sub.UserData> selectUserData(org.seasar.doma.jdbc.SelectOptions options) {
        entering("net.shinkasystems.kintai.entity.UserDaoImpl", "selectUserData", options);
        try {
            if (options == null) {
                throw new org.seasar.doma.DomaNullPointerException("options");
            }
            org.seasar.doma.internal.jdbc.query.SqlFileSelectQuery __query = new org.seasar.doma.internal.jdbc.query.SqlFileSelectQuery();
            __query.setConfig(config);
            __query.setSqlFilePath("META-INF/net/shinkasystems/kintai/entity/UserDao/selectUserData.sql");
            __query.setOptions(options);
            __query.setCallerClassName("net.shinkasystems.kintai.entity.UserDaoImpl");
            __query.setCallerMethodName("selectUserData");
            __query.setResultEnsured(false);
            __query.setQueryTimeout(-1);
            __query.setMaxRows(-1);
            __query.setFetchSize(-1);
            __query.prepare();
            org.seasar.doma.internal.jdbc.command.SelectCommand<java.util.List<net.shinkasystems.kintai.entity.sub.UserData>> __command = new org.seasar.doma.internal.jdbc.command.SelectCommand<java.util.List<net.shinkasystems.kintai.entity.sub.UserData>>(__query, new org.seasar.doma.internal.jdbc.command.EntityResultListHandler<net.shinkasystems.kintai.entity.sub.UserData>(net.shinkasystems.kintai.entity.sub._UserData.getSingletonInternal()));
            java.util.List<net.shinkasystems.kintai.entity.sub.UserData> __result = __command.execute();
            __query.complete();
            exiting("net.shinkasystems.kintai.entity.UserDaoImpl", "selectUserData", __result);
            return __result;
        } catch (java.lang.RuntimeException __e) {
            throwing("net.shinkasystems.kintai.entity.UserDaoImpl", "selectUserData", __e);
            throw __e;
        }
    }

    @Override
    public int update(net.shinkasystems.kintai.entity.User entity) {
        entering("net.shinkasystems.kintai.entity.UserDaoImpl", "update", entity);
        try {
            if (entity == null) {
                throw new org.seasar.doma.DomaNullPointerException("entity");
            }
            org.seasar.doma.internal.jdbc.query.AutoUpdateQuery<net.shinkasystems.kintai.entity.User> __query = new org.seasar.doma.internal.jdbc.query.AutoUpdateQuery<net.shinkasystems.kintai.entity.User>(net.shinkasystems.kintai.entity._User.getSingletonInternal());
            __query.setMethod(__method8);
            __query.setConfig(config);
            __query.setEntity(entity);
            __query.setCallerClassName("net.shinkasystems.kintai.entity.UserDaoImpl");
            __query.setCallerMethodName("update");
            __query.setQueryTimeout(-1);
            __query.setNullExcluded(false);
            __query.setVersionIncluded(false);
            __query.setVersionIgnored(false);
            __query.setIncludedPropertyNames();
            __query.setExcludedPropertyNames();
            __query.setUnchangedPropertyIncluded(false);
            __query.setOptimisticLockExceptionSuppressed(false);
            __query.prepare();
            org.seasar.doma.internal.jdbc.command.UpdateCommand __command = new org.seasar.doma.internal.jdbc.command.UpdateCommand(__query);
            int __result = __command.execute();
            __query.complete();
            exiting("net.shinkasystems.kintai.entity.UserDaoImpl", "update", __result);
            return __result;
        } catch (java.lang.RuntimeException __e) {
            throwing("net.shinkasystems.kintai.entity.UserDaoImpl", "update", __e);
            throw __e;
        }
    }

}
