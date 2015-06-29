package demo;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import application.Application;
import application.SecurityUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class SecurityUtilTest {

	@Autowired
	private SecurityUtil securityUtil;

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testValidPassword() {
		assertTrue(securityUtil.validPassword("Test1!"));
		assertFalse(securityUtil.validPassword(""));
		assertFalse(securityUtil.validPassword(null));
	}

	@Test
	public void testContainsOnlyValidCharacters() {
		// Space is an invalid character
		assertFalse(securityUtil.containsOnlyValidCharacters(" "));
		assertTrue(securityUtil.containsOnlyValidCharacters("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890" + SecurityUtil.VALID_SPECIAL_CHARACTERS));
	}

	@Test
	public void testChangeToTrueOnly() {
		assertFalse(securityUtil.changeToTrueOnly(false, false));
		assertTrue(securityUtil.changeToTrueOnly(false, true));
		assertTrue(securityUtil.changeToTrueOnly(true, false));
		assertTrue(securityUtil.changeToTrueOnly(true, true));
	}

	@Test
	public void testIsSpecialCharacter() {
		assertFalse(securityUtil.isSpecialCharacter("a".toCharArray()[0]));

		for (char t : SecurityUtil.VALID_SPECIAL_CHARACTERS.toCharArray()) {
			assertTrue(securityUtil.isSpecialCharacter(t));
		}
	}

	@Test
	public void testIsDigit() {
		assertFalse(securityUtil.isDigit("a".toCharArray()[0]));

		int[] iArray = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };

		for (int i : iArray) {
			char t = Integer.toString(i).toCharArray()[0];
			assertTrue(securityUtil.isDigit(t));
		}
	}

	@Test
	public void testIsLowerCaseCaracter() {
		assertFalse(securityUtil.isLowerCaseCaracter("A".toCharArray()[0]));

		char[] cArray = "abcdefghijklmnopqrstuvwxyz".toCharArray();

		for (char t : cArray) {
			assertTrue(securityUtil.isLowerCaseCaracter(t));
		}
	}

	@Test
	public void testIsUpperCaseCharacter() {
		assertFalse(securityUtil.isUpperCaseCharacter("a".toCharArray()[0]));

		char[] cArray = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

		for (char t : cArray) {
			assertTrue(securityUtil.isUpperCaseCharacter(t));
		}
	}

	@Test
	public void testValidPasswordLength() {

		int pmin = SecurityUtil.PASSWORD_MIN_LENGTH;
		int pmax = SecurityUtil.PASSWORD_MAX_LENGTH;

		String t = "";
		for (int i = 0; i <= pmax; i++) {

			// test pmin minus one
			if (pmin - 1 == i) {
				assertFalse(securityUtil.validPasswordLength(t));
			}
			if (pmax - 1 == i) {
				assertTrue(securityUtil.validPasswordLength(t));
			}

			// test pmin minus one
			if (pmin == i) {
				assertTrue(securityUtil.validPasswordLength(t));
			}
			if (pmax == i) {
				assertTrue(securityUtil.validPasswordLength(t));
			}

			// test pmin plus one
			if (pmin + 1 == i) {
				assertTrue(securityUtil.validPasswordLength(t));
			}
			if (pmax + 1 == i) {
				assertFalse(securityUtil.validPasswordLength(t));
			}

			t = t + "a";
		}

	}

}
