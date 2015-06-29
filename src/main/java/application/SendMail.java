package application;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@PropertySource("classpath:mail.properties")
public class SendMail {

	@Value("${mailSender.username}")
	private String from;

	@Autowired
	private JavaMailSenderImpl mailSender;

	@Autowired
	private TemplateEngine templateEngine;

	public void sendMail(String to, String subject, String msg) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(from);
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(msg);
		mailSender.send(simpleMailMessage);
	}

	public void sendRejectionMemo(final User user, final Animal animal) throws MessagingException {

		// Prepare the evaluation context
		final Context ctx = new Context();
		ctx.setVariable("user", user);
		ctx.setVariable("animal", animal);

		// Prepare message using a Spring helper
		final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
		final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
		message.setSubject("Your application for " + animal.getName() + " has been rejected");
		message.setFrom(from);
		message.setTo(user.getEmail());

		// Create the HTML body using Thymeleaf
		final String htmlContent = this.templateEngine.process("rejection-memo", ctx);
		message.setText(htmlContent, true);

		// Send email
		this.mailSender.send(mimeMessage);
	}

	public void sendAcceptamceMemo(final User user, final Animal animal) throws MessagingException {

		final String from = this.from;

		// Prepare the evaluation context
		final Context ctx = new Context();
		ctx.setVariable("user", user);
		ctx.setVariable("animal", animal);

		// Prepare message using a Spring helper
		final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
		final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
		message.setSubject("Your application for " + animal.getName() + " has been accepted");
		message.setFrom(from);

		message.setTo(user.getEmail());

		// Create the HTML body using Thymeleaf
		final String htmlContent = this.templateEngine.process("acceptance-memo", ctx);
		message.setText(htmlContent, true);

		// Send email
		this.mailSender.send(mimeMessage);
	}

	public void sendAdmissionReceipt(final User user, final Animal animal) throws MessagingException {

		final String from = this.from;

		// Prepare the evaluation context
		final Context ctx = new Context();
		ctx.setVariable("user", user);
		ctx.setVariable("animal", animal);

		// Prepare message using a Spring helper
		final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
		final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
		message.setSubject(animal.getName() + " is now located within one of our facilities");
		message.setFrom(from);

		message.setTo(user.getEmail());

		// Create the HTML body using Thymeleaf
		final String htmlContent = this.templateEngine.process("admission-receipt", ctx);
		message.setText(htmlContent, true);

		// Send email
		this.mailSender.send(mimeMessage);
	}
}
