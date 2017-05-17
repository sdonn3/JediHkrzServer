package com.jedihkrz.server;

import com.jedihkrz.server.models.Account;
import com.jedihkrz.server.responses.RegisterResponse;
import com.jedihkrz.server.services.*;
import spark.Spark;

import java.util.Map;

/**
 * Created by steven.donnelly on 5/8/17.
 */
public class testMain {
    public static void main (String[] args){

        Spark.post("/getaccount", (request, response) -> {
            String userId = request.queryParams("user");
            boolean authenticated = false;

            DetectService detectService = new DetectService();
            String testFaceId = detectService.detect(request.bodyAsBytes());

            Account compareAccount = UserMapper.get().getUserAccount(userId);
            if (compareAccount != null){
                String userFaceId = compareAccount.getFaceId();
                VerifyService verifyService = new VerifyService();
                if (verifyService.verify(userFaceId, testFaceId)){
                    authenticated = true;
                }
            }

            return (authenticated ? compareAccount : null);
        }, new JsonTransformer());

        Spark.post("/register", (request, response) -> {
            byte[] imageAsByteArray = request.bodyAsBytes();

            DetectService detectService = new DetectService();
            String faceId = detectService.detect(imageAsByteArray);

            String userId = request.queryParams("user");
            UserMapper.get().addUser(userId, faceId);

            RegisterResponse registerResponse = new RegisterResponse();
            registerResponse.setStatus("success");
            return registerResponse;
        }, new JsonTransformer());
    }
}
