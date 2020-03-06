package com.mysql.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.app.interfaces.IPerson;
import com.mysql.app.interfaces.service.IPersonService;
import com.mysql.app.models.Person;

@Service
public class PersonService implements IPersonService {

	@Autowired
	private IPerson iPerson;

	@Override
	public List<Person> findAll() {
		return (List<Person>) iPerson.findAll();
	}

	@Override
	public Optional<Person> findById(int id) {
		return iPerson.findById(id);
	}

	@Override
	public int save(Person p) {
		int res = 0;
		Person person = iPerson.save(p);
		if (!person.equals(null)) {
			res = 1;
		}
		return res;
	}

	@Override
	public void delete(int id) {
		iPerson.deleteById(id);
	}

}
