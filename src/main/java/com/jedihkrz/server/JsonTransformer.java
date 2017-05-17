package com.jedihkrz.server;

import com.google.gson.Gson;
import spark.ResponseTransformer;

/**
 * Created by steven.donnelly on 5/17/17.
 */
public class JsonTransformer implements ResponseTransformer {

    private Gson gson = new Gson();

    @Override
    public String render(Object model) throws Exception {
        return gson.toJson(model);
    }
}
