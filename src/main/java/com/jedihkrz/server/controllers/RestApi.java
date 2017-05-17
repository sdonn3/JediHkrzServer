package com.jedihkrz.server.controllers;

import spark.Request;
import spark.Response;
import spark.Spark;
import spark.servlet.SparkApplication;

import java.io.FileOutputStream;

/**
 * Created by steven.donnelly on 5/16/17.
 */
public class RestApi implements SparkApplication {
    public void init() {
        Spark.put("/register",
                (Request request, Response response) -> {
                    FileOutputStream fos = new FileOutputStream("/somepathname");
                    fos.write (request.bodyAsBytes());
                    fos.close();
                    response.status(200);
                    return response;
                });
    }
}
