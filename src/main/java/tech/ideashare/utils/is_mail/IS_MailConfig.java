package tech.ideashare.utils.is_mail;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;

public class IS_MailConfig {
    private String host;
    private int port;
    private String userAddr;
    private String[] receiverAddr;
    private List<InternetAddress> internetAddresses = new LinkedList<>();
    private String password;

    private MimeMessage message;

    public IS_MailConfig(String host, int port, String userAddr, String password,String... receiverAddr) {
        this.host = host;
        this.port = port;
        this.userAddr = userAddr;
        this.password = password;
        this.receiverAddr=receiverAddr;

    }

    public List<InternetAddress> getInternetAddresses() {
        return internetAddresses;
    }

    public void setInternetAddresses(String[] receiverAddr) {
        try{
            for (int i = 0; i < receiverAddr.length; i++) {
                this.internetAddresses.add(new InternetAddress(receiverAddr[i])) ;
            }
        }catch (AddressException e) {
            e.printStackTrace();
        }
    }

    public MimeMessage createMessage(Session session, String subject , String text){

        return message;
    }

    public String[] getReceiverAddr() {
        return receiverAddr;
    }

    public void setReceiverAddr(String... receiverAddr) {
        setInternetAddresses(receiverAddr);
        this.receiverAddr = receiverAddr;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUserAddr() {
        return userAddr;
    }

    public void setUserAddr(String userAddr) {
        this.userAddr = userAddr;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
