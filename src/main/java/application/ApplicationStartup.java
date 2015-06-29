package application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {

	public static final String MASTER_USER_EMAIL = "master@user.com";
	public static final String MASTER_USER_PASSWORD = "Test1!";

	@Autowired
	private AuthorityRepository authorityRepository;

	@Autowired
	private SecurityUtil securityUtil;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SendMail sendMail;

	@Override
	public void onApplicationEvent(final ContextRefreshedEvent event) {

		User user = userRepository.findByEmail(MASTER_USER_EMAIL).get(0);
		user.setPassword(securityUtil.encodePassword(MASTER_USER_PASSWORD));

		userRepository.save(user);

		// sendMail.sendMail("bmgorski987@gmail.com", "Hi", "you got a message from an application test");

		return;
	}
}