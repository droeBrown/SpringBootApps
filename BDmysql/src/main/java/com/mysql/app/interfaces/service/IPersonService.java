package com.mysql.app.interfaces.service;

import java.util.List;
import java.util.Optional;

import com.mysql.app.models.Person;

public interface IPersonService {
	public List<Person> findAll();

	public Optional<Person> findById(int id);

	public int save(Person p);

	public void delete(int id);
}
