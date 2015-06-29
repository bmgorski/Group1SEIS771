package application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SubmissionApplicationController {

	@Autowired
	private AnimalRepository animalRepository;

	@Autowired
	private Animal animal;

	@Autowired
	private SubmissionApplicationRepository submissionApplicationRepository;

	@Autowired
	private SubmissionApplication submissionApplication;

	@Autowired
	private UserRepository userRepositoryr;

	@Autowired
	private SendMail mail;

	@RequestMapping("/submit")
	public String returnView(Model model) {
		return "submit";
	}

	@RequestMapping(value = "/submit_application.html", method = RequestMethod.POST)
	public ModelAndView submitApplication(@ModelAttribute("animal") Animal animal, @ModelAttribute("submissionApplication") SubmissionApplication submissionApplication) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepositoryr.findByEmail(userDetails.getUsername()).get(0);

		ModelAndView model = new ModelAndView("submissionConfirmation");
		animalRepository.save(animal);

		submissionApplication.setAnimal_id(animal.getId());
		submissionApplication.setCustomer_id(user.getId());
		submissionApplication.setApplication_status(SubmissionApplication.STATUS_PENDING);
		submissionApplicationRepository.save(submissionApplication);
		return model;

	}
}
