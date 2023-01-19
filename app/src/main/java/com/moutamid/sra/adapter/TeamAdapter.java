package com.moutamid.sra.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moutamid.sra.MyTeamActivity;
import com.moutamid.sra.R;
import com.moutamid.sra.models.TeamModel;

import java.util.ArrayList;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamVH> {

    Context context;
    ArrayList<TeamModel> list;

    public TeamAdapter(Context context, ArrayList<TeamModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public TeamVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.team_card, parent, false);
        return new TeamVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamVH holder, int position) {
        TeamModel model = list.get(holder.getAdapterPosition());

        holder.name.setText(model.getName());
        holder.earn.setText("$"+model.getEarn());

        holder.itemView.setOnClickListener(v -> {
            Intent i = new Intent(context, MyTeamActivity.class);
            i.putExtra("name", (model.getName() + " Team"));
            i.putExtra("key", model.getKey());
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class TeamVH extends RecyclerView.ViewHolder{
        TextView name, earn;

        public TeamVH(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            earn = itemView.findViewById(R.id.earn);
        }
    }
}
