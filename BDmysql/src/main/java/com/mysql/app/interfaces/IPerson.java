package com.mysql.app.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mysql.app.models.Person;

@Repository
public interface IPerson extends CrudRepository<Person, Integer> {

}
