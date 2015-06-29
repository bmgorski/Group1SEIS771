package application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
public class AnimalListController {

	// @Autowired
	private List<Animal> animals;

	@Autowired
	private AnimalRepository animalRepository;

	@RequestMapping("/animal-list")
	public String returnView(Model model) {
		animals = animalRepository.findAll();
		System.out.println(animals.size());
		model.addAttribute("animals", animals);
		return "animal-list";
	}

}
