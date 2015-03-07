package net.shinkasystems.kintai;

import javax.sql.DataSource;

import org.seasar.doma.SingletonConfig;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.H2Dialect;
import org.seasar.doma.jdbc.tx.LocalTransactionDataSource;
import org.seasar.doma.jdbc.tx.LocalTransactionManager;
import org.seasar.doma.jdbc.tx.TransactionManager;

@SingletonConfig
public class KintaiDB implements Config {

	private static final KintaiDB CONFIG = new KintaiDB();

	private final Dialect dialect;

	private final LocalTransactionDataSource dataSource;

	private final TransactionManager transactionManager;

	private KintaiDB() {
		super();

		dialect = new H2Dialect();

		dataSource = new LocalTransactionDataSource("jdbc:h2:file:"
				+ KintaiConstants.APP_DB_FILE.getAbsolutePath(), "sa", null);

		transactionManager = new LocalTransactionManager(dataSource.getLocalTransaction(getJdbcLogger()));
	}

	@Override
	public Dialect getDialect() {
		return dialect;
	}

	@Override
	public DataSource getDataSource() {
		return dataSource;
	}

	@Override
	public TransactionManager getTransactionManager() {
		return transactionManager;
	}

	public static KintaiDB singleton() {
		return CONFIG;
	}
}
