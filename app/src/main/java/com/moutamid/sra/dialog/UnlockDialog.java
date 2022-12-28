package com.moutamid.sra.dialog;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
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
import com.moutamid.sra.models.TasksModel;

import java.util.List;


public class UnlockDialog extends Dialog implements View.OnClickListener {
    public Activity c;
    public Dialog d;
    public Button yes, no, buy;
    TasksModel tasksModel;
    List<TasksModel> list;
    TasksAdapter adapter;
    TaskDB database;
    int assets;

    public UnlockDialog(Activity a, TasksModel tasksModel, List<TasksModel> list, TasksAdapter adapter, int assets) {
        super(a);
        this.c = a;
        this.tasksModel = tasksModel;
        this.list = list;
        this.assets = assets;
        this.adapter = adapter;
        database = TaskDB.getInstance(c);
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
                    /*category.setLockState(false);
                    database.CategoryDAO().update(category.getID(), category.getCategoryName(), category.isLockState(), category.getImage());
                    list.clear();
                    list.addAll(database.CategoryDAO().getAll());
                    adapter.notifyDataSetChanged();
                    coinN = coinN - 25;
                    coins.setText(""+coinN);
                    preferences.saveCoin(coinN);*/
                } else {
                    Toast.makeText(c.getApplicationContext(), "Not Enough Coins", Toast.LENGTH_SHORT).show();
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