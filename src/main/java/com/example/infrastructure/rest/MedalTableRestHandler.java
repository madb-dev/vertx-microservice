package com.example.infrastructure.rest;

import io.vertx.ext.web.RoutingContext;

public interface MedalTableRestHandler {

	public void insertState(RoutingContext context);

	public void getRanking(RoutingContext context);

	public void getCountryById(RoutingContext context);

	public void updateCountryMedalTable(RoutingContext context);

	public void deleteCountryMedalTable(RoutingContext context);
}
