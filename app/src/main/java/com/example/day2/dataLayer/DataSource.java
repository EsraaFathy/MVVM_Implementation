package com.example.day2.dataLayer;

import java.util.List;

import retrofit2.Call;

public class DataSource {

    APIInterface apiInterface= APIClient.getClient().create(APIInterface.class);

    public Call<List<Model>>  getData(){
        return apiInterface.getList();
    }
    public Call<Model>  getDataModel(int id){
        return apiInterface.getModel(id);
    }
}
