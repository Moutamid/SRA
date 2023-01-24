package com.moutamid.sra.dialog;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.moutamid.sra.DepositActivity;
import com.moutamid.sra.R;
import com.moutamid.sra.adapter.TasksAdapter;
import com.moutamid.sra.database.TaskDB;
import com.moutamid.sra.models.Tasks;
import com.moutamid.sra.models.TasksModel;
import com.moutamid.sra.models.WithdrawRequestModel;
import com.moutamid.sra.utils.Constants;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


public class UnlockDialog extends Dialog implements View.OnClickListener {
    public Activity c;
    public Dialog d;
    public Button yes, no, buy;
    TasksModel tasksModel;
    List<TasksModel> list;
    TasksAdapter adapter;
    TaskDB database;
    float assets;
    ProgressDialog progressDialog;

    public UnlockDialog(Activity a, TasksModel tasksModel, List<TasksModel> list, TasksAdapter adapter, float assets) {
        super(a);
        this.c = a;
        this.tasksModel = tasksModel;
        this.list = list;
        this.assets = assets;
        this.adapter = adapter;
        database = TaskDB.getInstance(c);
        progressDialog = new ProgressDialog(a);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Sending Request...");
    }


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.unlock_task_dialog);

        yes = (Button) findViewById(R.id.btnYes);
        no = (Button) findViewById(R.id.btnCancel);
        buy = (Button) findViewById(R.id.btnBUY);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);
        buy.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnYes:
                if (assets >= tasksModel.getAmount()) {
                    progressDialog.show();

                    String uid = UUID.randomUUID().toString();
                    Date d = new Date();

                    Tasks task = new Tasks(uid, tasksModel.getUid(), tasksModel.getName(), tasksModel.getAmount(), tasksModel.getIncome(), tasksModel.isLock(), tasksModel.getTotal(), Constants.auth().getCurrentUser().getUid(), d.getTime(), "PEN", "TASK");

                    Constants.databaseReference().child("Request").child(Constants.auth().getCurrentUser().getUid())
                            .child(uid).setValue(task).addOnSuccessListener(unused -> {
                                progressDialog.dismiss();
                                Toast.makeText(c, "Request Sent", Toast.LENGTH_SHORT).show();
                            }).addOnFailureListener(e -> {
                                progressDialog.dismiss();
                            });

                    /*Map<String, Object> map = new HashMap<>();
                    map.put("deposit", assets - tasksModel.getAmount());
                    tasksModel.setLock(false);

                    Constants.databaseReference().child("users").child(Constants.auth().getCurrentUser().getUid())
                            .updateChildren(map).addOnSuccessListener(unused -> {
                                database.TaskDao().update(tasksModel.getId(), tasksModel.getName(), tasksModel.getAmount(), tasksModel.getIncome(), tasksModel.getImage(), tasksModel.isLock());
                                list.clear();
                                list.addAll(database.TaskDao().getAll());
                                adapter.notifyDataSetChanged();
                                progressDialog.dismiss();
                            }).addOnFailureListener(e -> {
                                progressDialog.dismiss();
                            });*/

                } else {
                    Toast.makeText(c.getApplicationContext(), "Not Enough Assets", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnCancel:
                dismiss();
                break;
            case R.id.btnBUY:
                c.startActivity(new Intent(c, DepositActivity.class));
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
}