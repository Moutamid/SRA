package com.moutamid.sra;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.moutamid.sra.databinding.ActivityCaptchaTaskBinding;

public class CaptchaTaskActivity extends AppCompatActivity {
    ActivityCaptchaTaskBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCaptchaTaskBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}