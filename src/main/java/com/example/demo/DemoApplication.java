package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

@SpringBootApplication
public class DemoApplication {

	public static void sendEmail(){
		final String username = "your_email@gmail.com";
		final String password = "your_secret_token";

		String from = "from_email@gmail.com";
		String to = "to_email@gmail.com";
		String subject = "Email subject";

		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");

		prop.put("mail.debug", "true");
		prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
	
		Session session = Session.getInstance(prop,
				new javax.mail.Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {
			Message message = new MimeMessage(session);

			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));//RecipientType.TO / CC / BCC
			message.setSubject(subject);
			message.setContent("<h1>This is HTML message</h1>", "text/html;charset=UTF-8"); //to send HTML Content
			//message.setText("test text :)"); //to send plain text

			Transport.send(message);

			System.out.println("Email sent successfully!");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		sendEmail();
		SpringApplication.run(DemoApplication.class, args);
	}
}