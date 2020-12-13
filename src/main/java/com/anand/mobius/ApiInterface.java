package com.anand.mobius;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ApiInterface {


    @GET("4c663239-03af-49b5-bcb3-0b0c41565bd2")
    Call<JsonArray> getcupons();
}
