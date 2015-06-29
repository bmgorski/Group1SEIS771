package application;

import java.util.List;

public class ReviewResourceModel {

	// private static Logger logger = Logger.getLogger(ReviewResourceModel.class);

	private SubmissionApplication submissionApplication;
	private User customer;
	private Animal animal;
	private List<Facility> facilities;

	public ReviewResourceModel(long id, SubmissionApplicationRepository submissionApplicationRepository, UserRepository userRepository, AnimalRepository animalRepository,
	        FacilityRepository facilityRepository) {
		setSubmissionApplication(submissionApplicationRepository.findOne(id));
		setCustomer(userRepository.findOne(submissionApplication.getCustomer_id()));
		setAnimal(animalRepository.findOne(submissionApplication.getAnimal_id())); // TODO Auto-generated constructor stub
		setFacilities(facilityRepository.findAll());
	}

	public SubmissionApplication getSubmissionApplication() {
		return submissionApplication;
	}

	public void setSubmissionApplication(SubmissionApplication submissionApplication) {
		this.submissionApplication = submissionApplication;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	public List<Facility> getFacilities() {
		return facilities;
	}

	public void setFacilities(List<Facility> facilities) {
		this.facilities = facilities;
	}

}
