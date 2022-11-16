package com.example.infrastructure.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;

import com.example.domain.model.MedalTable;
import com.example.infrastructure.repository.MedalTableDAO;

import io.reactivex.Single;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class MedalTableDAOImpl implements MedalTableDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(MedalTableDAOImpl.class.getName());

	private static MedalTableDAOImpl instance;
	private EntityManager entityManager;

	public static MedalTableDAOImpl getInstance() {
		if (instance == null) {
			instance = new MedalTableDAOImpl();
		}
		return instance;
	}

	public MedalTableDAOImpl() {
		entityManager = getEntityManager();
	}

	private EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("testCRUD");
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
		}

		return entityManager;
	}

	public Single<MedalTable> insertStateOnRanking(MedalTable stateToInsert) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(stateToInsert);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.error("Something went wrong ", e);
			entityManager.getTransaction().rollback();
		}
		return Single.just(stateToInsert);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Single<List<MedalTable>> getAllMedals() {
		var ranking = entityManager.createQuery("FROM " + MedalTable.class.getName()).getResultList();
		return Single.just(ranking);
	}

	@Override
	public Single<MedalTable> getStateById(Integer id) {
		MedalTable medalTable = entityManager.find(MedalTable.class, id);
		if (medalTable == null) {
			LOGGER.error("Entity doesn't found");
			throw new EntityNotFoundException("Entity doesn't found");
		}
		return Single.just(medalTable);
	}

	@Override
	public Single<MedalTable> updateStateMedal(MedalTable stateToUpdate) {
		entityManager.getTransaction().begin();
		var stateById = entityManager.find(MedalTable.class, stateToUpdate.getId());
		if (stateById != null) {
			entityManager.merge(stateToUpdate);
			entityManager.getTransaction().commit();
		} else {
			LOGGER.error("Entity doen´t exist in ddbb");
			entityManager.getTransaction().rollback();
			throw new EntityNotFoundException("Entity doen´t exist in ddbb");
		}
		return Single.just(stateById);
	}

	@Override
	public Single<MedalTable> deleteState(MedalTable stateToDelete) {
		entityManager.getTransaction().begin();
		var stateById = entityManager.find(MedalTable.class, stateToDelete.getId());
		if (stateById != null) {
			entityManager.remove(stateById);
			entityManager.getTransaction().commit();
		} else {
			LOGGER.error("Entity doen´t exist in ddbb");
			entityManager.getTransaction().rollback();
			throw new EntityNotFoundException("Entity doen´t exist in ddbb");
		}
		return Single.just(stateToDelete);
	}
}
