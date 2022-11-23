package com.example.infrastructure.rest.impl;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.example.application.service.MedalTableService;
import com.example.application.service.impl.MedalTableServiceImpl;
import com.example.domain.model.MedalTable;
import com.example.infrastructure.rest.MedalTableRestHandler;
import com.example.util.ResponseFactory;

import io.vertx.core.json.Json;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;

public class MedalTableRestHandlerImpl implements MedalTableRestHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(MedalTableServiceImpl.class.getName());

	private MedalTableService service;

	public MedalTableRestHandlerImpl(MedalTableService service) {
		this.service = service;
	}

	@Override
	public void insertState(RoutingContext context) {
		LOGGER.info(
				"SENDING POST " + context.currentRoute().getPath() + " -> " + Json.encode(context.getBodyAsString()));
		var stateToInsert = context.getBodyAsJson().mapTo(MedalTable.class);
		service.insertStateOnRanking(stateToInsert).subscribe(
				response -> ResponseFactory.HttpCreateResponse(context, response),
				error -> ResponseFactory.HttpErrorResponse(context, error));
	}

	public void getRanking(RoutingContext context) {
		LOGGER.info("SENDING GET " + context.currentRoute().getPath());
		service.getAllMedals().subscribe(response -> ResponseFactory.HttpOkResponse(context, response),
				error -> ResponseFactory.HttpErrorResponse(context, error));

	}

	@Override
	public void getCountryById(RoutingContext context) {
		try {
			LOGGER.info("SENDING GET " + context.currentRoute().getPath() + " -> " + context.request().getParam("id"));
			service.getStateById(context.request().getParam("id")).subscribe(
					response -> ResponseFactory.HttpOkResponse(context, response),
					error -> ResponseFactory.HttpErrorResponse(context, error));
		} catch (EntityNotFoundException e) {
			e.getMessage();
			ResponseFactory.HttpErrorResponse(context, e);
		}
	}

	@Override
	public void updateCountryMedalTable(RoutingContext context) {

		var stateToUpdate = context.getBodyAsJson().mapTo(MedalTable.class);

		Optional.ofNullable(context.getBodyAsJson().fieldNames());

		try {
			LOGGER.info("SENDING UPDATE " + context.currentRoute().getPath() + " -> "
					+ Json.encode(context.getBodyAsString()));
			service.updateStateMedal(stateToUpdate).subscribe(
					response -> ResponseFactory.HttpNoContentResponse(context, response),
					error -> ResponseFactory.HttpErrorResponse(context, error));
		} catch (EntityNotFoundException e) {
			e.getMessage();
			ResponseFactory.HttpErrorResponse(context, e);
		}
	}

	@Override
	public void deleteCountryMedalTable(RoutingContext context) {
		var stateToDelete = context.getBodyAsJson().mapTo(MedalTable.class);
		try {
			LOGGER.info("SENDING DELETE " + context.currentRoute().getPath() + " -> "
					+ Json.encode(context.getBodyAsString()));
			service.deleteState(stateToDelete).subscribe(
					response -> ResponseFactory.HttpAcceptedResponse(context, response),
					error -> ResponseFactory.HttpErrorResponse(context, error));
		} catch (EntityNotFoundException e) {
			e.getMessage();
			ResponseFactory.HttpErrorResponse(context, e);
		}
	}
}
