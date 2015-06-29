package application;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {

	public static final String VALID_SPECIAL_CHARACTERS = "/*!@#$%^&*()\"{}_[]|\\?/<>,.";
	// use Patter.quote to ensure safe for regex
	private static final String VALID_SPECIAL_CHARACTERS_QUOTED = Pattern.quote(VALID_SPECIAL_CHARACTERS);
	public static final int PASSWORD_MIN_LENGTH = 6;
	public static final int PASSWORD_MAX_LENGTH = 32;

	public static final String AUTHORITY_CUSTOMER = "customer";
	public static final String AUTHORITY_EMPLOYEE = "employee";
	public static final String AUTHORITY_VETERINARIAN = "veterinarian";

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * 
	 * @param password
	 * @return true if the following conditions are met:
	 * 
	 * @param password
	 *            is equal to or longer than {@value #PASSWORD_MIN_LENGTH}
	 * @param password
	 *            contains only A-Z upper or lower, numbers, and {@value #VALID_SPECIAL_CHARACTERS_QUOTED}
	 * @param password
	 *            has one or more upper case characters
	 * @param password
	 *            has one or more lower case character
	 * @param password
	 *            contains one or more {@value #VALID_SPECIAL_CHARACTERS_QUOTED}
	 * @param password
	 *            contains one or more numbers
	 * 
	 */
	public boolean validPassword(String password) {
		return passwordMeetsAllRequirments(password);
	}

	private boolean passwordMeetsAllRequirments(String password) {
		if (password == null || !validPasswordLength(password) || !containsOnlyValidCharacters(password)) {
			return false;
		}

		boolean hasUpperCase = false;
		boolean hasLowerCase = false;
		boolean hasSpecialCharacter = false;
		boolean hasNumber = false;

		for (char ch : password.toCharArray()) {
			hasUpperCase = changeToTrueOnly(hasUpperCase, isUpperCaseCharacter(ch));
			hasLowerCase = changeToTrueOnly(hasLowerCase, isLowerCaseCaracter(ch));
			hasSpecialCharacter = changeToTrueOnly(hasSpecialCharacter, isSpecialCharacter(ch));
			hasNumber = changeToTrueOnly(hasNumber, isDigit(ch));
		}

		return (hasUpperCase && hasLowerCase && hasSpecialCharacter && hasNumber);
	}

	public boolean containsOnlyValidCharacters(String password) {
		return password.matches("^[A-Za-z0-9" + VALID_SPECIAL_CHARACTERS_QUOTED + "]+$");
	}

	public boolean changeToTrueOnly(boolean currentValue, boolean eval) {
		if (eval) {
			currentValue = true;
		}
		return currentValue;
	}

	public boolean isSpecialCharacter(char ch) {
		return Character.toString(ch).matches("[" + VALID_SPECIAL_CHARACTERS_QUOTED + "]");
	}

	public boolean isDigit(char ch) {
		return Character.isDigit(ch);
	}

	public boolean isLowerCaseCaracter(char ch) {
		return Character.isLowerCase(ch);
	}

	public boolean isUpperCaseCharacter(char ch) {
		return Character.isUpperCase(ch);
	}

	public boolean validPasswordLength(String password) {
		if (password.length() < PASSWORD_MIN_LENGTH && password.length() <= PASSWORD_MAX_LENGTH) {
			return false;
		}
		return true;
	}

	public String encodePassword(String passwordHolder) {
		return passwordEncoder.encode(passwordHolder);
	}
}
