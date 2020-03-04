package com.oreilly.persistence.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.oreilly.persistence.entities.Officer;

@SuppressWarnings("JpaQlInspection")
@Repository
public class jpaOfficerDAO implements OfficerDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Officer save(Officer office) {
		entityManager.persist(office);
		return office;
	}

	@Override
	public Optional<Officer> findById(Integer id) {
		return Optional.ofNullable(entityManager.find(Officer.class, id));
	}

	@Override
	public List<Officer> findAll() {
		return entityManager.createQuery("select o from Office o", Officer.class).getResultList();
	}

	@Override
	public long Count() {
		return entityManager.createQuery("select count(o.id) from Office o", Long.class).getSingleResult();
	}

	@Override
	public void Delete(Officer office) {
		entityManager.remove(office);
	}

	@Override
	public boolean ExistById(Integer id) {
		Long count = entityManager.createQuery("select count(o.id) from Office o where o.id=:id", Long.class)
				.setParameter("id", id).getSingleResult();
		return count > 0;
	}

}
