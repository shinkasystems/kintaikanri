package net.shinkasystems.kintai.mail;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.Date;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * entry
 * approval
 * rejection
 * withdrawal
 * passback
 * 
 * @author Aogiri
 *
 */
public enum Mailer {

	/**
	 * 申請メールです。
	 */
	NOTIFICATION_ENTRY("【勤怠管理ツール】勤怠の申請"),

	/**
	 * 承認メールです。
	 */
	NOTIFICATION_APPROVAL("【勤怠管理ツール】勤怠の承認"),

	/**
	 * 却下メールです。
	 */
	NOTIFICATION_REJECTION("【勤怠管理ツール】勤怠の却下"),

	/**
	 * 取り下げメールです。
	 */
	NOTIFICATION_WITHDRAWAL("【勤怠管理ツール】勤怠の取り下げ");

	private static final String EMAIL_CHARSET = "UTF-8";

	/**
	 * 勤怠通知メールの件名です。
	 */
	private final String subject;

	/** ロガー */
	private static final Logger log = LoggerFactory.getLogger(Mailer.class);

	/**
	 * 
	 * @param subject
	 */
	private Mailer(String subject) {
		this.subject = subject;
	}

	/**
	 * メールを送信します。
	 * @param sender 差出人のメールアドレス
	 * @param receiver 宛先のメールアドレス
	 * @param arguments メールテンプレートのプレースホルダに代入する値
	 */
	public void send(String receiver, String sender, Object... arguments) {

		final String body = MessageFormat.format(this.getMailTemplate(), arguments);

		this.sendMail(receiver, sender, body);
	}

	/**
	 * メールのテンプレートファイルを読み込み文字列として返します。
	 * @return メールのテンプレート文字列
	 */
	private String getMailTemplate() {

		StringBuilder builder = new StringBuilder();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(
					getClass().getResourceAsStream(this.toString() + ".txt"), EMAIL_CHARSET));

			int c;
			while ((c = reader.read()) != -1) {
				builder.append((char) c);
			}

		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return builder.toString();
	}

	/**
	 * メールを送信します。
	 * @param sender 差出人のメールアドレス
	 * @param receiver 宛先のメールアドレス
	 * @param body メール本文
	 */
	private void sendMail(String receiver, String sender, String body) {

		final Session session = Session.getInstance(MailerProperty.PROPERTIES, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(
						MailerProperty.USER.getValue(),
						MailerProperty.PASSWORD.getValue());
			}
		});

		try {
			final MimeMessage message = new MimeMessage(session);
			message.setHeader("Content-Transfer-Encoding", "7bit");
			message.setRecipients(Message.RecipientType.TO, new InternetAddress[] { new InternetAddress(receiver) });
			message.setRecipients(Message.RecipientType.CC, new InternetAddress[] { new InternetAddress(sender) });
			message.setReplyTo(new InternetAddress[] { new InternetAddress(sender) });
			message.setFrom(new InternetAddress(MailerProperty.USER.getValue()));
			message.setSubject(subject, EMAIL_CHARSET);
			message.setSentDate(new Date());
			message.setContent(body, "text/plain;charset=" + EMAIL_CHARSET);

			Transport.send(message);

		} catch (MessagingException e) {

			log.error("メール送信中に例外が発生しました。" + e.getMessage());
			e.printStackTrace();
		}
	}
}
