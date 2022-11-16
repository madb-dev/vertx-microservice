package com.example.application.service.impl;

import java.util.List;

import com.example.application.service.MedalTableService;
import com.example.domain.model.MedalTable;
import com.example.infrastructure.repository.impl.MedalTableDAOImpl;

import io.reactivex.Single;

public class MedalTableServiceImpl implements MedalTableService {

	private MedalTableDAOImpl medalDao = MedalTableDAOImpl.getInstance();

	public MedalTableServiceImpl(MedalTableDAOImpl mtDAO) {
		this.medalDao = mtDAO;
	}

	@Override
	public Single<List<MedalTable>> getAllMedals() {
		return medalDao.getAllMedals();
	}

	@Override
	public Single<MedalTable> deleteState(MedalTable stateToDelete) {
		return medalDao.deleteState(stateToDelete);
	}

	@Override
	public Single<MedalTable> insertStateOnRanking(MedalTable stateToInsert) {
		return medalDao.insertStateOnRanking(stateToInsert);
	}

	@Override
	public Single<MedalTable> getStateById(String id) {
		return medalDao.getStateById(Integer.parseInt(id));
	}

	@Override
	public Single<MedalTable> updateStateMedal(MedalTable stateToUpdate) {
		return medalDao.updateStateMedal(stateToUpdate);
	}
}
