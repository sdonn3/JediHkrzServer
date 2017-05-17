package com.jedihkrz.server.services;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.jedihkrz.server.responses.DetectResponse;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by steven.donnelly on 5/17/17.
 */
public class DetectService {
    private static final String detectURL = "https://westus.api.cognitive.microsoft.com/face/v1.0/detect";
    private static final String urlParams = "?returnFaceId=true&returnFaceLandmarks=false";
    private static final String subscriptionKey = "fed774d634494cde827d53ccca65efd4";

    public String detect (byte[] facePhoto){

        String returnId = "";

        try{
            URL url = new URL(detectURL + urlParams);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/octet-stream");
            conn.setRequestProperty("Ocp-Apim-Subscription-Key", subscriptionKey);

            OutputStream os = conn.getOutputStream();
            os.write(facePhoto);
            os.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null){
                sb.append(line);
            }

            Gson gson = new Gson();
            DetectResponse[] detectResponses = gson.fromJson(sb.toString(), DetectResponse[].class);

            if (detectResponses.length > 0){
                returnId = detectResponses[0].getFaceId();
            }

            return returnId;

        } catch (Exception e){

        }

        return "";
    }
}
