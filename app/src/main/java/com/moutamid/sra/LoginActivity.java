package com.moutamid.sra;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.moutamid.sra.databinding.ActivityLoginBinding;
import com.moutamid.sra.utils.Constants;

import java.util.Locale;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Logging Into Your Account...");

        binding.register.setOnClickListener(v -> {
            startActivity(new Intent(this, SignUpActivity.class));
        });

        binding.support.setOnClickListener(v -> {
            Uri uri = new Uri.Builder().scheme("http").authority("telegram.me").appendEncodedPath("+13366851283").build();
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, uri).setPackage("org.telegram.messenger"));
            } catch (Exception e){
                e.printStackTrace();
            }
        });

        binding.btnLogin.setOnClickListener(v -> {
            if (validate()){
                progressDialog.show();
                Constants.auth().signInWithEmailAndPassword(
                        (binding.username.getEditText().getText().toString().toLowerCase(Locale.ROOT).trim() + "@sra.com"),
                        binding.password.getEditText().getText().toString()
                ).addOnSuccessListener(authResult -> {
                    progressDialog.dismiss();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }).addOnFailureListener(e -> {
                    progressDialog.dismiss();
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
        });
    }

    private boolean validate() {
        if (binding.username.getEditText().getText().toString().isEmpty()){
            Toast.makeText(this, "Username is Required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.password.getEditText().getText().toString().isEmpty()){
            Toast.makeText(this, "Password is Required", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}