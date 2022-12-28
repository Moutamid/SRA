package com.moutamid.sra.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.moutamid.sra.AboutUsActivity;
import com.moutamid.sra.CaptchaTaskActivity;
import com.moutamid.sra.DepositActivity;
import com.moutamid.sra.InviteFriendsActivity;
import com.moutamid.sra.R;
import com.moutamid.sra.RulesActivity;
import com.moutamid.sra.WithdrawActivity;
import com.moutamid.sra.adapter.TasksAdapter;
import com.moutamid.sra.database.TaskDB;
import com.moutamid.sra.databinding.FragmentHomeBinding;
import com.moutamid.sra.dialog.UnlockDialog;
import com.moutamid.sra.listners.ClickListner;
import com.moutamid.sra.models.TasksModel;
import com.moutamid.sra.models.UserModel;
import com.moutamid.sra.utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;
    Context context;
    String[] taskNames = {"Captcha", "Amazon", "Dummy Task 1", "Dummy Task 2"};
    int[] taskAmount = {50, 100, 150, 200};
    int[] taskIncome = {1, 3, 5, 10};
    int[] taskImages = {R.drawable.captcha, R.drawable.amazon, R.drawable.sra_logo, R.drawable.sra_logo};
    boolean[] taskLockState = {false, true, true, true};
    TasksAdapter adapter;
    List<TasksModel> list;
    TaskDB database;
    UnlockDialog dialog;
    int assets;

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

        list = new ArrayList<>();

        database = TaskDB.getInstance(context);

        try {
            list = database.TaskDao().getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (list.isEmpty()){
            getData();
        }

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

        adapter = new TasksAdapter(context, list, clickListner);
        binding.tasks.setAdapter(adapter);

        return view;
    }

    ClickListner clickListner = new ClickListner() {
        @Override
        public void onClick(TasksModel task) {
            if (!task.isLock()) {
                assets = Integer.parseInt(binding.totalAssetsCount.getText().toString().substring(1));
                if (task.getName().equals("Captcha")) {
                    Intent i = new Intent(context, CaptchaTaskActivity.class);
                    i.putExtra("assets", assets);
                    startActivity(i);
                } else {
                    Toast.makeText(context, task.getName(), Toast.LENGTH_SHORT).show();
                }
            } else {
                assets = Integer.parseInt(binding.totalAssetsCount.getText().toString().substring(1));
                dialog = new UnlockDialog((Activity) context, task, list, adapter, assets);
                dialog.show();
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }
        }
    };

    private void getData() {
        for (int i=0; i< taskNames.length; i++) {
            TasksModel model = new TasksModel(taskNames[i], taskAmount[i], taskIncome[i], taskImages[i], taskLockState[i]);
            database.TaskDao().insert(model);
            new Handler().postDelayed(()->{},200);
        }
        list.clear();
        list.addAll(database.TaskDao().getAll());
        if (adapter != null)
            adapter.notifyDataSetChanged();
    }
}