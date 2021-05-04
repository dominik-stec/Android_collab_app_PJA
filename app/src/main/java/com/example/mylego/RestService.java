package com.example.mylego;

import android.os.Build;

import androidx.annotation.RequiresApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;

public class RestService {

    public static String testUrl = "http://checkip.amazonaws.com";
    public static String testUrlLegoColors = "https://rebrickable.com/api/v3/lego/colors/?key=cae9480418c5c7f7ef9a76142f8f5f48&page=2&ordering=-name%2Cid";
    public static String testUrlLegoSetById =  "https://rebrickable.com/api/v3/lego/sets/75954-1/?key=cae9480418c5c7f7ef9a76142f8f5f48";

    public JSONObject getJson(String url) {

        JSONObject jsonObject = null;

        String rawJson = getRawJson(url);

        //JsonBuilderFactory builderFactory = Json.createBuilderFactory(Collections.emptyMap());

        try {
//            JSONParser parser = new JSONParser();
//            JSONObject json = (JSONObject) parser.parse(stringToParse);
            jsonObject = new JSONObject(rawJson);
        } catch(JSONException e) {
            e.getMessage();
        }

//        Map<String, ?> config = Collections.singletonMap(JsonGenerator.PRETTY_PRINTING, true);
//        JsonWriterFactory writerFactory = Json.createWriterFactory(config);
//        writerFactory.createWriter(System.out).write(jsonObject);
        System.out.println(jsonObject.toString());

        return jsonObject;
    }

    private String getRawJson(String url) {

        String rawJson = "";
        Process process = null;

        String[] request = prepareRestUrl(url);

        try {

            process = Runtime.getRuntime().exec(request);

            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(process.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                rawJson = "test";
                //rawJson line;
            }

        } catch(IOException e) {
            e.getMessage();
        }

        return rawJson;
    }

//        for(String res : response) {
//        System.out.println("length of rest data: " + res.length());
//        System.out.println(res);
//    }

    private String[] prepareRestUrl(String url) {
            String[] res = {"curl", "-X", "GET", url};
            return res;
    }

}
