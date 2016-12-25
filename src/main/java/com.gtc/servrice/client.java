package com.gtc.servrice;

import okhttp3.*;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by gtc on 2016/12/22.
 */
@Service(value = "custemClient")
public class client {

    private OkHttpClient client = new OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build();

    public String sendGet(String shortUrl) {
        String url = "http://localhost:8080/" + shortUrl;
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        Response response = null;
        String s = null;
        try {
            response = client.newCall(request).execute();
            s = response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                response.body().close();
                response.close();
            }
        }
        return s;
    }

    public String sendPost(String shortUrl, String json) {
        String url = "http://localhost:8080/" + shortUrl;
        //MediaType mediaType = MediaType.parse("text/plain");
        MediaType mediaType = MediaType.parse("application/json");

        RequestBody body = RequestBody.create(mediaType, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("content-type", "application/json")
                .build();
        System.out.println(request.body().toString());

        Response response = null;
        String s = null;
        try {
            response = client.newCall(request).execute();
            s = response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                response.body().close();
                response.close();
            }
        }
        return s;
    }
}
