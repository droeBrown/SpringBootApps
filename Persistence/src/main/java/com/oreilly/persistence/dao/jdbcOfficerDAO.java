package com.oreilly.persistence.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.oreilly.persistence.entities.Officer;
import com.oreilly.persistence.entities.Rank;

@SuppressWarnings({ "SqlResolve", "SqlNoDataSourceInspection", "ConstantConditions" })
@Repository
public class jdbcOfficerDAO implements OfficerDAO {

	private JdbcTemplate JdbcTemplate;
	private SimpleJdbcInsert insertOfficer;

	private RowMapper<Officer> officerMapper = (rs, rowNum) -> new Officer(rs.getInt("id"),
			Rank.valueOf(rs.getString("rank")), rs.getString("first_name"), rs.getString("last_name"));

	public jdbcOfficerDAO(JdbcTemplate jdbcTemplate) {
		this.JdbcTemplate = jdbcTemplate;
		this.insertOfficer = new SimpleJdbcInsert(jdbcTemplate).withTableName("officers")
				.usingGeneratedKeyColumns("id");
	}

	@Override
	public Officer save(Officer office) {

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("rank", office.getRank());
		parameters.put("first_name", office.getFirst());
		parameters.put("last_name", office.getLast());
		Integer newId = (Integer) insertOfficer.executeAndReturnKey(parameters);
		office.setId(newId);

		return office;
	}

	@Override
	public Optional<Officer> findById(Integer id) {

		if (!ExistById(id))
			return Optional.empty();
		return Optional.ofNullable(JdbcTemplate.queryForObject("SELECT * FROM officers WHERE id=?", officerMapper, id));
	}

	@Override
	public List<Officer> findAll() {

		return JdbcTemplate.query("SELECT * FROM officers", officerMapper);
	}

	@Override
	public long Count() {
		return JdbcTemplate.queryForObject("select count(*) from officers", Long.class);
	}

	@Override
	public void Delete(Officer office) {
		JdbcTemplate.update("DELETE FROM officers WHERE id=?", office.getId());
	}

	@Override
	public boolean ExistById(Integer id) {
		return JdbcTemplate.queryForObject("SELECT EXISTS(SELECT 1 FROM officers WHERE id=?)", Boolean.class, id);
	}

}
