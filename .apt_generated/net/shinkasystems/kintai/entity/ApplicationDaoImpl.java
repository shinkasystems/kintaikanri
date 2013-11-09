package net.shinkasystems.kintai.entity;

/** */
@javax.annotation.Generated(value = { "Doma", "1.31.0" }, date = "2013-11-09T23:08:16.416+0900")
public class ApplicationDaoImpl extends org.seasar.doma.internal.jdbc.dao.AbstractDao implements net.shinkasystems.kintai.entity.ApplicationDao {

    static {
        org.seasar.doma.internal.Artifact.validateVersion("1.31.0");
    }

    private static final java.lang.reflect.Method __method1 = org.seasar.doma.internal.jdbc.dao.AbstractDao.__getDeclaredMethod(net.shinkasystems.kintai.entity.ApplicationDao.class, "delete", net.shinkasystems.kintai.entity.Application.class);

    private static final java.lang.reflect.Method __method2 = org.seasar.doma.internal.jdbc.dao.AbstractDao.__getDeclaredMethod(net.shinkasystems.kintai.entity.ApplicationDao.class, "insert", net.shinkasystems.kintai.entity.Application.class);

    private static final java.lang.reflect.Method __method4 = org.seasar.doma.internal.jdbc.dao.AbstractDao.__getDeclaredMethod(net.shinkasystems.kintai.entity.ApplicationDao.class, "update", net.shinkasystems.kintai.entity.Application.class);

    /** */
    public ApplicationDaoImpl() {
        super(new net.shinkasystems.kintai.KintaiDB());
    }

    /**
     * @param connection the connection
     */
    public ApplicationDaoImpl(java.sql.Connection connection) {
        super(new net.shinkasystems.kintai.KintaiDB(), connection);
    }

    /**
     * @param dataSource the dataSource
     */
    public ApplicationDaoImpl(javax.sql.DataSource dataSource) {
        super(new net.shinkasystems.kintai.KintaiDB(), dataSource);
    }

    /**
     * @param config the configuration
     */
    protected ApplicationDaoImpl(org.seasar.doma.jdbc.Config config) {
        super(config);
    }

    /**
     * @param config the configuration
     * @param connection the connection
     */
    protected ApplicationDaoImpl(org.seasar.doma.jdbc.Config config, java.sql.Connection connection) {
        super(config, connection);
    }

    /**
     * @param config the configuration
     * @param dataSource the dataSource
     */
    protected ApplicationDaoImpl(org.seasar.doma.jdbc.Config config, javax.sql.DataSource dataSource) {
        super(config, dataSource);
    }

    @Override
    public void createTable() {
        entering("net.shinkasystems.kintai.entity.ApplicationDaoImpl", "createTable");
        try {
            org.seasar.doma.internal.jdbc.query.SqlFileScriptQuery __query = new org.seasar.doma.internal.jdbc.query.SqlFileScriptQuery();
            __query.setConfig(config);
            __query.setScriptFilePath("META-INF/net/shinkasystems/kintai/entity/ApplicationDao/createTable.script");
            __query.setCallerClassName("net.shinkasystems.kintai.entity.ApplicationDaoImpl");
            __query.setCallerMethodName("createTable");
            __query.setBlockDelimiter("");
            __query.setHaltOnError(true);
            __query.prepare();
            org.seasar.doma.internal.jdbc.command.ScriptCommand __command = new org.seasar.doma.internal.jdbc.command.ScriptCommand(__query);
            __command.execute();
            __query.complete();
            exiting("net.shinkasystems.kintai.entity.ApplicationDaoImpl", "createTable", null);
        } catch (java.lang.RuntimeException __e) {
            throwing("net.shinkasystems.kintai.entity.ApplicationDaoImpl", "createTable", __e);
            throw __e;
        }
    }

    @Override
    public int delete(net.shinkasystems.kintai.entity.Application entity) {
        entering("net.shinkasystems.kintai.entity.ApplicationDaoImpl", "delete", entity);
        try {
            if (entity == null) {
                throw new org.seasar.doma.DomaNullPointerException("entity");
            }
            org.seasar.doma.internal.jdbc.query.AutoDeleteQuery<net.shinkasystems.kintai.entity.Application> __query = new org.seasar.doma.internal.jdbc.query.AutoDeleteQuery<net.shinkasystems.kintai.entity.Application>(net.shinkasystems.kintai.entity._Application.getSingletonInternal());
            __query.setMethod(__method1);
            __query.setConfig(config);
            __query.setEntity(entity);
            __query.setCallerClassName("net.shinkasystems.kintai.entity.ApplicationDaoImpl");
            __query.setCallerMethodName("delete");
            __query.setQueryTimeout(-1);
            __query.setVersionIgnored(false);
            __query.setOptimisticLockExceptionSuppressed(false);
            __query.prepare();
            org.seasar.doma.internal.jdbc.command.DeleteCommand __command = new org.seasar.doma.internal.jdbc.command.DeleteCommand(__query);
            int __result = __command.execute();
            __query.complete();
            exiting("net.shinkasystems.kintai.entity.ApplicationDaoImpl", "delete", __result);
            return __result;
        } catch (java.lang.RuntimeException __e) {
            throwing("net.shinkasystems.kintai.entity.ApplicationDaoImpl", "delete", __e);
            throw __e;
        }
    }

    @Override
    public int insert(net.shinkasystems.kintai.entity.Application entity) {
        entering("net.shinkasystems.kintai.entity.ApplicationDaoImpl", "insert", entity);
        try {
            if (entity == null) {
                throw new org.seasar.doma.DomaNullPointerException("entity");
            }
            org.seasar.doma.internal.jdbc.query.AutoInsertQuery<net.shinkasystems.kintai.entity.Application> __query = new org.seasar.doma.internal.jdbc.query.AutoInsertQuery<net.shinkasystems.kintai.entity.Application>(net.shinkasystems.kintai.entity._Application.getSingletonInternal());
            __query.setMethod(__method2);
            __query.setConfig(config);
            __query.setEntity(entity);
            __query.setCallerClassName("net.shinkasystems.kintai.entity.ApplicationDaoImpl");
            __query.setCallerMethodName("insert");
            __query.setQueryTimeout(-1);
            __query.setNullExcluded(false);
            __query.setIncludedPropertyNames();
            __query.setExcludedPropertyNames();
            __query.prepare();
            org.seasar.doma.internal.jdbc.command.InsertCommand __command = new org.seasar.doma.internal.jdbc.command.InsertCommand(__query);
            int __result = __command.execute();
            __query.complete();
            exiting("net.shinkasystems.kintai.entity.ApplicationDaoImpl", "insert", __result);
            return __result;
        } catch (java.lang.RuntimeException __e) {
            throwing("net.shinkasystems.kintai.entity.ApplicationDaoImpl", "insert", __e);
            throw __e;
        }
    }

    @Override
    public net.shinkasystems.kintai.entity.Application selectById(java.lang.Integer id) {
        entering("net.shinkasystems.kintai.entity.ApplicationDaoImpl", "selectById", id);
        try {
            org.seasar.doma.internal.jdbc.query.SqlFileSelectQuery __query = new org.seasar.doma.internal.jdbc.query.SqlFileSelectQuery();
            __query.setConfig(config);
            __query.setSqlFilePath("META-INF/net/shinkasystems/kintai/entity/ApplicationDao/selectById.sql");
            __query.addParameter("id", java.lang.Integer.class, id);
            __query.setCallerClassName("net.shinkasystems.kintai.entity.ApplicationDaoImpl");
            __query.setCallerMethodName("selectById");
            __query.setResultEnsured(false);
            __query.setQueryTimeout(-1);
            __query.setMaxRows(-1);
            __query.setFetchSize(-1);
            __query.prepare();
            org.seasar.doma.internal.jdbc.command.SelectCommand<net.shinkasystems.kintai.entity.Application> __command = new org.seasar.doma.internal.jdbc.command.SelectCommand<net.shinkasystems.kintai.entity.Application>(__query, new org.seasar.doma.internal.jdbc.command.EntitySingleResultHandler<net.shinkasystems.kintai.entity.Application>(net.shinkasystems.kintai.entity._Application.getSingletonInternal()));
            net.shinkasystems.kintai.entity.Application __result = __command.execute();
            __query.complete();
            exiting("net.shinkasystems.kintai.entity.ApplicationDaoImpl", "selectById", __result);
            return __result;
        } catch (java.lang.RuntimeException __e) {
            throwing("net.shinkasystems.kintai.entity.ApplicationDaoImpl", "selectById", __e);
            throw __e;
        }
    }

    @Override
    public int update(net.shinkasystems.kintai.entity.Application entity) {
        entering("net.shinkasystems.kintai.entity.ApplicationDaoImpl", "update", entity);
        try {
            if (entity == null) {
                throw new org.seasar.doma.DomaNullPointerException("entity");
            }
            org.seasar.doma.internal.jdbc.query.AutoUpdateQuery<net.shinkasystems.kintai.entity.Application> __query = new org.seasar.doma.internal.jdbc.query.AutoUpdateQuery<net.shinkasystems.kintai.entity.Application>(net.shinkasystems.kintai.entity._Application.getSingletonInternal());
            __query.setMethod(__method4);
            __query.setConfig(config);
            __query.setEntity(entity);
            __query.setCallerClassName("net.shinkasystems.kintai.entity.ApplicationDaoImpl");
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
            exiting("net.shinkasystems.kintai.entity.ApplicationDaoImpl", "update", __result);
            return __result;
        } catch (java.lang.RuntimeException __e) {
            throwing("net.shinkasystems.kintai.entity.ApplicationDaoImpl", "update", __e);
            throw __e;
        }
    }

}
