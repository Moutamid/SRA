package com.moutamid.sra;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Toast;

import com.moutamid.sra.databinding.ActivitySignUpBinding;
import com.moutamid.sra.models.UserModel;
import com.moutamid.sra.utils.Constants;

import java.util.Locale;
import java.util.Random;

public class SignUpActivity extends AppCompatActivity {
    ActivitySignUpBinding binding;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Creating You Account...");

        binding.login.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
        });

        binding.support.setOnClickListener(v -> {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(String.format("https://api.whatsapp.com/send?phone=%s&text=%s",
                            "+14375380650", ""))));
        });

        binding.btnRegister.setOnClickListener(v -> {
            if (validate()){
                progressDialog.show();
                Constants.auth().createUserWithEmailAndPassword(
                       (binding.username.getEditText().getText().toString().toLowerCase(Locale.ROOT).trim() + "@sra.com"),
                        binding.password.getEditText().getText().toString()
                ).addOnSuccessListener(authResult -> {
                    long referralCode = (long) Math.floor(Math.random() * (999999999999L-100000+1)+100000);
                    UserModel userModel = new UserModel(
                            Constants.auth().getCurrentUser().getUid(),
                            binding.username.getEditText().getText().toString().toLowerCase(Locale.ROOT),
                            binding.username.getEditText().getText().toString().toLowerCase(Locale.ROOT).trim() + "@sra.com",
                            binding.password.getEditText().getText().toString(),
                            binding.whatsapp.getEditText().getText().toString(),
                            String.valueOf(referralCode),
                            binding.invitationCode.getEditText().getText().toString(),
                            5, false
                    );
                    Constants.databaseReference().child("users").child(Constants.auth().getCurrentUser().getUid())
                            .setValue(userModel).addOnSuccessListener(unused -> {
                                Toast.makeText(getApplicationContext(), "User Created", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(this, LoginActivity.class));
                            }).addOnFailureListener(e -> {
                                progressDialog.dismiss();
                                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            });
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
        if (binding.password.getEditText().getText().toString().length() < 6 || !binding.password.getEditText().getText().toString().matches("^(?=.*[0-9])(?=.*[a-zA-Z])[a-zA-Z0-9]+$")){
            Toast.makeText(this, "Password is not valid", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.cnfrmPassword.getEditText().getText().toString().isEmpty()){
            Toast.makeText(this, "Please re-enter your Password", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.whatsapp.getEditText().getText().toString().isEmpty()){
            Toast.makeText(this, "Whatsapp account is Required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.invitationCode.getEditText().getText().toString().isEmpty()){
            Toast.makeText(this, "Invitation Code is Required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!binding.cnfrmPassword.getEditText().getText().toString().equals(binding.password.getEditText().getText().toString())){
            Toast.makeText(this, "Password doesn't match", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}