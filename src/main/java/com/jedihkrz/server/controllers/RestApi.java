package com.jedihkrz.server.controllers;

import com.jedihkrz.server.services.AccountReader;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.servlet.SparkApplication;

import java.io.FileOutputStream;
import java.util.Random;

/**
 * Created by steven.donnelly on 5/16/17.
 */
public class RestApi implements SparkApplication {
    public void init() {
        Spark.put("/register",
                (Request request, Response response) -> {
                    FileOutputStream fos = new FileOutputStream("");
                    fos.write (request.bodyAsBytes());
                    fos.close();
                    response.status(200);
                    return response;
                });

        Spark.get("/accounts",
                (Request request, Response response) -> {
                    JsonParser parser = new JsonParser();
                    JsonElement requestData = parser.parse(request.body());
                    return response;
                });
    }
}
