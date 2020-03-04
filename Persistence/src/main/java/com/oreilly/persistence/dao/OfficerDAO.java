package com.oreilly.persistence.dao;

import java.util.List;
import java.util.Optional;

import com.oreilly.persistence.entities.Officer;

public interface OfficerDAO {

	Officer save(Officer office);

	Optional<Officer> findById(Integer id);

	List<Officer> findAll();

	long Count();

	void Delete(Officer office);

	boolean ExistById(Integer id);
}
