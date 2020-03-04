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

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.oreilly.persistence.dao.OfficerDAO;
import com.oreilly.persistence.entities.Officer;
import com.oreilly.persistence.entities.Rank;

@SuppressWarnings({ "SqlNoDataSoureInspection", "SqlResolve" })
@SpringBootTest
@Transactional
@ExtendWith(SpringExtension.class)
class JpaOfficerDAOTest {

	@Autowired
	@Qualifier("jpaOfficerDAO")
	private OfficerDAO dao;

	@Autowired
	private JdbcTemplate template;
	private RowMapper<Integer> idMapper = (rs, num) -> rs.getInt("id");

	@Test
	public void testSave() {
		Officer officer = new Officer(Rank.LIEUTENAT, "Nyota", "Uhuru");
		officer = dao.save(officer);
		assertNotNull(officer.getId());
	}

	@Test
	public void findOneThatExist() {
		template.query("select id from officers", idMapper).forEach(id -> {
			Optional<Officer> officer = dao.findById(id);
			assertTrue(officer.isPresent());
			assertEquals(id, officer.get().getId());
		});
	}

	@Test
	public void findOneThatDoesNotExist() {
		Optional<Officer> officer = dao.findById(999);
		assertFalse(officer.isPresent());
	}

	@Test
	public void findAll() {
		List<String> dbNames = dao.findAll().stream().map(Officer::getLast).collect(Collectors.toList());
		assertThat(dbNames, containsInAnyOrder("Archer", "Janeway", "Kirk", "Picard", "Sisko"));
	}

	@Test
	public void count() throws Exception {
		assertEquals(5, dao.Count());
	}

	@Test
	public void delete() {
		template.query("select id from officers", idMapper).forEach(id -> {
			Optional<Officer> officer = dao.findById(id);
			assertTrue(officer.isPresent());
			dao.Delete(officer.get());
		});
		assertEquals(0, dao.Count());
	}

	@Test
	public void existsById() {
		template.query("select id from officers", idMapper).forEach(id -> assertTrue(dao.ExistById(id)));

	}

	@Test
	public void doesNotExist() {
		List<Integer> ids = template.query("select id from officers", idMapper);
		// assertThat(ids,not(contains(999)));
		assertFalse(dao.ExistById(999));
	}
}
