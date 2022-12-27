package com.moutamid.sra;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        findViewById(R.id.back).setOnClickListener(v -> {
            onBackPressed();
            finish();
        });
    }
}