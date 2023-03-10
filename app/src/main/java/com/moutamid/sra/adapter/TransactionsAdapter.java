package com.moutamid.sra.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.moutamid.sra.R;
import com.moutamid.sra.models.RequestModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class TransactionsAdapter extends RecyclerView.Adapter<TransactionsAdapter.TransactionVH> {
    Context context;
    ArrayList<RequestModel> list;

    public TransactionsAdapter(Context context, ArrayList<RequestModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public TransactionVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.transaction_card, parent, false);
        return new TransactionVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionVH holder, int position) {
        RequestModel model = list.get(holder.getAdapterPosition());
        if (model.getType().equals("DEP")){
            holder.name.setText("Deposit Request");
        } else if (model.getType().equals("TASK")){
            holder.name.setText("Task Open Request");
        }  else {
            holder.name.setText("Withdraw Request");
        }
        if (model.getStatus().equals("PEN")){
            holder.card.setCardBackgroundColor(context.getResources().getColor(R.color.secondary_color));
            holder.status.setText("Pending");
        } else if (model.getStatus().equals("COM")){
            holder.card.setCardBackgroundColor(context.getResources().getColor(R.color.primary_color));
            holder.status.setText("Completed");
        } else {
            holder.card.setCardBackgroundColor(context.getResources().getColor(R.color.red));
            holder.status.setText("Canceled");
        }
        holder.amount.setText("Amount : $" + model.getAmount());
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy, hh:mm aa");
        String date = format.format(model.getTimestamps());
        holder.time.setText("Date/Time : " + date);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TransactionVH extends RecyclerView.ViewHolder{
        CardView card;
        TextView status, time, amount, name;

        public TransactionVH(@NonNull View itemView) {
            super(itemView);

            card = itemView.findViewById(R.id.statusCard);
            status = itemView.findViewById(R.id.status);
            time = itemView.findViewById(R.id.date);
            amount = itemView.findViewById(R.id.amount);
            name = itemView.findViewById(R.id.requestFor);

        }
    }
}
