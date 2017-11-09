package com.codigo.aplios.toolbox.core;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmailTest {

	/**
	 * Outgoing Mail (SMTP) Server requires TLS or SSL: smtp.gmail.com (use authentication) Use Authentication: Yes Port
	 * for SSL: 465
	 */
	public static void main(String[] args) {

		System.out.println("SimpleEmail=> Start");

		String smtpHostServer = "smtp.gmail.com";
		String emailID = "ar.radziszewski@gmail.com";

		Properties props = System.getProperties();

		props.put("mail.smtp.host", smtpHostServer); // SMTP Host
		Session session = Session.getInstance(props, null);
		session.setDebug(true);

		SendEmailTest.sendEmail(session, emailID, "SimpleEmail Testing Subject", "SimpleEmail Testing Body");
	}

	// final String fromEmail = "ar.radziszewski@gmail.com"; // requires valid gmail id
	// final String password = "andrzej79#123"; // correct password for gmail id
	// final String toEmail = "Andrzej.Radziszewski@kdpw.pl"; // can be any email id
	//
	// System.out.println("SSLEmail Start");
	// Properties props = new Properties();
	// props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
	// props.put("mail.smtp.socketFactory.port", "465"); // SSL Port
	// props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // SSL Factory
	// Class
	// props.put("mail.smtp.auth", "true"); // Enabling SMTP Authentication
	// props.put("mail.smtp.port", "465"); // SMTP Port
	//
	// Authenticator auth = new Authenticator() {
	// // override the getPasswordAuthentication method
	// @Override
	// protected PasswordAuthentication getPasswordAuthentication() {
	//
	// return new PasswordAuthentication(fromEmail, password);
	// }
	// };
	//
	// Session session = Session.getDefaultInstance(props, auth);
	// session.setDebug(true);
	// System.out.println("Session created");
	// SendEmailTest.sendEmail(session, toEmail, "test długości tematu", "SSLEmail Testing Body");

	// SendEmailTest.sendAttachmentEmail(session, toEmail, "SSLEmail Testing Subject with Attachment",
	// "SSLEmail Testing Body with Attachment");
	//
	// SendEmailTest.sendImageEmail(session, toEmail, "SSLEmail Testing Subject with Image",
	// "SSLEmail Testing Body with Image");

	/**
	 * Utility method to send simple HTML email
	 *
	 * @param session
	 * @param toEmail
	 * @param subject
	 * @param body
	 */
	public static void sendEmail(Session session, String toEmail, String subject, String body) {

		try {
			MimeMessage msg = new MimeMessage(session);
			// set message headers
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");
			msg.setFrom(new InternetAddress("ar.radziszewski@gmail.com", "NoReply-JD"));
			msg.setReplyTo(InternetAddress.parse("Andrzej.Radziszewski@kdpw.pl", false));
			msg.setSubject(subject, "UTF-8");
			msg.setText(body, "UTF-8");
			msg.setSentDate(new Date());
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
			System.out.println("=> Message is ready");
			Transport.send(msg);

			System.out.println("EMail Sent Successfully!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
