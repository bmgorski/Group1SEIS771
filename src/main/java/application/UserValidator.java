package application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

	@Autowired
	private SecurityUtil securityUtil;

	@Override
	public boolean supports(Class<?> clazz) {
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;

		// if no id then know its a new user
		if (user.getId() == null || user.getId() <= 0) {
			// This is required and must pass our password requirements
			if (!securityUtil.validPassword(user.getPasswordHolder())) {
				errors.rejectValue("passwordHolder", null, "Invalid Password");
			}
		}
	}

}
