package net.shinkasystems.kintai;

import javax.sql.DataSource;

import org.seasar.doma.jdbc.DomaAbstractConfig;
import org.seasar.doma.jdbc.SimpleDataSource;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.H2Dialect;
import org.seasar.doma.jdbc.tx.LocalTransaction;
import org.seasar.doma.jdbc.tx.LocalTransactionalDataSource;

public class KintaiDB extends DomaAbstractConfig {

    protected static final LocalTransactionalDataSource DATA_SOURCE = createDataSource();

    protected static final Dialect DIALECT = new H2Dialect();

    @Override
    public DataSource getDataSource() {
            return DATA_SOURCE;
    }

    @Override
    public Dialect getDialect() {
            return DIALECT;
    }

    /**
     * データソースを生成します。
     * @return
     */
    protected static LocalTransactionalDataSource createDataSource() {
            SimpleDataSource dataSource = new SimpleDataSource();
            dataSource.setUrl("jdbc:h2:file:" + KintaiConstants.APP_DB_FILE.getAbsolutePath());
            dataSource.setUser("sa");
            
            return new LocalTransactionalDataSource(dataSource);
    }
    
    /**
     * ローカルトランザクションを取得します。
     * @return
     */
public static LocalTransaction getLocalTransaction() {
    return DATA_SOURCE.getLocalTransaction(defaultJdbcLogger);
}
}
