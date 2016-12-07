package co.simplon.kif.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

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
}
