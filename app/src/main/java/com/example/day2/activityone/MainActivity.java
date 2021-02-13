package com.example.day2.activityone;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day2.activitydetails.Details;
import com.example.day2.dataLayer.Model;
import com.example.day2.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

    MainActivityViewModel presenter;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    Adapter adapter;
    List<Model> models =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            presenter = new ViewModelProvider(this).get(MainActivityViewModel.class);
        recyclerView=findViewById(R.id.recyclerView);
        progressBar=findViewById(R.id.progressBar);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        adapter=new Adapter(models,presenter);
        recyclerView.setAdapter(adapter);

        Log.d("TAG", "onCreate: ");
        presenter.LoadData();

        presenter.getList().observe(this, new Observer<List<Model>>() {
            @Override
            public void onChanged(List<Model> models) {
                upDataUI(models);
            }
        });

        presenter.getError().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean)
                    showError();
            }
        });

        presenter.getProgressStatus().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                progressBar.setVisibility(integer);
            }
        });

        presenter.getNavigate().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                navigate(integer);
            }
        });



    }


    public void upDataUI(List<Model> models) {
        Log.d("TAG", "upDataUI: ");
        adapter.setModels(models);
        adapter.notifyDataSetChanged();

    }

    public void showError() {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();

    }


    public void navigate(int id) {
        Intent intent=new Intent(MainActivity.this, Details.class);
        intent.putExtra("ID",id);
        startActivity(intent);
    }

}