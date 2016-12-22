package com.gtc.servrice;

import com.gtc.entity.User;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public String sendGet(String shortUrl){
        String url = "localhost:8080/"+shortUrl;
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        Response response = null;
        String s = null;
        try {
            response = client.newCall(request).execute();
            s = response.body().toString();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (response != null) {
                response.body().close();
                response.close();
            }
        }
        return s;
    }

    public String sendPost(String shortUrl){
        String url = "localhost:8080/"+shortUrl;
        MediaType mediaType = MediaType.parse("text/plain");
        Request request = new Request.Builder()
                .url(url)
                .post()
                .build();

        Response response = null;
        String s = null;
        try {
            response = client.newCall(request).execute();
            s = response.body().toString();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (response != null) {
                response.body().close();
                response.close();
            }
        }
        return s;
    }
}
