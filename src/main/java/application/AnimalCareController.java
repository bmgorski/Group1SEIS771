package application;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AnimalCareController {

	@Autowired
	private AnimalRepository animalRepository;

	@Autowired
	private Animal animal;

	@Autowired
	private AnimalCareRepository animalCareRepository;

	@Autowired
	private AnimalCare animalCare;

	@RequestMapping(value = "/animal-care/{id}", method = RequestMethod.GET)
	public String returnView(Model model) {
		return "animal-care";
	}

	@RequestMapping(value = "/animal-care/{id}", method = RequestMethod.POST)
	public String updateAnimalCare(@PathVariable long id, @ModelAttribute("animalCare") AnimalCare animalCare, BindingResult result, Model model) {
		System.out.println("started updateAnimalCare");
		if (result.hasErrors()) {
			System.out.println("there were some errors");
			return "animal-care";
		} else {
			animalCare.setAnimal_id(id);
			animalCareRepository.save(animalCare);
			System.out.println("executed animalCareRepository.save");
			// return "redirect:/animal-care/" + id; //for reference. redirect to same animal care record.
			return "redirect:/animal-list";
		}
	}

	@ModelAttribute
	public AnimalCare getRecord(@PathVariable Map<String, String> pathVariables) {
		return new AnimalCare();

	}

	@ModelAttribute
	public Animal getAnimalRecord(@PathVariable Map<String, String> pathVariables) {
		return animalRepository.getOne(Long.parseLong(pathVariables.get("id")));
	}

}
