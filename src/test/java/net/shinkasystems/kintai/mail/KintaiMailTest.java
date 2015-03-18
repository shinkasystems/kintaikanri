package net.shinkasystems.kintai.mail;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import net.shinkasystems.kintai.KintaiConstants;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.subethamail.smtp.auth.EasyAuthenticationHandlerFactory;
import org.subethamail.smtp.auth.LoginFailedException;
import org.subethamail.smtp.auth.UsernamePasswordValidator;
import org.subethamail.smtp.server.SMTPServer;
import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;

public class KintaiMailTest {

	Wiser wiser;
	
	
	@Before
	public void setUp() throws Exception {

		wiser = new Wiser(Integer.valueOf(KintaiMailPropery.PORT.getValue()));
		
		final SMTPServer smtpServer = wiser.getServer();
	    UsernamePasswordValidator myUsernamePasswordValidator = new UsernamePasswordValidator() {
	        @Override
	        public void login(String id, String pass) throws LoginFailedException {
	            if(id.equals(KintaiMailPropery.USER.getValue()) && pass.equals(KintaiMailPropery.PASSWORD.getValue())){
	                return;
	            }
	            throw new LoginFailedException();
	        }
	    };
	    smtpServer.setAuthenticationHandlerFactory(new EasyAuthenticationHandlerFactory(myUsernamePasswordValidator));
	    
		wiser.start();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {

		final KintaiMailArgument argument = new KintaiMailArgument();

		argument.setReceiverName("決裁者名");
		argument.setReceiverMailAddress("unit-test-authority@localhost");
		argument.setSenderName("申請者名");
		argument.setSenderMailAddress("unit-test-applicant@localhost");
		argument.setTerm(KintaiConstants.DATE_FORMAT.format(new Date()));
		argument.setForm("有給休暇");
		argument.setComment("私用のため");

		KintaiMail.ENTRY.send(argument);

		for (WiserMessage message : wiser.getMessages())
		{
			String envelopeSender = message.getEnvelopeSender();
			String envelopeReceiver = message.getEnvelopeReceiver();

			try {
				MimeMessage mimeMessage = message.getMimeMessage();
				
				System.out.println(mimeMessage.getSubject());
				
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
	}

}
