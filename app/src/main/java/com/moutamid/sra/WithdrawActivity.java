package com.moutamid.sra;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import com.moutamid.sra.databinding.ActivityWithdrawBinding;
import com.moutamid.sra.models.UserModel;
import com.moutamid.sra.models.WithdrawRequestModel;
import com.moutamid.sra.utils.Constants;

import java.util.Date;
import java.util.UUID;

public class WithdrawActivity extends AppCompatActivity {
    ActivityWithdrawBinding binding;
    String amount;
    int assets;
    boolean isValid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWithdrawBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Constants.databaseReference().child("users").child(Constants.auth().getCurrentUser().getUid())
                .get().addOnSuccessListener(dataSnapshot -> {
                    progressDialog.dismiss();
                    assets = dataSnapshot.getValue(UserModel.class).getAssets();
                    binding.assets.setText("$"+assets);
                }).addOnFailureListener(e -> {

                });

        binding.amount.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int i = 0;
                if (!s.toString().isEmpty()){
                    i = Integer.parseInt(s.toString());
                    int r = assets - i;
                    if (r >= 0) {
                        binding.assets.setText("$" + r);
                        isValid = true;
                    } else {
                        binding.assets.setText("Enter Valid Amount");
                        isValid = false;
                    }
                } else {
                    binding.assets.setText("$" + assets);
                    isValid = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.back.setOnClickListener(v -> {
            onBackPressed();
            finish();
        });

        binding.btnSend.setOnClickListener(v -> {
            if (validate()){
                if (isValid){
                    progressDialog.show();
                    String uid = UUID.randomUUID().toString();
                    Date d = new Date();
                    WithdrawRequestModel model = new WithdrawRequestModel(uid,
                            binding.hashkey.getEditText().getText().toString(),
                            Constants.auth().getCurrentUser().getUid(),
                            Integer.parseInt(binding.amount.getEditText().getText().toString()), d.getTime(), "PEN", "WITH");
                    Constants.databaseReference().child("withdrawRequests").child(Constants.auth().getCurrentUser().getUid())
                            .child(uid).setValue(model).addOnSuccessListener(unused -> {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Request Send Successfully", Toast.LENGTH_SHORT).show();
                                onBackPressed();
                                finish();
                            }).addOnFailureListener(e -> {
                                progressDialog.dismiss();
                            });
                }
            }
        });

    }

    private boolean validate() {
        if (binding.hashkey.getEditText().getText().toString().isEmpty()){
            Toast.makeText(this, "Enter Your HashKey", Toast.LENGTH_SHORT).show();
            return false;
        } if (binding.amount.getEditText().getText().toString().isEmpty()){
            Toast.makeText(this, "Enter Your Amount", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}