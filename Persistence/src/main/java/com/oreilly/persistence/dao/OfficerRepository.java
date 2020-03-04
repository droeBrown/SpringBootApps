package com.oreilly.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oreilly.persistence.entities.Officer;
import com.oreilly.persistence.entities.Rank;

public interface OfficerRepository extends JpaRepository<Officer, Integer> {
	List<Officer> findByLast(String last);

	List<Officer> findAllByRankAndLastLike(Rank rank, String last);
}
