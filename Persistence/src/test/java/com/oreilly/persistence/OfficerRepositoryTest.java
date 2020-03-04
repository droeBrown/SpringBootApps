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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.oreilly.persistence.dao.OfficerRepository;
import com.oreilly.persistence.entities.Officer;
import com.oreilly.persistence.entities.Rank;

@SuppressWarnings({ "SqlNoDataSoureInspection", "SqlResolve" })
@SpringBootTest
@Transactional
@ExtendWith(SpringExtension.class)
class OfficerRepositoryTest {

	@Autowired
	private OfficerRepository dao;

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
		assertEquals(5, dao.count());
	}

	@Test
	public void delete() {
		template.query("select id from officers", idMapper).forEach(id -> {
			Optional<Officer> officer = dao.findById(id);
			assertTrue(officer.isPresent());
			dao.delete(officer.get());
		});
		assertEquals(0, dao.count());
	}

	@Test
	public void existsById() {
		template.query("select id from officers", idMapper).forEach(id -> assertTrue(dao.existsById(id)));

	}

	@Test
	public void doesNotExist() {
		List<Integer> ids = template.query("select id from officers", idMapper);
		// assertThat(ids,not(contains(999)));
		assertFalse(dao.existsById(999));
	}

	@Test
	public void findByLast() {
		List<Officer> kirks = dao.findByLast("Kirk");
		assertEquals(1, kirks.size());
		assertEquals("Kirk", kirks.get(0).getLast());
	}

	@Test
	public void findByRankAndLastLike() {
		List<Officer> officers = dao.findAllByRankAndLastLike(Rank.CAPTAIN, "%i%");
		System.out.println(officers);
		List<String> lastNames = officers.stream().map(Officer::getLast).collect(Collectors.toList());
		assertThat(lastNames, containsInAnyOrder("Kirk", "Picard", "Sisko"));
	}
}
