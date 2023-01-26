package com.moutamid.sra.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;
import com.moutamid.sra.R;
import com.moutamid.sra.listners.ClickListner;
import com.moutamid.sra.models.TasksModel;

import java.util.ArrayList;
import java.util.List;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TaskVH> {
    Context context;
    List<TasksModel> list;
    ClickListner clickListner;

    public TasksAdapter(Context context, List<TasksModel> list, ClickListner clickListner) {
        this.context = context;
        this.list = list;
        this.clickListner = clickListner;
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
        holder.amount.setText("Required amount : $"+model.getAmount());
        holder.income.setText("income : "+model.getIncome()+"%");
        Glide.with(context).load(model.getImage()).into(holder.image);

        if (model.isLock()){
            holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.card_bg));
            holder.lockState.setVisibility(View.VISIBLE);
        } else {
            holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.white));
            holder.lockState.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(v -> clickListner.onClick(list.get(holder.getAdapterPosition())));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TaskVH extends RecyclerView.ViewHolder{
        TextView name, amount, income;
        ImageView image;
        MaterialCardView cardView;
        RelativeLayout lockState;

        public TaskVH(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.taskName);
            amount = itemView.findViewById(R.id.taskAmount);
            income = itemView.findViewById(R.id.taskIncome);
            image = itemView.findViewById(R.id.taskImage);
            cardView = itemView.findViewById(R.id.taskCard);
            lockState = itemView.findViewById(R.id.lockState);
        }
    }
}
