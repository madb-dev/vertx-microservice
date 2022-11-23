package com.example.verticle;

import com.example.Main;
import com.example.application.service.MedalTableService;
import com.example.application.service.impl.MedalTableServiceImpl;
import com.example.infrastructure.repository.MedalTableDAO;
import com.example.infrastructure.repository.impl.MedalTableDAOImpl;
import com.example.infrastructure.rest.MedalTableRestHandler;
import com.example.infrastructure.rest.impl.MedalTableRestHandlerImpl;
import com.example.router.MedalTableRouter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;

public class MainVerticle extends AbstractVerticle {
	private static final Logger LOGGER = LoggerFactory.getLogger(Main.class.getName());

	@Override
	public void start(Future<Void> startPromise) throws Exception {

		final MedalTableDAO medalDAO = new MedalTableDAOImpl();
		final MedalTableService medalService = new MedalTableServiceImpl(medalDAO);
		final MedalTableRestHandler medalHandler = new MedalTableRestHandlerImpl(medalService);
		final MedalTableRouter medalRouter = new MedalTableRouter(vertx, medalHandler);

		final Router router = Router.router(vertx);
		medalRouter.setRouter(router);

		router.get("/test").handler(context -> {
			LOGGER.info("Sending GET");
			context.response().putHeader("content-type", "application/json")
					.end(new JsonObject().put("hello", "Hello world !!!").encode());
		});

		vertx.createHttpServer().requestHandler(router).listen(8888, http -> {
			if (http.succeeded()) {
				startPromise.complete();
				LOGGER.info("HTTP server started on port 8888");
			} else {
				startPromise.fail(http.cause());
			}
		});
	}
}
