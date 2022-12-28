package com.moutamid.sra;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.moutamid.sra.databinding.ActivityCaptchaTaskBinding;
import com.moutamid.sra.utils.Constants;

import java.util.HashMap;
import java.util.Map;

public class CaptchaTaskActivity extends AppCompatActivity {
    ActivityCaptchaTaskBinding binding;
    int[] captchaImages = {
            R.drawable.i1, R.drawable.i2, R.drawable.i3, R.drawable.i4, R.drawable.i5,
            R.drawable.i6, R.drawable.i7, R.drawable.i8, R.drawable.i9, R.drawable.i10,
            R.drawable.i11, R.drawable.i2, R.drawable.i3, R.drawable.i4, R.drawable.i5,
            R.drawable.i16, R.drawable.i17, R.drawable.i18, R.drawable.i19, R.drawable.i20,
            R.drawable.i21, R.drawable.i22, R.drawable.i23, R.drawable.i24, R.drawable.i25
    };
    int i = 0;
    int assets;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCaptchaTaskBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please wait...");

        assets = getIntent().getIntExtra("assets", 0);

        binding.back.setOnClickListener(v -> {
            onBackPressed();
            finish();
        });

        binding.image.setImageResource(captchaImages[i]);

        binding.btnNext.setOnClickListener(v -> {
            if ((binding.captcha.getText().toString().length() >= 4 && binding.captcha.getText().toString().length() <= 6) &&
                !binding.captcha.getText().toString().isEmpty()){
                ++i;
                if (i < captchaImages.length){
                    binding.image.setImageResource(captchaImages[i]);
                } else {
                    progressDialog.show();
                    Map<String, Object> map = new HashMap<>();
                    map.put("assets", assets+1);
                    Constants.databaseReference().child("users").child(Constants.auth().getCurrentUser().getUid())
                            .updateChildren(map).addOnSuccessListener(unused -> {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Task Completed", Toast.LENGTH_SHORT).show();
                                onBackPressed();
                                finish();
                            }).addOnFailureListener(e -> {
                                progressDialog.dismiss();
                            });
                }
            } else {
                binding.captcha.setError("Try Again");
            }
        });

    }
}