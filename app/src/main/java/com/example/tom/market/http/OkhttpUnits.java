package com.example.tom.market.http;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by tom on 2016/9/12.
 */
public class OkhttpUnits {
    public static String getData(String path){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(path)
                .get()
                .build();
        try {
            Response response = client.newCall(request).execute();
            return  response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
