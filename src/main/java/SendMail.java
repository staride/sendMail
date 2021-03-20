import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class SendMail {
    public static void main(String[] args) {

        try {

            Properties props = new Properties();
            props.setProperty("mail.transport.protocol", "smtp");
            props.setProperty("mail.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.auth", "true");
            props.setProperty("mail.smtp.port", "465");

            props.setProperty("mail.smtp.socketFactory.port", "465");
            props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            props.setProperty("mail.smtp.quitwait", "false");

            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("tmailAuth", "qwerty1231!");
                }
            });

            session.setDebug(true);

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("sendMailTest@test.com", "Test Sender"));
            message.addRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress("tmailAuth@gmail.com")});

            message.setSubject("테스트용 메일의 제목입니다.");

            Multipart mp = new MimeMultipart();

            BodyPart bp = new MimeBodyPart();
//            bp.setText("테스트용 메일의 내용입니다.");
            bp.setContent("<html><head></head><body>테스트 메시지 입니다<br></body></html>", "text/html; charset=utf-8");

            mp.addBodyPart(bp);
            message.setContent(mp);

            Transport.send(message);
            System.out.println("Message Send Success");

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
