/**
 * 
 */
package com.fixme.obs.config;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author muthu_m
 *
 */
public class SendEmail {

	public void sendEmail() {
		String from = "nirmal.biit@gmail.com";
		String password = "muthukumar@143";
		String to = "muthukumarnirmal@gmail.com";
		//Get properties object    
        Properties props = new Properties();    
        props.put("mail.smtp.host", "smtp.gmail.com");    
        props.put("mail.smtp.socketFactory.port", "465");    
        props.put("mail.smtp.socketFactory.class",    
                  "javax.net.ssl.SSLSocketFactory");    
        props.put("mail.smtp.auth", "true");    
        props.put("mail.smtp.port", "465");    
        //get Session   
        Session session = Session.getDefaultInstance(props,    
         new javax.mail.Authenticator() {    
         protected PasswordAuthentication getPasswordAuthentication() {    
         return new PasswordAuthentication(from,password);  
         }    
        });    
		
		// Compose the message
		try {
			MimeMessage message = new MimeMessage(session);    
	        message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
	        message.setSubject("Test Mail");
	        message.setText("This is test mail from Muthukumar M");
			//Send Message
			Transport.send(message);
			System.out.println("Message Send Successfully");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SendEmail sendEmail = new SendEmail();
		sendEmail.sendEmail();
	}

}
