package com.moutamid.sra.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.moutamid.sra.AboutUsActivity;
import com.moutamid.sra.DepositActivity;
import com.moutamid.sra.InviteFriendsActivity;
import com.moutamid.sra.R;
import com.moutamid.sra.RulesActivity;
import com.moutamid.sra.WithdrawActivity;
import com.moutamid.sra.adapter.TasksAdapter;
import com.moutamid.sra.databinding.FragmentHomeBinding;
import com.moutamid.sra.models.TasksModel;
import com.moutamid.sra.models.UserModel;
import com.moutamid.sra.utils.Constants;

import java.util.ArrayList;
import java.util.UUID;


public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;
    Context context;
    String[] taskNames = {"Captcha"};
    int[] taskAmount = {50};
    int[] taskIncome = {10};
    int[] taskImages = {R.drawable.captcha};
    TasksAdapter adapter;
    ArrayList<TasksModel> list;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        context = view.getContext();

        binding.tasks.setLayoutManager(new LinearLayoutManager(context));
        binding.tasks.setHasFixedSize(false);

        Constants.databaseReference().child("users").child(Constants.auth().getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        UserModel model = snapshot.getValue(UserModel.class);
                        binding.username.setText(model.getUsername());
                        binding.totalAssetsCount.setText("$" + model.getAssets());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        binding.inviteFriends.setOnClickListener(v -> {
            startActivity(new Intent(context, InviteFriendsActivity.class));
        });

        binding.deposit.setOnClickListener(v -> {
            startActivity(new Intent(context, DepositActivity.class));
        });

        binding.withdraw.setOnClickListener(v -> {
            Intent i = new Intent(context, WithdrawActivity.class);
            startActivity(i);
        });

        binding.aboutUS.setOnClickListener(v -> {
            startActivity(new Intent(context, AboutUsActivity.class));
        });

        binding.rules.setOnClickListener(v -> {
            startActivity(new Intent(context, RulesActivity.class));
        });

        getData();

        adapter = new TasksAdapter(context, list);
        binding.tasks.setAdapter(adapter);

        return view;
    }

    private void getData(){
        list = new ArrayList<>();
        for (int i=0; i< taskNames.length; i++) {
            String uid = UUID.randomUUID().toString();
            list.add(new TasksModel(uid, taskNames[i], taskAmount[i], taskIncome[i], taskImages[i], false));
        }
    }
}