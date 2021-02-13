package com.example.day2.activityone;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.day2.dataLayer.DataSource;
import com.example.day2.dataLayer.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends ViewModel {
    DataSource dataSourc;
    private final MutableLiveData<List<Model>> data=new MutableLiveData<>();
    private final MutableLiveData<Boolean> error=new MutableLiveData<>();
    private final MutableLiveData<Integer> navigate=new MutableLiveData<>();
    private final MutableLiveData<Integer> progress=new MutableLiveData<>();

    public MainActivityViewModel() {
        Log.d("TAG","constracror");
        dataSourc=new DataSource();
    }

    public void LoadData(){
        Log.d("TAG", "LoadData: ");
        progress.setValue(View.VISIBLE);
        dataSourc.getData().enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                data.setValue(response.body());
                progress.setValue(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {
                error.setValue(true);
            }
        });
    }



    public void setNavigate(int id){
        if (id!=0)
        navigate.setValue(id);
        else
            error.setValue(true);
    }
    public LiveData<Integer> getNavigate(){
        return navigate;
    }

    public LiveData<Integer> getProgressStatus() {
        return progress;
    }

    public LiveData<List<Model>> getList(){
        return data;
    }
    public LiveData<Boolean> getError(){
        return error;
    }
}
