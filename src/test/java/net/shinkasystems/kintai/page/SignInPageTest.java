/**
 * 
 */
package net.shinkasystems.kintai.page;

import java.io.File;

import net.shinkasystems.kintai.KintaiDB;
import net.shinkasystems.kintai.KintaiWebApplication;
import net.shinkasystems.kintai.page.notification.IndexPage;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.csv.CsvDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Aogiri
 *
 */
public class SignInPageTest {

	/**
	 * Wicket のテスターです。
	 */
	private WicketTester wicketTester;

	/**
	 * データベースのテスターです。
	 */
	private IDatabaseTester databaseTester;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		/*
		 * Application
		 */
		KintaiDB.setMode(KintaiDB.TEST); // テストモードで DB 接続を行う
		KintaiDB.createDB();

		/*
		 * Wicket
		 */
		wicketTester = new WicketTester(new KintaiWebApplication());

		/*
		 * DBUnit
		 */
		databaseTester = new JdbcDatabaseTester(
				"org.h2.Driver",
				"jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
				"sa", // user
				null, // pass
				null // schema
		);

		databaseTester.setDataSet(new CsvDataSet(new File("src/test/resources/SignInPageTest")));
		databaseTester.setSetUpOperation(DatabaseOperation.INSERT);
		databaseTester.onSetup();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {

		/*
		 * Wicket
		 */
		wicketTester.destroy();

		/*
		 * DBUnit
		 */
		databaseTester.setTearDownOperation(DatabaseOperation.DELETE);
		databaseTester.onTearDown();
	}

	/**
	 * ログイン画面が表示されること。
	 */
	@Test
	public void testPageRender() {

		wicketTester.startPage(SignInPage.class);

		wicketTester.assertRenderedPage(SignInPage.class);
	}

	/**
	 * ログイン画面のコンポーネントが表示されること。
	 */
	@Test
	public void testComponentRender() {

		wicketTester.startPage(SignInPage.class);

		wicketTester.assertComponent("signin-form", Form.class);
		wicketTester.assertComponent("signin-form:user-name", TextField.class);
		wicketTester.assertComponent("signin-form:plane-password", PasswordTextField.class);
	}

	/**
	 * ログインに成功すること。
	 */
	@Test
	public void testSigninSuccess() {

		wicketTester.startPage(SignInPage.class);

		FormTester formTester = wicketTester.newFormTester("signin-form");

		formTester.setValue("user-name", "ut_user6");
		formTester.setValue("plane-password", "password");

		formTester.submit();

		wicketTester.assertRenderedPage(IndexPage.class);
	}

	/**
	 * ログインに失敗すること。
	 */
	@Test
	public void testSigninFail() {

		wicketTester.startPage(SignInPage.class);

		FormTester formTester = wicketTester.newFormTester("signin-form");

		formTester.setValue("user-name", "ut_user6");
		formTester.setValue("plane-password", "p@ssword");

		formTester.submit();

		wicketTester.assertErrorMessages(new String[] { "ユーザー名とパスワードが一致しません。" });
	}

	/**
	 * 必須チェック（ユーザー名）。
	 */
	@Test
	public void testSigninRequiredUsername() {

		wicketTester.startPage(SignInPage.class);

		FormTester formTester = wicketTester.newFormTester("signin-form");

		formTester.setValue("user-name", "");
		formTester.setValue("plane-password", "xxxxxxxx");

		formTester.submit();

		wicketTester.assertErrorMessages(new String[] { "'ユーザー名' 欄 は必須です。" });
	}

	/**
	 * 必須チェック（パスワードの両方）。
	 */
	@Test
	public void testSigninRequiredPassword() {

		wicketTester.startPage(SignInPage.class);

		FormTester formTester = wicketTester.newFormTester("signin-form");

		formTester.setValue("user-name", "xxxxxxxx");
		formTester.setValue("plane-password", "");

		formTester.submit();

		wicketTester.assertErrorMessages(new String[] { "'パスワード' 欄 は必須です。" });
	}

	/**
	 * 必須チェック（ユーザー名とパスワードの両方）。
	 */
	@Test
	public void testSigninRequiredUsernameAndPassword() {

		wicketTester.startPage(SignInPage.class);

		FormTester formTester = wicketTester.newFormTester("signin-form");

		formTester.setValue("user-name", "");
		formTester.setValue("plane-password", "");

		formTester.submit();

		wicketTester.assertErrorMessages(new String[] { "'ユーザー名' 欄 は必須です。", "'パスワード' 欄 は必須です。" });
	}

}
