package com.example.repeat4;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Properties;
import java.util.Random;

import javax.activation.DataHandler;
import javax.imageio.ImageIO;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.web.bind.annotation.RequestBody;

import com.google.zxing.common.BitMatrix;
public class CheckClass
{
public String sendmail(User_Data user_data)
{
	   //New_Class new_class=Myrepo_2.findByIDandKey(user_data.getClient_id(),user_data.getKey_gen());
	   String result=""; 
	   //if(new_class!=null)
	   //{
		   byte [] arr=null;
		   ByteArrayDataSource bds = new ByteArrayDataSource(arr, "image/png"); 
		   final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		   // Get a Properties object
		      Properties props = System.getProperties();
		      props.setProperty("mail.smtp.host", "smtp.gmail.com");
		      props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		      props.setProperty("mail.smtp.socketFactory.fallback", "false");
		      props.setProperty("mail.smtp.port", "465");
		      props.setProperty("mail.smtp.socketFactory.port", "465");
		      props.put("mail.smtp.auth", "true");
		      props.put("mail.debug", "true");
		      props.put("mail.store.protocol", "pop3");
		      props.put("mail.transport.protocol", "smtp");
		      final String username = "chirayumaheshwari5@gmail.com";
		      final String password = "mockinggrid1234@";
		      try {
		     	 Session session = Session.getDefaultInstance(props, 
		                  new Authenticator(){
		                     protected PasswordAuthentication getPasswordAuthentication() {
		                        return new PasswordAuthentication(username, password);
		                     }});

		      

		          Message message = new MimeMessage(session);
		          message.setFrom(new InternetAddress("chirayumaheshwari5@gmail.com"));
		          message.setRecipients(Message.RecipientType.TO,
		             InternetAddress.parse(user_data.getEmail()));
		          InternetAddress internetAddress = new InternetAddress(user_data.getEmail());
		            internetAddress.validate();
		            
		           
		          message.setSubject("Testing Subject");
		          MimeMultipart multipart = new MimeMultipart("related");
		          BodyPart messageBodyPart = new MimeBodyPart();  
		          messageBodyPart.setDataHandler(new DataHandler(bds));
		          messageBodyPart.setHeader("Content-ID", "<image>");
		          messageBodyPart.setFileName("image");
		          multipart.addBodyPart(messageBodyPart);
		          message.setContent(multipart);
		          Transport.send(message);
		          System.out.println("Sent message successfully....");

		       } catch (MessagingException e) {
		          //throw new RuntimeException(e);
		    	   result="check your supplied email address";
		    	   return result;
		   }
	   //}
	   /*else
	   {
		   result="Check your client_id or Key";
		   return result;
	   }*/
	   //System.out.println(new_class);
	   
	   return "Successful";
}

}
