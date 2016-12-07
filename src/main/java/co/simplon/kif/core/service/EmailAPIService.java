package co.simplon.kif.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import co.simplon.kif.core.model.Message;
import co.simplon.kif.core.model.Reply;

@Service
public class EmailAPIService {
	@Autowired
	private MailSender mailSender;

	public void sendEmail(String toAddress, String fromAddress, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(fromAddress);
		message.setTo(toAddress);
		message.setSubject(subject);
		message.setText(body);
		mailSender.send(message);
	}
	
	// Get informations and send mail to user
	public boolean sendReplyMail(Message message, Reply reply) {
		if (message == null || reply == null) {
			return false;
		}
		String toAddress = message.getEmail();
		String fromAddress = "simplon.kif@gmail.com";
		String subject = "RE: " + message.getTitle();
		String body = "Un administrateur a répondu à votre demande : " + message.getTitle() + ".\n" + reply.getContent();
		sendEmail(toAddress, fromAddress, subject, body);
		return true;
	}
	
	// Send mail to administrator
	public boolean sendMailToAdmin(Message message) {
		if (message == null) {
			return false;
		}
		String toAddress = "simplon.kif@gmail.com";
		String fromAddress = message.getEmail();
		String subject = "Nouvelle requête utilisateur : " + message.getTitle();
		String body = "Un utilisateur a envoyé une requête : " + message.getTitle() + ".\n" + message.getContent();
		sendEmail(toAddress, fromAddress, subject, body);
		return true;
	}

	// Send confirmation mail to user
	public boolean sendConfirmationMail(Message message) {
		if (message == null) {
			return false;
		}
		String toAddress = message.getEmail();
		String fromAddress = "simplon.kif@gmail.com";
		String subject = "Votre demande : " + message.getTitle() + " a bien été envoyée.";
		String body = "Votre demande a bien été envoyée : " + message.getTitle() + ".\n" + message.getContent();
		sendEmail(toAddress, fromAddress, subject, body);
		return true;
	}
}
