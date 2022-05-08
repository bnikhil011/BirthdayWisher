package com.example.services;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.example.model.StaticData;
import com.example.model.User;
/*
 * WishingService is the  Service which is used to wish BIrthday to users 
 * As soon as the Service is started it takes current date form the server and check is there any
 * user in list to which Birth date matches to today's date. 
 */
@Service
@Scope("singleton")
public class WishingService implements Runnable {
	



	@Override
	public void run() {
		System.out.println("SErvice Started");
		while(StaticData.startService) 
		{
			Date date = new Date();
			String d = (date.getMonth()+1)+"+"+date.getDate();
			Map <String,List<User>> data = StaticData.users;
			if(StaticData.users.get((date.getMonth()+1)+"+"+date.getDate())!=null)
			{
				List<User> users = StaticData.users.get((date.getMonth()+1)+"+"+date.getDate());
				for(User user : users)
				{
					System.out.println("Found One");

					Message message = createmessage(user);
					try {
						Transport.send(message);
						System.out.println("Sent SUcessfully");
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			}
			try {
				Thread.sleep(1000000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
	}

	
	public Message createmessage(User user)
	{
		Message message = null;
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "465");
		
		
		 
		
		try {
			
			Session session = Session.getInstance(prop, new Authenticator() {
		    @Override
		    protected PasswordAuthentication getPasswordAuthentication() {
		        return new PasswordAuthentication("abcd@gmail.com", "**********");
		    }
			});
		
			message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user.getFrom()));
			message.setRecipients(
			  Message.RecipientType.TO, InternetAddress.parse(user.getTo()));
			message.setSubject("Happy Birthday "+user.getName());
	
			String msg = user.getMessage();
	
			MimeBodyPart mimeBodyPart = new MimeBodyPart();
			mimeBodyPart.setContent(msg, "text/html; charset=utf-8");
	
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(mimeBodyPart);
	
			message.setContent(multipart);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				
			}
		
		
		return message;
	}
	
	
}
