package com.JFSD.SDP.Miscellaneous;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;


@Service
public class ContactEmailSender {
 public void sendEmail()
 {
	 final String senderEmail = "carshowroomjava@gmail.com";
     final String senderPassword = "jxohhmezpevarfwz";
     String recipientEmail = "sunnysnivas@gmail.com";

     // Set up properties for the mail server
     Properties properties = new Properties();
     properties.put("mail.smtp.auth", "true");
     properties.put("mail.smtp.starttls.enable", "true");
     properties.put("mail.smtp.host", "smtp.gmail.com"); // e.g., smtp.gmail.com for Gmail
     properties.put("mail.smtp.port", "587"); // e.g., 587 for Gmail

     // Create a session with the properties and authenticator
     Session session = Session.getInstance(properties, new Authenticator() {
         @Override
         protected PasswordAuthentication getPasswordAuthentication() {
             return new PasswordAuthentication(senderEmail, senderPassword);
         }
     });

     try {
         // Create a message object
         Message message = new MimeMessage(session);

         // Set the sender and recipient addresses
         message.setFrom(new InternetAddress(senderEmail));
         message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));

         // Set the email subject and content
         message.setSubject("Test Email");
         message.setText("This is a test email sensdfvbghjksndc jksjdfh.");

         // Send the message
         Transport.send(message);

         System.out.println("Email sent successfully!");

     } catch (MessagingException e) {
         e.printStackTrace();
     }
 }
}