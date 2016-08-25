/**
 * 
 */
package net.shinkasystems.kintai;

import javax.sql.DataSource;

import net.shinkasystems.kintai.entity.NotificationDao;
import net.shinkasystems.kintai.entity.UserDao;
import net.shinkasystems.kintai.util.DaoFactory;

import org.seasar.doma.SingletonConfig;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.H2Dialect;
import org.seasar.doma.jdbc.tx.LocalTransactionDataSource;
import org.seasar.doma.jdbc.tx.LocalTransactionManager;
import org.seasar.doma.jdbc.tx.TransactionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 勤怠管理ツールの JDBC に関する設定です。
 * @author Aogiri
 *
 */
@SingletonConfig
public enum KintaiDB implements Config {

	/**
	 * 開発向けの設定です。
	 * 組み込みモードでデータベースファイルにアクセスします。
	 */
	DEVELOPMENT(
			new LocalTransactionDataSource(
					"jdbc:h2:file:~/kintai_data/kintai",
					"sa",
					null)
	),

	/**
	 * テスト向けの設定です。
	 * テスト時（Eclipse の Junit、Gradle test）は H2 Database のインメモリデータベースを使用します。
	 */
	TEST(
			new LocalTransactionDataSource(
					"jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
					"sa",
					null)
	),

	/**
	 * 運用向けの設定です。
	 * サーバーモードでデータベースファイルへアクセスします。
	 */
	DEPLOYMENT(
			new LocalTransactionDataSource(
					"jdbc:h2:tcp://localhost:9092/~/kintai_data/kintai",
					"sa",
					null)
	);

	/**
	 * RDBMSの方言です。
	 */
	private final Dialect dialect;

	/**
	 * データソースです。
	 */
	private final LocalTransactionDataSource dataSource;

	/**
	 * トランザクションの管理クラスです。
	 */
	private final TransactionManager transactionManager;

	/**
	 * モードです。
	 * デフォルトは DEVELOPMENT です。
	 */
	private static KintaiDB mode = null;

	/** ロガー */
	private static final Logger log = LoggerFactory.getLogger(KintaiDB.class);

	/**
	 * コンストラクタです。
	 */
	private KintaiDB(LocalTransactionDataSource dataSource) {

		this.dialect = new H2Dialect();
		this.dataSource = dataSource;
		this.transactionManager = new LocalTransactionManager(dataSource.getLocalTransaction(getJdbcLogger()));
	}

	@Override
	public DataSource getDataSource() {
		return this.dataSource;
	}

	@Override
	public Dialect getDialect() {
		return this.dialect;
	}

	@Override
	public TransactionManager getTransactionManager() {
		return this.transactionManager;
	}

	/**
	 * DB を作成します。
	 */
	public static void createDB() {

		singleton().transactionManager.required(() -> {

			{
				UserDao dao = DaoFactory.createDaoImplements(UserDao.class);
				dao.createTable();
			}
			{
				NotificationDao dao = DaoFactory.createDaoImplements(NotificationDao.class);
				dao.createTable();
			}

		});
	}

	/**
	 * KintaiDB の唯一のインスタンスを返します。
	 * @return KintaiDB
	 */
	public static KintaiDB singleton() {
		return mode;
	}

	/**
	 * モードを取得します。
	 * @return mode JDBC 設定モード
	 */
	public static KintaiDB getMode() {
		return mode;
	}

	/**
	 * モードを設定します。
	 * @param mode JDBC 設定モード
	 */
	public static void setMode(KintaiDB mode) {
		KintaiDB.mode = mode;

		log.info("JDBC設定モードを *{}* に変更しました。", singleton().toString());
		;
	}
}
