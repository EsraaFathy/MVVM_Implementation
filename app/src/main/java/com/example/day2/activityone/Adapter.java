package com.example.day2.activityone;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day2.dataLayer.Model;
import com.example.day2.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewModel> {
//    OnItemClickListener onItemClickListener;
   MainActivityViewModel mainActivityViewModel;
    List<Model> models=new ArrayList<>();

    public void setModels(List<Model> models) {
        this.models = models;
    }

    public Adapter(List<Model> models, MainActivityViewModel mainActivityViewModel) {
        this.models = models;
        this.mainActivityViewModel=mainActivityViewModel;
//        this.onItemClickListener=onItemClickListener;
    }

    @NonNull
    @Override
    public Adapter.ViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item, parent, false);
        return new ViewModel(view);    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewModel holder, int position) {
        holder.bind(models.get(position));
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewModel extends RecyclerView.ViewHolder  {
        TextView title;
        CheckBox checkBox;
        public ViewModel(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            checkBox=itemView.findViewById(R.id.checkBox);


        }

        void bind(Model model){
            title.setText(model.getTitle());
            checkBox.setChecked(model.getCompleted());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mainActivityViewModel.setNavigate(model.getId());
                }
            });
        }
    }
}
