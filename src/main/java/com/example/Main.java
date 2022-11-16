package com.example;

import com.example.verticle.MainVerticle;

import io.vertx.core.Vertx;

public class Main {

	public static void main(String[] args) {
		Vertx verticle = Vertx.vertx();
		verticle.deployVerticle(MainVerticle.class.getName());
	}

}
