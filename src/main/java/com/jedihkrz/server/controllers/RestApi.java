package com.jedihkrz.server.controllers;

import com.jedihkrz.server.JsonTransformer;
import com.jedihkrz.server.models.Account;
import com.jedihkrz.server.responses.ClearResponse;
import com.jedihkrz.server.responses.GetAccountResponse;
import com.jedihkrz.server.responses.RegisterResponse;
import com.jedihkrz.server.services.AccountReader;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jedihkrz.server.services.DetectService;
import com.jedihkrz.server.services.UserMapper;
import com.jedihkrz.server.services.VerifyService;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.servlet.SparkApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Random;

/**
 * Created by steven.donnelly on 5/16/17.
 */
public class RestApi implements SparkApplication {

    static private final Logger log = LoggerFactory.getLogger(RestApi.class);

    @Override
    public void init() {
        Spark.post("/getaccount", (request, response) -> {
            String userId = request.queryParams("user");
            boolean authenticated = false;

            DetectService detectService = new DetectService();
            String testFaceId = detectService.detect(request.bodyAsBytes());    // Gets the FaceId of the image

            Account compareAccount = UserMapper.get().getUserAccount(userId);
            GetAccountResponse getAccountResponse = new GetAccountResponse();
            if (compareAccount != null) {
                String userFaceId = compareAccount.getFaceId();
                VerifyService verifyService = new VerifyService();
                if (verifyService.verify(userFaceId, testFaceId)) {             // Verifies that the image is associated with a registered user
                    authenticated = true;
                    log.info("Authenticated for " + userId);
                    getAccountResponse.setStatus("success");
                }

            } else {
                getAccountResponse.setStatus("failure");
                log.warn("User authentication failed. Try again.");
            }

            return getAccountResponse;
        }, new JsonTransformer());

        Spark.post("/register", (request, response) -> {
            byte[] imageAsByteArray = request.bodyAsBytes();

            DetectService detectService = new DetectService();
            String faceId = detectService.detect(imageAsByteArray);

            String userId = request.queryParams("user");
            UserMapper.get().addUser(userId, faceId);

            RegisterResponse registerResponse = new RegisterResponse();

            log.info("User Registration Successful!");

            registerResponse.setStatus("User Registration Successful!");
            return registerResponse.getStatus();
        }, new JsonTransformer());

        Spark.get("/clear", (request, response) -> {
            UserMapper.get().clear();
            ClearResponse clearResponse = new ClearResponse();
            clearResponse.setStatus("success");
            return clearResponse;
        }, new JsonTransformer());
    }
}
