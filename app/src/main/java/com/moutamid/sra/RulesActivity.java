package com.moutamid.sra;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class RulesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        findViewById(R.id.back).setOnClickListener(v -> {
            onBackPressed();
            finish();
        });

    }
}