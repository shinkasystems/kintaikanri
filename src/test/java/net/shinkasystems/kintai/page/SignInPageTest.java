/**
 * 
 */
package net.shinkasystems.kintai.page;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.Calendar;

import net.shinkasystems.kintai.KintaiDB;
import net.shinkasystems.kintai.KintaiRole;
import net.shinkasystems.kintai.entity.User;
import net.shinkasystems.kintai.entity.UserDao;
import net.shinkasystems.kintai.util.DaoFactory;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.seasar.doma.jdbc.tx.TransactionManager;

/**
 * @author Aogiri
 *
 */
public class SignInPageTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		Calendar expireCalendar = Calendar.getInstance();
		expireCalendar.add(Calendar.MONTH, 1);

		TransactionManager transactionManager = KintaiDB.singleton()
				.getTransactionManager();

		transactionManager.required(() -> {

			UserDao dao = DaoFactory.createDaoImplements(UserDao.class);

			User user = new User();
			user.setUserName("ut_admin");
			user.setPassword(DigestUtils.sha512Hex("ut_admin"));
			user.setDisplayName("管理者@UT");
			user.setAuthorityId(null);
			user.setActivated(true);
			user.setExpireDate(new Date(expireCalendar.getTimeInMillis()));
			user.setRole(KintaiRole.ADMIN);

			dao.insert(user);

			user.setAuthorityId(user.getId());

			dao.update(user);
		});
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {

		TransactionManager transactionManager = KintaiDB.singleton()
				.getTransactionManager();

		transactionManager.required(() -> {
			
			UserDao dao = DaoFactory.createDaoImplements(UserDao.class);

			{
				User user = dao.selectByUserName("ut_admin");

				dao.delete(user);
			}
		});
	}

	@Test
	public void test() {
		fail("まだ実装されていません");
	}

}
