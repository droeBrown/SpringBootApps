package com.youtube.service;

import java.util.Hashtable;

import org.springframework.stereotype.Service;

import com.youtube.model.Person;

@Service
public class PersonService {
	Hashtable<String, Person> persons = new Hashtable<String, Person>();

	public PersonService() {

		Person insertPersonInHash = new Person();

		insertPersonInHash.setId("1");
		insertPersonInHash.setAge(21);
		insertPersonInHash.setFirstName("Alexis");
		insertPersonInHash.setLastName("Tiscareño");

		persons.put("1", insertPersonInHash);

		insertPersonInHash = new Person();
		insertPersonInHash.setId("2");
		insertPersonInHash.setAge(21);
		insertPersonInHash.setFirstName("Droe");
		insertPersonInHash.setLastName("Brown");

		persons.put("2", insertPersonInHash);
	}

	public Person getPerson(String id) {
		if (persons.containsKey(id)) {
			return persons.get(id);
		} else {
			return null;
		}
	}

	public Hashtable<String, Person> getAll() {
		return persons;
	}
}
