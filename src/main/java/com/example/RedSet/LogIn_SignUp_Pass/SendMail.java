package com.example.RedSet.LogIn_SignUp_Pass;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public class SendMail {
    public static void sendEmail(String body, String subject, String email, String user) {
        String host="smtp.gmail.com";
        Properties properties=System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
//        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        try {
            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("clubcognita@gmail.com",
                            "anfx biyr bonz jjyc");
                }
            });
            session.setDebug(true);
            MimeMessage m = new MimeMessage(session);
            try {
                m.setFrom(new InternetAddress(user));
                m.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
                m.setSubject(subject);
                m.setText(body);
                Transport.send(m);
                System.out.println("Sent mail successfully...");
            } catch (Exception e) {
                System.out.println(e);
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
