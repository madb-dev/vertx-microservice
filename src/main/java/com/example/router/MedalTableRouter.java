package com.example.router;

import com.example.infrastructure.rest.impl.MedalTableRestHandlerImpl;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class MedalTableRouter {

	private final Vertx vertx;
	private final MedalTableRestHandlerImpl medalTableHandler;

	public MedalTableRouter(Vertx vertx, MedalTableRestHandlerImpl medalTableHandler) {
		this.vertx = vertx;
		this.medalTableHandler = medalTableHandler;
	}

	/**
	 * Main path
	 * 
	 * @param router
	 */
	public void setRouter(Router router) {
		router.mountSubRouter("/v1", buildRouter());
	}

	/**
	 * Route of all endpoints
	 * 
	 * @return router
	 */
	private Router buildRouter() {
		final Router medalRouter = Router.router(vertx);

		medalRouter.route().handler(BodyHandler.create());
		medalRouter.get("/medals").handler(medalTableHandler::getRanking);
		medalRouter.get("/medal/:id").handler(medalTableHandler::getCountryById);
		medalRouter.post("/medal").handler(medalTableHandler::insertState);
		medalRouter.put("/medal").handler(medalTableHandler::updateCountryMedalTable);
		medalRouter.delete("/medal/delete").handler(medalTableHandler::deleteCountryMedalTable);

		return medalRouter;
	}
}
