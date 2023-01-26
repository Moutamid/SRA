package com.moutamid.sra.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fxn.stash.Stash;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.moutamid.sra.AboutUsActivity;
import com.moutamid.sra.AmazonTaskActivity;
import com.moutamid.sra.CaptchaTaskActivity;
import com.moutamid.sra.DepositActivity;
import com.moutamid.sra.InviteFriendsActivity;
import com.moutamid.sra.R;
import com.moutamid.sra.RulesActivity;
import com.moutamid.sra.TranslateTaskActivity;
import com.moutamid.sra.WithdrawActivity;
import com.moutamid.sra.adapter.TasksAdapter;
import com.moutamid.sra.database.TaskDB;
import com.moutamid.sra.databinding.FragmentHomeBinding;
import com.moutamid.sra.dialog.UnlockDialog;
import com.moutamid.sra.listners.ClickListner;
import com.moutamid.sra.models.TasksModel;
import com.moutamid.sra.models.UserModel;
import com.moutamid.sra.utils.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;


public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;
    Context context;
    TasksAdapter adapter;
    List<TasksModel> list;
    TaskDB database;
    UnlockDialog dialog;
    float assets;
    float d;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        context = view.getContext();

        binding.tasks.setLayoutManager(new LinearLayoutManager(context));
        binding.tasks.setHasFixedSize(false);

        list = new ArrayList<>();

        database = TaskDB.getInstance(context);
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String mDate = format.format(date);
        d = Stash.getFloat(mDate, 0.0F);
        String te = String.format(Locale.getDefault(), "%.2f", d);

        binding.todayEarning.setText("$" + te);

        getData();

        Constants.databaseReference().child("users").child(Constants.auth().getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        UserModel model = snapshot.getValue(UserModel.class);
                        binding.username.setText(model.getUsername());
                        //binding.totalAssetsCount.setText("$" + model.getAssets());
                        String a = String.format(Locale.getDefault(), "%.2f", model.getAssets());
                        binding.promotionBonus.setText("$" + a);
                        String s = String.format(Locale.getDefault(), "%.2f", model.getDeposit());
                        binding.depositAmount.setText("$" + s);
                        double dep = model.getDeposit();
                        double por = model.getAssets();
                        float tot = (float) (dep + por);
                        String t = String.format(Locale.getDefault(), "%.2f", tot);
                        binding.totalAssetsCount.setText("$" + t);
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
                Date d = new Date();
                SimpleDateFormat format = new SimpleDateFormat("HH:mm aa");
                String a = format.format(d);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String mDate = dateFormat.format(d);
                if (Stash.getBoolean((mDate + task.getUid()), true)) {

                    int cur = Integer.parseInt(a.substring(0, 2));
                    if (cur >= 9 || cur == 0) {
                        assets = Float.parseFloat(binding.totalAssetsCount.getText().toString().substring(1));
                        if (task.getName().equalsIgnoreCase("captcha")) {
                            Intent i = new Intent(context, CaptchaTaskActivity.class);
                            i.putExtra("assets", assets);
                            i.putExtra("amount", task.getAmount());
                            i.putExtra("uid", task.getUid());
                            i.putExtra("income", task.getIncome());
                            i.putExtra("total", task.getTotal());
                            startActivity(i);
                        } else if (task.getName().equalsIgnoreCase("Translate Text")) {
                            Intent i = new Intent(context, TranslateTaskActivity.class);
                            i.putExtra("assets", assets);
                            i.putExtra("amount", task.getAmount());
                            i.putExtra("income", task.getIncome());
                            i.putExtra("uid", task.getUid());
                            i.putExtra("total", task.getTotal());
                            startActivity(i);
                        } else if (task.getName().equalsIgnoreCase("Amazon")) {
                            Intent i = new Intent(context, AmazonTaskActivity.class);
                            i.putExtra("assets", assets);
                            i.putExtra("amount", task.getAmount());
                            i.putExtra("income", task.getIncome());
                            i.putExtra("uid", task.getUid());
                            i.putExtra("total", task.getTotal());
                            startActivity(i);
                        }
                    } else {
                        Toast.makeText(context, "You can only do your task between 9:00 Am to 12:00 AM", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "You can perform a task once a day", Toast.LENGTH_SHORT).show();
                }
            } else {
                assets = Float.parseFloat(binding.depositAmount.getText().toString().substring(1));
                dialog = new UnlockDialog((Activity) context, task, list, adapter, assets);
                dialog.show();
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }
        }
    };

    private void getData() {

        Constants.databaseReference().child("userTasks").child(Constants.auth().getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            list.clear();
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                TasksModel model = dataSnapshot.getValue(TasksModel.class);
                                list.add(model);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                    Collections.sort(list, Comparator.comparing(TasksModel::getIncome));
                                }
                            }
                            if (adapter != null)
                                adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        /*String uid = UUID.randomUUID().toString();
        TasksModel model1 = new TasksModel(uid, "Captcha", 50, 1.25F, R.drawable.captcha, true, 30);
        database.TaskDao().insert(model1);
        String uid2 = UUID.randomUUID().toString();
        TasksModel model2 = new TasksModel(uid2, "Amazon", 100, 2.5F, R.drawable.amazon, true, 10);
        database.TaskDao().insert(model2);
        String uid3 = UUID.randomUUID().toString();
        TasksModel model3 = new TasksModel(uid3, "Translate Text", 300, 3.75F, R.drawable.translate_logo, true, 10);
        database.TaskDao().insert(model3);
        String uid4 = UUID.randomUUID().toString();
        TasksModel model4 = new TasksModel(uid4, "Captcha", 500, 5F, R.drawable.captcha, true, 40);
        database.TaskDao().insert(model4);
        String uid5 = UUID.randomUUID().toString();
        TasksModel model5 = new TasksModel(uid5, "Amazon", 800, 6.25F, R.drawable.amazon, true, 15);
        database.TaskDao().insert(model5);
        String uid6 = UUID.randomUUID().toString();
        TasksModel model6 = new TasksModel(uid6, "Translate Text", 1500, 7.5F, R.drawable.translate_logo, true, 15);
        database.TaskDao().insert(model6);
        String uid7 = UUID.randomUUID().toString();
        TasksModel model7 = new TasksModel(uid7, "Captcha", 3000, 8.75F, R.drawable.captcha, true, 45);
        database.TaskDao().insert(model7);
        String uid8 = UUID.randomUUID().toString();
        TasksModel model8 = new TasksModel(uid8, "Amazon", 5000, 10F, R.drawable.amazon, true, 20);
        database.TaskDao().insert(model8);
        String uid9 = UUID.randomUUID().toString();
        TasksModel model9 = new TasksModel(uid9, "Translate Text", 7500, 11.25F, R.drawable.translate_logo, true, 20);
        database.TaskDao().insert(model9);
        String uid10 = UUID.randomUUID().toString();
        TasksModel model10 = new TasksModel(uid10, "Captcha", 10000, 12.5F, R.drawable.captcha, true, 50);
        database.TaskDao().insert(model10);
        new Handler().postDelayed(() -> {
        }, 200);
        list.clear();
        list.addAll(database.TaskDao().getAll());
        if (adapter != null)
            adapter.notifyDataSetChanged();*/
    }
}