package com.nationaldebtnotice.notice;


import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class Email {
   private Properties properties; 
   private Session mailSession; 
   private MimeMessage mailMessage; 
   private Transport trans; 
   
   private static String account;
   private static String pwd;
   private static String adminEmail;
   
   static{		
			ResourceBundle resource = ResourceBundle.getBundle("com/nationaldebtnotice/notice/emailconfig");		
			account=resource.getString("Admin.email.account").trim();
			pwd=resource.getString("Admin.email.pwd").trim();
			adminEmail=resource.getString("Admin.email").trim();			
   }
   
   public String sendMail(String email,String subject,String text) { 
        try { 
            properties = new Properties(); 
            /*
             * 163 
            //�����ʼ������� 
            properties.put("mail.smtp.host", "smtp.163.com"); 
            */
            final  String  SSL_FACTORY  =  "javax.net.ssl.SSLSocketFactory";
            properties.setProperty("mail.smtp.socketFactory.class",  SSL_FACTORY);
            properties.setProperty("mail.smtp.socketFactory.fallback",  "false");
            properties.setProperty("mail.smtp.host",  "smtp.gmail.com");
            properties.setProperty("mail.smtp.port",  "465");
            properties.setProperty("mail.smtp.socketFactory.port",  "465");
            //��֤ 
            properties.put("mail.smtp.auth", "true"); 
            
               
          //  properties.setProperty("mail.smtp.socketFactory.class",  SSL_FACTORY);
          //  properties.setProperty("mail.smtp.socketFactory.fallback",  "false");
          
         //   properties.setProperty("mail.smtp.socketFactory.port",  "465");
        

            //���������½�һ���ʼ��Ự 
            mailSession = Session.getInstance(properties, 
                                              new Authenticator() { 
                public PasswordAuthentication getPasswordAuthentication() { 
                    return new PasswordAuthentication(account, 
                        pwd); 
                } 
            }); 
            mailSession.setDebug(true); 
            //������Ϣ���� 
            mailMessage = new MimeMessage(mailSession); 
            //������ 
            mailMessage.setFrom(new InternetAddress(adminEmail)); 
            //�ռ��� 
            mailMessage.setRecipient(MimeMessage.RecipientType.TO, 
                                 new InternetAddress(email)); 
            //���� 
            mailMessage.setSubject(subject); 
            //���� 
            BodyPart bp=new MimeBodyPart();
            bp.setContent("<meta http-equiv=Context-Type context=text/html;charset=gb2312>"+text,"text/html;charset=GB2312");
            MimeMultipart multiMsg = new MimeMultipart();
            multiMsg.addBodyPart(bp);
            mailMessage.setContent(multiMsg);
            //����ʱ�� 
            mailMessage.setSentDate(new Date()); 
            //�洢��Ϣ 
            mailMessage.saveChanges(); 
            // 
            trans = mailSession.getTransport("smtp"); 
            //���� 
            trans.send(mailMessage); 
        }catch (javax.mail.SendFailedException sfe){
        	return sfe.getMessage();
        }
        catch (Exception e) { 
            return e.getMessage(); 
        } finally { 
        	;
        } 
        return "���ͳɹ�";
    } 
}
