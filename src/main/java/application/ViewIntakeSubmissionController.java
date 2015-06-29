package application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewIntakeSubmissionController {

	@Autowired
	private AnimalRepository animalRepository;

	@Autowired
	private User user;

	@Autowired
	private SubmissionApplicationRepository submissionApplicationRepository;

	@Autowired
	private List<SubmissionApplication> submissionApplications;

	@RequestMapping("/view-intake-submissions")
	public String returnView(Model model) {
		submissionApplications = submissionApplicationRepository.findAll();
		model.addAttribute("submissionApplications", submissionApplications);
		return "view-intake-submissions";
	}
}
