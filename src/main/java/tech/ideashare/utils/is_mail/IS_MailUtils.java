package tech.ideashare.utils.is_mail;

import com.sun.mail.smtp.SMTPTransport;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class IS_MailUtils {

    public  static void sendSimpleBySMTP(IS_MailConfig config,String subject , String text){

        Properties props = new Properties();
        props.put("mail.smtp.host", config.getHost());
        Session session = Session.getInstance(props, null);
        // create a message
        MimeMessage msg = new MimeMessage(session);
        try {

            msg.setFrom(new InternetAddress(config.getUserAddr()));
//            String[] s_address = config.getReceiverAddr();
//            InternetAddress[] address = new InternetAddress[20];
//            for (int i = 0; i < s_address.length; i++) {
//                address[i] = new InternetAddress(s_address[i]);
//            }
            for (InternetAddress address : config.getInternetAddresses()) {
                msg.addRecipient(Message.RecipientType.TO,address);
            }
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            // If the desired charset is known, you can use
            // setText(text, charset)
            msg.setText(text);
        } catch (MessagingException e) {
            e.printStackTrace();
        }


        try {
            SMTPTransport t = (SMTPTransport)session.getTransport("smtp");
            t.connect(config.getHost(),config.getPort(),config.getUserAddr(),config.getPassword());
            t.sendMessage(msg,msg.getAllRecipients());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        IS_MailConfig mailConfig = new IS_MailConfig("smtp.mxhichina.com",25,"xxxxx","xxxxx");
        mailConfig.setReceiverAddr("lixiang9409@vip.qq.com","weixiaodelixiang@gmail.com");
        sendSimpleBySMTP(mailConfig,"Mail Apis","hello this mail have been send to two mailAddr");
    }

}
