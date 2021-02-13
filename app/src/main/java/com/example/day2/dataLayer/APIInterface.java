package com.example.day2.dataLayer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIInterface {
    @GET("todos/")
    Call<List<Model>> getList();

    @GET("todos/{id}")
    Call<Model> getModel(@Path("id") int id);
}
