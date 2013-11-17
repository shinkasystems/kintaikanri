package net.shinkasystems.kintai.mail;

import java.util.Date;

import net.shinkasystems.kintai.KintaiConstants;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class KintaiMailTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		
		final KintaiMailArgument argument = new KintaiMailArgument();
		
		argument.setReceiverName("決裁者名");
		argument.setReceiverMailAddress("********@********.net");
		argument.setSenderName("申請者名");
		argument.setSenderMailAddress("********@********.net");
		argument.setTerm(KintaiConstants.DATE_FORMAT.format(new Date()));
		argument.setForm("有給休暇");
		argument.setComment("私用のため");
		
		KintaiMail.ENTRY.send(argument);
	}

}
