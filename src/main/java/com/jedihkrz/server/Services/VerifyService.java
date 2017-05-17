package com.jedihkrz.server.services;

import com.google.gson.Gson;
import com.jedihkrz.server.requests.VerifyRequest;
import com.jedihkrz.server.responses.DetectResponse;
import com.jedihkrz.server.responses.VerifyResponse;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by steven.donnelly on 5/17/17.
 */
public class VerifyService {
    private static final String verifyURL = "https://westus.api.cognitive.microsoft.com/face/v1.0/verify";
    private static final String urlParams = "?returnFaceId=true&returnFaceLandmarks=false";
    private static final String subscriptionKey = "fed774d634494cde827d53ccca65efd4";

    private static final double CONFIDENCE_VALUE = 0.8;

    public boolean verify (String faceId1, String faceId2){

        try{
            URL url = new URL(verifyURL + urlParams);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Ocp-Apim-Subscription-Key", subscriptionKey);

            VerifyRequest verifyRequest= new VerifyRequest();
            verifyRequest.setFaceId1(faceId1);
            verifyRequest.setFaceId2(faceId2);

            Gson gson = new Gson();
            String jsonRequest = gson.toJson(verifyRequest);

            OutputStream os = conn.getOutputStream();
            os.write(jsonRequest.getBytes());
            os.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null){
                sb.append(line);
            }

            VerifyResponse verifyResponse = gson.fromJson(sb.toString(), VerifyResponse.class);

            return (verifyResponse.getIsIdentical() && (verifyResponse.getConfidence() > CONFIDENCE_VALUE));

        } catch (Exception e){
        }

        return false;
    }
}
