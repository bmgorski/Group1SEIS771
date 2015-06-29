package application;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("links")
public class LinksController {
	@RequestMapping(method = RequestMethod.GET)
	public String getLinks(Model model) {
		return "links";
	}
}
