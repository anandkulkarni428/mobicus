package com.anand.mobius.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpHelpers {
    public final static String BASE_IMG_URL = "https://run.mocky.io/v3/";
//    private static String BASE_URL = "https://run.mocky.io/v3/";
    private static String BASE_URL = "https://run.mocky.io/v3/";
    private static Retrofit retrofit;
    private static Gson gson;

    public static synchronized Retrofit getInstance() {
        if (retrofit == null) {
            if (gson == null) {
                gson = new GsonBuilder().setLenient().create();
            }

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
