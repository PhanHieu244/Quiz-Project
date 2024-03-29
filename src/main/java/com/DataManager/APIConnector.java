package com.DataManager;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;


public class APIConnector {
    private final static OkHttpClient client = new OkHttpClient();

    public static Object getData(String url){
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            JSONParser parser = new JSONParser();
            try {
                return parser.parse(string);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void postData(String json, String url) throws IOException {
        RequestBody body = RequestBody.create(json, DataManager.JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();

        response.close();
    }

    public static void putData(String json, String url) throws IOException {
        RequestBody body = RequestBody.create(json, DataManager.JSON);
        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .build();
        Response response = client.newCall(request).execute();

        response.close();
    }

    public static void deleteData(String json, String url) throws IOException {
        RequestBody body = RequestBody.create(json, DataManager.JSON);
        Request request = new Request.Builder()
                .url(url)
                .delete(body)
                .build();
        Response response = client.newCall(request).execute();

        response.close();
    }
}
