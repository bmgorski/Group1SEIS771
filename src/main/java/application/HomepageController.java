package application;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({ "/", "homepage" })
public class HomepageController {
	@RequestMapping(method = RequestMethod.GET)
	public String createUser(Model model) {
		return "homepage";
	}
}
