package com.oreilly.persistence;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.oreilly.persistence.dao.OfficerDAO;
import com.oreilly.persistence.entities.Officer;
import com.oreilly.persistence.entities.Rank;

@SuppressWarnings({ "SqlNoDataSourceInspection", "SqlResolve" })
@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
public class JdbcOfficerDAOTests {

	@Autowired
	@Qualifier("jpaOfficerDAO")
	private OfficerDAO dao;

	@Test
	public void save() {
		Officer office = new Officer(Rank.ESIGN, "Mario", "Garcia");
		office = dao.save(office);
		assertNotNull(office.getId());
	}

	@Test
	public void findByIdThatExists() {
		Optional<Officer> officer = dao.findById(1);
		assertTrue(officer.isPresent());
		assertEquals(1, officer.get().getId().intValue());
	}

	@Test
	public void findByIdThatDoesNotExists() {
		Optional<Officer> officer = dao.findById(999);
		assertFalse(officer.isPresent());
	}

	@Test
	public void count() {
		assertEquals(5, dao.Count());
	}

	@Test
	public void findAll() {
		List<String> dbNames = dao.findAll().stream().map(Officer::getFirst).collect(Collectors.toList());
		assertThat(dbNames, containsInAnyOrder("Goretty", "Rolando", "Adriana", "David", "JuanCarlos"));
	}

	@Test
	public void delete() {
		IntStream.rangeClosed(1, 5).forEach(id -> {
			Optional<Officer> officer = dao.findById(id);
			assertTrue(officer.isPresent());
			dao.Delete(officer.get());
		});
	}

	@Test
	public void existsById() {
		IntStream.rangeClosed(1, 5).forEach(id -> assertTrue(dao.ExistById(id)));
	}

}
