package com.youtube.controller;

import java.util.Hashtable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.youtube.model.Person;
import com.youtube.service.PersonService;

@RestController
public class PersonController {

	@Autowired
	PersonService ps;

	@RequestMapping("/all")
	public Hashtable<String, Person> getAll() {
		return ps.getAll();
	}

	@RequestMapping("/person/{id}")
	public Person getPerson(@PathVariable("id") String id) {
		return ps.getPerson(id);
	}
}
