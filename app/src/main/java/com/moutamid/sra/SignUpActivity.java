package com.moutamid.sra;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.widget.Toast;

import com.moutamid.sra.databinding.ActivitySignUpBinding;
import com.moutamid.sra.models.TasksModel;
import com.moutamid.sra.models.UserModel;
import com.moutamid.sra.utils.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

public class SignUpActivity extends AppCompatActivity {
    ActivitySignUpBinding binding;
    ProgressDialog progressDialog;
    List<TasksModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Creating Your Account...");

        list = new ArrayList<>();

        binding.login.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
        });

        binding.support.setOnClickListener(v -> {
            Uri uri = new Uri.Builder().scheme("http").authority("telegram.me").appendEncodedPath("+13366851283").build();
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, uri).setPackage("org.telegram.messenger"));
            } catch (Exception e){
                e.printStackTrace();
            }
        });

        binding.btnRegister.setOnClickListener(v -> {
            if (validate()){
                progressDialog.show();
                Constants.auth().createUserWithEmailAndPassword(
                       (binding.username.getEditText().getText().toString().toLowerCase(Locale.ROOT).trim() + "@sra.com"),
                        binding.password.getEditText().getText().toString()
                ).addOnSuccessListener(authResult -> {
                    // long referralCode = (long) Math.floor(Math.random() * (999999999999L-100000+1)+100000);
                    UserModel userModel = new UserModel(
                            ""+Constants.auth().getCurrentUser().getUid(),
                            ""+binding.username.getEditText().getText().toString().toLowerCase(Locale.ROOT),
                            binding.username.getEditText().getText().toString().toLowerCase(Locale.ROOT).trim() + "@sra.com",
                            ""+binding.password.getEditText().getText().toString(),
                            ""+binding.whatsapp.getEditText().getText().toString(),
                            ""+binding.invitationCode.getEditText().getText().toString(),
                            0, 0.0, 0.0, 0.0, false
                    );
                    Constants.databaseReference().child("users").child(Constants.auth().getCurrentUser().getUid())
                            .setValue(userModel).addOnSuccessListener(unused -> {
                                addUser();
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

    private void addUser() {
        Map<String, String> map = new HashMap<>();
        map.put("key", Constants.auth().getCurrentUser().getUid());
        Constants.databaseReference().child("team").child(binding.invitationCode.getEditText().getText().toString())
                .push().setValue(map).addOnSuccessListener(unused -> {
                    addtask();
                }).addOnFailureListener(e -> {
                    progressDialog.dismiss();
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    private void addtask() {
        getData();
        for (int i =0; i<list.size(); i++){
            int finalI = i;
            Constants.databaseReference().child("userTasks").child(Objects.requireNonNull(Constants.auth().getCurrentUser()).getUid()).child(list.get(i).getUid())
                    .setValue(list.get(i)).addOnSuccessListener(unused -> {
                        if (finalI ==list.size()-1){
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "User Created", Toast.LENGTH_SHORT).show();
                            Constants.auth().signOut();
                            startActivity(new Intent(this, LoginActivity.class));
                        }
                    }).addOnFailureListener(e -> {
                        e.printStackTrace();
                    });
        }
    }

    private void getData() {
        String uid = UUID.randomUUID().toString();
        TasksModel model1 = new TasksModel(uid, "Captcha", 50, 1.25F, R.drawable.captcha, true, 30);
        list.add(model1);
        String uid2 = UUID.randomUUID().toString();
        TasksModel model2 = new TasksModel(uid2,"Amazon", 100, 2.5F, R.drawable.amazon, true, 10);
        list.add(model2);
        String uid3 = UUID.randomUUID().toString();
        TasksModel model3 = new TasksModel(uid3,"Translate Text", 300, 3.75F, R.drawable.translate_logo, true, 10);
        list.add(model3);
        String uid4 = UUID.randomUUID().toString();
        TasksModel model4 = new TasksModel(uid4,"Captcha", 500, 5.0F, R.drawable.captcha, true, 40);
        list.add(model4);
        String uid5 = UUID.randomUUID().toString();
        TasksModel model5 = new TasksModel(uid5,"Amazon", 800, 6.25F, R.drawable.amazon, true, 15);
        list.add(model5);
        String uid6 = UUID.randomUUID().toString();
        TasksModel model6 = new TasksModel(uid6,"Translate Text", 1500, 7.5F, R.drawable.translate_logo, true, 15);
        list.add(model6);
        String uid7 = UUID.randomUUID().toString();
        TasksModel model7 = new TasksModel(uid7, "Captcha", 3000, 8.75F, R.drawable.captcha, true, 45);
        list.add(model7);
        String uid8 = UUID.randomUUID().toString();
        TasksModel model8 = new TasksModel(uid8,"Amazon", 5000, 10.0F, R.drawable.amazon, true, 20);
        list.add(model8);
        String uid9 = UUID.randomUUID().toString();
        TasksModel model9 = new TasksModel(uid9,"Translate Text", 7500, 11.25F, R.drawable.translate_logo, true, 20);
        list.add(model9);
        String uid10 = UUID.randomUUID().toString();
        TasksModel model10 = new TasksModel(uid10, "Captcha", 10000, 12.5F, R.drawable.captcha, true, 50);
        list.add(model10);
        new Handler().postDelayed(()->{},200);
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