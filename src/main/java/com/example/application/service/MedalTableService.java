package com.example.application.service;

import java.util.List;

import com.example.domain.model.MedalTable;

import io.reactivex.Single;

public interface MedalTableService {

	public Single<MedalTable> insertStateOnRanking(MedalTable stateToInsert);

	public Single<List<MedalTable>> getAllMedals();

	public Single<MedalTable> getStateById(String id);

	public Single<MedalTable> updateStateMedal(MedalTable stateToUpdate);

	public Single<MedalTable> deleteState(MedalTable stateToDelete);

}
