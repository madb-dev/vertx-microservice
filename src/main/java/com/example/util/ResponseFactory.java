package com.example.util;

import java.util.NoSuchElementException;

import javax.persistence.EntityNotFoundException;

import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;

/**
 * Http responses
 *
 */
public class ResponseFactory {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResponseFactory.class.getName());

	private static final String CONTENT_TYPE_HEADER = "Content-Type";
	private static final String APPLICATION_JSON = "application/json";

	private ResponseFactory() {
	}

	/** Build 200 Response */
	public static void HttpOkResponse(RoutingContext context, Object response) {
		context.response().setStatusCode(200).putHeader(CONTENT_TYPE_HEADER, APPLICATION_JSON)
				.end(Json.encodePrettily(response));
		LOGGER.info("Response " + context.response().getStatusCode() + " " + context.response().getStatusMessage()
				+ " -> " + Json.encode(response));
	}

	/** Build 201 response */
	public static void HttpCreateResponse(RoutingContext context, Object response) {
		context.response().setStatusCode(201).end();
		LOGGER.info("Response " + context.response().getStatusCode() + " " + context.response().getStatusMessage()
				+ " -> " + Json.encode(response));
	}

	/** Build 202 response */
	public static void HttpAcceptedResponse(RoutingContext context, Object response) {
		context.response().setStatusCode(202).end();
		LOGGER.info("Response " + context.response().getStatusCode() + " " + context.response().getStatusMessage()
				+ " -> " + Json.encode(response));
	}

	/** Build 204 response */
	public static void HttpNoContentResponse(RoutingContext context, Object response) {
		context.response().setStatusCode(204).end();
		LOGGER.info("Response " + context.response().getStatusCode() + " " + context.response().getStatusMessage()
				+ " -> " + Json.encode(response));
	}

	/** Build error responses */
	public static void HttpErrorResponse(RoutingContext context, Throwable error) {
		final int status;
		final String message;

		if (error instanceof IllegalArgumentException || error instanceof IllegalStateException
				|| error instanceof NullPointerException) {
			// Bad Request
			status = 400;
			message = error.getMessage();
		} else if (error instanceof NoSuchElementException || error instanceof EntityNotFoundException) {
			// Not Found
			status = 404;
			message = error.getMessage();
		} else {
			// Internal Server Error
			status = 500;
			message = "Internal Server Error";
		}

		context.response().setStatusCode(status).putHeader(CONTENT_TYPE_HEADER, APPLICATION_JSON)
				.end(new JsonObject().put("error", message).encodePrettily());
	}
}
