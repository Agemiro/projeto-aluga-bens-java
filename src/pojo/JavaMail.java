package pojo;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMail {		
	
	private String emailRemetente = "agemiro.alugabens.recuperarsenha@gmail.com";
    private String senhaRemetente = "alugabensagemiro";
	
	public void enviarEmail(String destinatario, String assunto, String mensagem){
	           
	    Properties props = new Properties();
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.socketFactory.port", "465");
	    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.port", "465");
	       
	    Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                      return new PasswordAuthentication(emailRemetente, senhaRemetente);
	            }
	    });
	       
	    session.setDebug(true);
	       
	    try {
	       
	          Message message = new MimeMessage(session);
	          message.setFrom(new InternetAddress(emailRemetente));
	       
	          Address[] toUser = InternetAddress.parse(destinatario);  
	       
	          message.setRecipients(Message.RecipientType.TO, toUser);
	          message.setSubject(assunto);
	          message.setText(mensagem);
	          Transport.send(message);
	       
	    }catch (MessagingException e) {}
	}
}


