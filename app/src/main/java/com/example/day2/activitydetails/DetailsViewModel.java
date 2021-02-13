package com.example.day2.activitydetails;


import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.day2.dataLayer.DataSource;
import com.example.day2.dataLayer.Model;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsViewModel extends ViewModel {
    DataSource dataSource;

    private final MutableLiveData<Model> data=new MutableLiveData<>();
    private final MutableLiveData<Integer> progress=new MutableLiveData<>();
    private final MutableLiveData<Boolean> error=new MutableLiveData<>();
    public DetailsViewModel() {
        dataSource=new DataSource();
    }


    public void loadData(int id) {

        progress.setValue(View.VISIBLE);
        dataSource.getDataModel(id).enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                data.setValue(response.body());
                progress.setValue(View.INVISIBLE);

            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
               error.setValue(true);
            }
        });
    }

    public LiveData<Model> getData() {
        return data;
    }

    public LiveData<Integer> getProgress() {
        return progress;
    }

    public LiveData<Boolean> getError() {
        return error;
    }
}
