package net.shinkasystems.kintai.util;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PasswordUtilityTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * 同じパスワードでもユーザーに応じて異なるハッシュを生成すること。
	 */
	@Test
	public void test01() {

		final String userID1 = "user1";
		final String userID2 = "user2";
		final String password1 = "password1";
		final String password2 = "password2";
		
		final PasswordUtility passwordUtility1 = new PasswordUtility(userID1, password1);
		final PasswordUtility passwordUtility2 = new PasswordUtility(userID2, password2);

		assertFalse(passwordUtility1.getPasswordHash().equals(passwordUtility2.getPasswordHash()));
	}

}
