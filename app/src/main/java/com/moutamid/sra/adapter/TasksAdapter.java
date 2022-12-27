package com.moutamid.sra.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.moutamid.sra.R;
import com.moutamid.sra.models.TasksModel;

import java.util.ArrayList;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TaskVH> {
    Context context;
    ArrayList<TasksModel> list;

    public TasksAdapter(Context context, ArrayList<TasksModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public TaskVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.task_card, parent, false);
        return new TaskVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskVH holder, int position) {
        TasksModel model = list.get(position);
        holder.name.setText(model.getName());
        holder.amount.setText("Required amount : "+model.getAmount());
        holder.income.setText("income : "+model.getIncome());
        Glide.with(context).load(model.getImage()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TaskVH extends RecyclerView.ViewHolder{
        TextView name, amount, income;
        ImageView image;

        public TaskVH(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.taskName);
            amount = itemView.findViewById(R.id.taskAmount);
            income = itemView.findViewById(R.id.taskIncome);
            image = itemView.findViewById(R.id.taskImage);
        }
    }
}
