package application;

import javax.mail.MessagingException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReviewSubmissionApplicationController {

	private static final String ACTION_APPROVE = "approve";
	private static final String ACTION_REJECT = "reject";
	private static final String ACTION_RECEIVE = "receive";

	Logger logger = Logger.getLogger(ReviewSubmissionApplicationController.class);

	@Autowired
	private SubmissionApplicationRepository submissionApplicationRepository;

	@Autowired
	private AnimalRepository animalRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FacilityRepository facilityRepository;

	@Autowired
	private SendMail mail;

	@RequestMapping(value = { "/review-intake-submission/" }, method = RequestMethod.GET)
	public String returnView(Model model) {
		return "view-intake-submissions";
	}

	@RequestMapping(value = { "/review-intake-submission/{id}" }, method = RequestMethod.GET)
	public String returnViewWithResource(Model model, @PathVariable Long id) {
		return "review-intake-submission";
	}

	@RequestMapping(value = { "/review-intake-submission/{id}" }, method = RequestMethod.POST)
	public String postReviewSubmissionApplication(@PathVariable Long id, @ModelAttribute ReviewResourceModel resourceModel, @RequestParam(defaultValue = "") String action,
	        @RequestParam(defaultValue = "0") Long facilityId) throws MessagingException {

		User customer = resourceModel.getCustomer();
		Animal animal = resourceModel.getAnimal();
		SubmissionApplication subApp = resourceModel.getSubmissionApplication();

		logger.debug("Action param: " + action);
		logger.debug("Facility id: " + facilityId);

		if (action.equals(ACTION_APPROVE)) {
			mail.sendAcceptamceMemo(customer, resourceModel.getAnimal());
			subApp.setApplication_status(SubmissionApplication.STATUS_APPROVED);
			submissionApplicationRepository.save(subApp);
		} else if (action.equals(ACTION_REJECT)) {
			mail.sendRejectionMemo(customer, resourceModel.getAnimal());
			subApp.setApplication_status(SubmissionApplication.STATUS_REJECTED);
			submissionApplicationRepository.save(subApp);
		} else if (action.equals(ACTION_RECEIVE)) {
			mail.sendAdmissionReceipt(customer, animal);
			subApp.setApplication_status(SubmissionApplication.STATUS_APPROVED_RECEIVED);
			submissionApplicationRepository.save(subApp);

			if (facilityId > 0) {
				animal.setFacility_id(facilityId.intValue());
				animalRepository.save(animal);
			}
		}

		return "redirect:/review-intake-submission/" + id;
	}

	@ModelAttribute
	public ReviewResourceModel getReviewResourceModel(@PathVariable Long id) {
		return new ReviewResourceModel(id, submissionApplicationRepository, userRepository, animalRepository, facilityRepository);
	}
}
