package com.moutamid.sra;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.moutamid.sra.databinding.ActivityRulesBinding;
import com.moutamid.sra.utils.Constants;

public class RulesActivity extends AppCompatActivity {
    ActivityRulesBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRulesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        findViewById(R.id.back).setOnClickListener(v -> {
            onBackPressed();
            finish();
        });

        binding.rules.setText("");

        Constants.databaseReference().child("rules").get().addOnSuccessListener(dataSnapshot -> {
            String s = dataSnapshot.child("rules").getValue().toString();
            binding.rules.setText(s);
        }).addOnFailureListener(e -> {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        });

    }
}