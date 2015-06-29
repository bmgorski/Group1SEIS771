package application;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
@Configurable
@PropertySource("classpath:mail.properties")
public class MailConfiguration {

	@Value("${mailSender.host}")
	private String host;

	@Value("${mailSender.port}")
	private Integer port;

	@Value("${mailSender.username}")
	private String username;

	@Value("${mailSender.password}")
	private String password;

	@Value("${mail.transport.protocol}")
	private String transportProtocol;

	@Value("${mail.smtp.auth}")
	private boolean smtpAuth;

	@Value("${mail.smtp.starttls.enable}")
	private boolean smtpStarttlsEnable;

	@Value("${mail.debug}")
	private boolean debug;

	@Bean
	public JavaMailSenderImpl javaMailSenderImpl() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(host);
		mailSender.setPort(port);
		mailSender.setUsername(username);
		mailSender.setPassword(password);

		Properties properties = mailSender.getJavaMailProperties();
		properties.put("mail.transport.protocol", transportProtocol);
		properties.put("mail.smtp.auth", smtpAuth);
		properties.put("mail.smtp.starttls.enable", smtpStarttlsEnable);
		properties.put("mail.debug", debug);

		return mailSender;
	}

	@Bean
	public ClassLoaderTemplateResolver emailTemplateResolver() {
		ClassLoaderTemplateResolver emailTemplateResolver = new ClassLoaderTemplateResolver();
		emailTemplateResolver.setPrefix("/mail/");
		emailTemplateResolver.setSuffix(".html");
		emailTemplateResolver.setTemplateMode("HTML5");
		emailTemplateResolver.setCharacterEncoding("UTF-8");
		emailTemplateResolver.setOrder(1);

		return emailTemplateResolver;
	}
}
