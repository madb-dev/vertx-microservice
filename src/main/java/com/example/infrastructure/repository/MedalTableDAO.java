package com.example.infrastructure.repository;

import java.util.List;

import com.example.domain.model.MedalTable;

import io.reactivex.Single;

public interface MedalTableDAO {

	public Single<MedalTable> insertStateOnRanking(MedalTable stateToInsert);

	public Single<List<MedalTable>> getAllMedals();

	public Single<MedalTable> getStateById(Integer id);

	public Single<MedalTable> updateStateMedal(MedalTable stateToUpdate);

	public Single<MedalTable> deleteState(MedalTable stateToDelete);

}
