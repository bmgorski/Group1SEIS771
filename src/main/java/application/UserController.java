package application;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthorityRepository authorityRepository;

	@Autowired
	private User user;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private SecurityUtil securityUtil;

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	@PreAuthorize("#u.email == principal.username")
	public String viewUser(@P("u") User user, Model model) {
		return "user";
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String createUser(Model model) {
		return "user";
	}

	@RequestMapping(value = { "/user/{id}", "/user" }, method = RequestMethod.POST)
	public String updateUser(@ModelAttribute("user") User user, BindingResult result, Model model) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		userValidator.validate(user, result);

		if (result.hasErrors()) {
			return "user";
		} else {
			user.setPassword(securityUtil.encodePassword(user.getPasswordHolder()));

			userRepository.save(user);

			Authority authority2 = new Authority();
			authority2.setAuthority(SecurityUtil.AUTHORITY_CUSTOMER);
			authority2.setEmail(user.getEmail());

			authorityRepository.save(authority2);

			return "redirect:/user/" + user.getId();
		}
	}

	@ModelAttribute
	public User getAccount(@PathVariable Map<String, String> pathVariables) {
		if (pathVariables.containsKey("id")) {
			return userRepository.getOne(Long.parseLong(pathVariables.get("id")));
		}

		return new User();
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		if (request.getMethod().equals("POST")) {
			dataBinder.setRequiredFields(new String[] { "first_name", "last_name", "email", "street", "city", "state", "zip", "phone" });

			dataBinder.setAllowedFields(new String[] { "first_name", "last_name", "email", "street", "city", "state", "zip", "phone", "passwordHolder" });
		}
	}

}
