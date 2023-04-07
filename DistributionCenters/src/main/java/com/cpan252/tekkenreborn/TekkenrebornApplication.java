package com.cpan252.tekkenreborn;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class TekkenrebornApplication {

	@RequestMapping("/")
	public String home() {
		return "index";
	}

	@GetMapping("/hero")
	public String showForm(Model model) {
		Hero hero = new Hero();
		Hero.setName("Iron Man");
		model.addAttribute("hero", hero);

		return "form";
	}

	@PostMapping("/hero")
	public String submitForm(@Valid @ModelAttribute("hero") Hero hero,
							 BindingResult bindingResult, Model model) {
		System.out.println("Professing form...");
		System.out.println(hero);
			return "form";

	}
}

