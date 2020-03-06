package com.mysql.app.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysql.app.interfaces.service.IPersonService;
import com.mysql.app.models.Person;

@Controller
@RequestMapping
public class PersonController {

	@Autowired
	private IPersonService service;

	@GetMapping("/persons")
	public String finAllPersons(Model model) {
		List<Person> persons = service.findAll();
		model.addAttribute("persons", persons);
		return "index";
	}

	@GetMapping("/new")
	public String add(Model model) {
		model.addAttribute("person", new Person());
		return "form";
	}

	@PostMapping("/save")
	public String save(@Valid Person p, Model model) {
		service.save(p);
		return "redirect:/persons";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id, Model model) {
		Optional<Person> person = service.findById(id);
		model.addAttribute("person", person);
		return "form";
	}

	@GetMapping("/delete/{id}")
	public String delete(Model model, @PathVariable int id) {
		service.delete(id);
		return "redirect:/persons";
	}
}
