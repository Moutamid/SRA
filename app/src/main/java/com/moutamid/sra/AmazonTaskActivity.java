package com.moutamid.sra;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.moutamid.sra.databinding.ActivityAmazonTaskBinding;

import java.util.ArrayList;

public class AmazonTaskActivity extends AppCompatActivity {
    ActivityAmazonTaskBinding binding;int i = 0;
    int assets;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAmazonTaskBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please wait...");

        assets = getIntent().getIntExtra("assets", 0);

        binding.back.setOnClickListener(v -> {
            onBackPressed();
            finish();
        });
    }
}