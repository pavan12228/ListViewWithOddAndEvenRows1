package com.blogspot.hongthaiit.listview;

import com.google.gson.JsonArray;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Ravi on 05-02-2017.
 */

public interface Apiservice {

     @GET("/json/movies.json")
    public void Mymeth(Callback<JsonArray> callback);

}
