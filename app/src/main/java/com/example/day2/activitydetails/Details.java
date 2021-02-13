package com.example.day2.activitydetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.day2.dataLayer.Model;
import com.example.day2.R;

public class Details extends AppCompatActivity{

    TextView idAPI,userId,title;
    CheckBox checkBox;
    DetailsViewModel presenter;
    ProgressBar progressBar;
    int id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        idAPI=findViewById(R.id.id);
        userId=findViewById(R.id.userId);
        title=findViewById(R.id.title);
        checkBox=findViewById(R.id.checkBox2);
        progressBar=findViewById(R.id.progressBar2);

        id= getIntent().getIntExtra("ID",0);
        if (savedInstanceState==null)
        presenter=new ViewModelProvider(this).get(DetailsViewModel.class);
        if (id!=0)
            presenter.loadData(id);

        presenter.getData().observe(this, new Observer<Model>() {
            @Override
            public void onChanged(Model model) {
                upDataUI(model);
            }
        });
        presenter.getProgress().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                progressBar.setVisibility(integer);
            }
        });
        presenter.getError().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean)
                    showError();
            }
        });
    }


    @SuppressLint("SetTextI18n")
    public void upDataUI(Model models) {
        idAPI.setText("id = "+models.getId());
        userId.setText("user id = "+models.getUserId());
        title.setText("title = "+models.getTitle());
        checkBox.setChecked(models.getCompleted());

    }

    public void showError() {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();

    }
}