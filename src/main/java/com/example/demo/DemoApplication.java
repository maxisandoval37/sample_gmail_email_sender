package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.mail.*;
import javax.mail.internet.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

@SpringBootApplication
public class DemoApplication {
	
	private static Properties getProperties(String propertiesSrc){
		Properties prop1 = new Properties();

		try (InputStream input = new FileInputStream(propertiesSrc)) {
            prop1.load(input);
        } catch (Exception ex) {
			System.err.println("Fail to load email properties");
        }

		return prop1;
	}

	public static void sendEmail(){

		Properties prop = getProperties("src/main/resources/email.properties");

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
		SpringApplication.run(DemoApplication.class, args);
		sendEmail();
	}
}