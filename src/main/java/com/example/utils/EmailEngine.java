package com.example.utils;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class EmailEngine {

    public void sendEmail(){

		PropertiesManager pm = new PropertiesManager();
		Properties prop = pm.getProperties("src/main/resources/email.properties");

		String from = "from_email@gmail.com";
		String to = "to_email@gmail.com";
		String subject = "Email subject";
	
		Session session = Session.getInstance(prop,
				new javax.mail.Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(prop.getProperty("mail.username"), prop.getProperty("mail.password"));
					}
				});

		try {
			Message message = new MimeMessage(session);

			message.setFrom(new InternetAddress(from));
			message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(to));//RecipientType.TO / CC / BCC

			/* 
				NOTE: To send to more than one email duplicate: "message.addRecipients (...)"
			*/

			message.setSubject(subject);
			message.setContent("<h1>This is HTML message</h1>", "text/html;charset=UTF-8"); //to send HTML Content
			//message.setText("test text :)"); //to send plain text

			Transport.send(message);

			System.out.println("Email sent successfully!");//TODO: replace with logger
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}