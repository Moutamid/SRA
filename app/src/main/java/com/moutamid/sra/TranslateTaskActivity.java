package com.moutamid.sra;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.moutamid.sra.databinding.ActivityTranslateTaskBinding;
import com.moutamid.sra.models.TranslateModel;
import com.moutamid.sra.utils.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TranslateTaskActivity extends AppCompatActivity {
    ActivityTranslateTaskBinding binding;
    int i = 0;
    int assets;
    ProgressDialog progressDialog;
    ArrayList<TranslateModel> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTranslateTaskBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please wait...");

        assets = getIntent().getIntExtra("assets", 0);

        list = new ArrayList<>();

        getData();

        binding.counter.setText("Total Completed : " + i + "/"+ list.size());

        binding.original.setText(list.get(i).getOriginal());

        binding.back.setOnClickListener(v -> {
            onBackPressed();
            finish();
        });

        binding.btnNext.setOnClickListener(v -> {
            if (!binding.translate.getText().toString().isEmpty()) {
                if (binding.translate.getText().toString().equalsIgnoreCase(list.get(i).getTranslated())) {
                    ++i;
                    if (i < list.size()) {
                        binding.counter.setText("Total Completed : " + i + "/" + list.size());
                        binding.original.setText(list.get(i).getOriginal());
                        binding.translate.setText("");
                    } else {
                        progressDialog.show();
                        Map<String, Object> map = new HashMap<>();
                        map.put("assets", assets + 5);
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
                    binding.translate.requestFocus();
                    Toast.makeText(this, "Translation doesn't match", Toast.LENGTH_SHORT).show();
                }
            } else {
                binding.translate.setError("*Required");
            }
        });

    }

    private void getData() {
        TranslateModel model1 = new TranslateModel(getResources().getString(R.string.translate_text_1_O), getResources().getString(R.string.translate_text_1_T));
        list.add(model1);

        TranslateModel model2 = new TranslateModel(getResources().getString(R.string.translate_text_2_O), getResources().getString(R.string.translate_text_2_T));
        list.add(model2);

        TranslateModel model3 = new TranslateModel(getResources().getString(R.string.translate_text_3_O), getResources().getString(R.string.translate_text_3_T));
        list.add(model3);

        TranslateModel model4 = new TranslateModel(getResources().getString(R.string.translate_text_4_O), getResources().getString(R.string.translate_text_4_T));
        list.add(model4);

        TranslateModel model5 = new TranslateModel(getResources().getString(R.string.translate_text_5_O), getResources().getString(R.string.translate_text_5_T));
        list.add(model5);

        Collections.shuffle(list);
    }
}