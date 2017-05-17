package com.jedihkrz.server;

import com.jedihkrz.server.responses.RegisterResponse;
import com.jedihkrz.server.services.AccountReader;
import com.jedihkrz.server.services.FileWriter;
import spark.Spark;

import java.util.Map;

/**
 * Created by steven.donnelly on 5/8/17.
 */
public class testMain {
    public static void main (String[] args){

        Spark.get("/accounts", (request, response) -> {
            AccountReader accountReader = new AccountReader();
            return accountReader.getAccountsFromJson();
        }, new JsonTransformer());

        Spark.put("/register", (request, response) -> {

            byte[] imageAsByteArray = request.bodyAsBytes();

            FileWriter fileWriter = new FileWriter();
            fileWriter.writeFile(imageAsByteArray, "currentImage.jpg");

            RegisterResponse registerResponse = new RegisterResponse();
            registerResponse.setStatus("successful");
            return registerResponse;
        }, new JsonTransformer());
    }
}
