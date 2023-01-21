package com.moutamid.sra;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.fxn.stash.Stash;
import com.moutamid.sra.databinding.ActivityTranslateTaskBinding;
import com.moutamid.sra.models.TranslateModel;
import com.moutamid.sra.utils.Constants;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TranslateTaskActivity extends AppCompatActivity {
    ActivityTranslateTaskBinding binding;
    int i = 0;
    float d,percentage, assets;
    float income;
    String mDate;
    ProgressDialog progressDialog;
    ArrayList<TranslateModel> list;
    int totalListSize;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTranslateTaskBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please wait...");

        assets = getIntent().getFloatExtra("assets", 0.0F);
        income = getIntent().getFloatExtra("income", 0.0F);
        totalListSize = getIntent().getIntExtra("total", 0);

        percentage = (income/100)*assets;

        list = new ArrayList<>();

        getData();

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        mDate = format.format(date);
        d = Stash.getFloat(mDate, 0.0F);

        binding.counter.setText("Total Completed : " + i + "/"+ totalListSize);

        binding.original.setText(list.get(i).getOriginal());

        binding.back.setOnClickListener(v -> {
            onBackPressed();
            finish();
        });

        binding.btnNext.setOnClickListener(v -> {
            if (!binding.translate.getText().toString().isEmpty()) {
                if (binding.translate.getText().toString().contains(list.get(i).getTranslated())) {
                    ++i;
                    if (i < totalListSize) {
                        binding.counter.setText("Total Completed : " + i + "/" + totalListSize);
                        binding.original.setText(list.get(i).getOriginal());
                        binding.translate.setText("");
                    } else {
                        progressDialog.show();
                        d = (float) (d + percentage);
                        Map<String, Object> map = new HashMap<>();
                        map.put("assets", (assets + percentage));
                        Stash.put(mDate, d);
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

        TranslateModel model6 = new TranslateModel(getResources().getString(R.string.translate_text_6_O), getResources().getString(R.string.translate_text_6_T));
        list.add(model6);

        TranslateModel model7 = new TranslateModel(getResources().getString(R.string.translate_text_7_O), getResources().getString(R.string.translate_text_7_T));
        list.add(model7);

        TranslateModel model8 = new TranslateModel(getResources().getString(R.string.translate_text_8_O), getResources().getString(R.string.translate_text_8_T));
        list.add(model8);

        TranslateModel model9 = new TranslateModel(getResources().getString(R.string.translate_text_9_O), getResources().getString(R.string.translate_text_9_T));
        list.add(model9);

        TranslateModel model10 = new TranslateModel(getResources().getString(R.string.translate_text_10_O), getResources().getString(R.string.translate_text_10_T));
        list.add(model10);

        TranslateModel model11 = new TranslateModel(getResources().getString(R.string.translate_text_11_O), getResources().getString(R.string.translate_text_11_T));
        list.add(model11);

        TranslateModel model12 = new TranslateModel(getResources().getString(R.string.translate_text_12_O), getResources().getString(R.string.translate_text_12_T));
        list.add(model12);

        TranslateModel model13 = new TranslateModel(getResources().getString(R.string.translate_text_13_O), getResources().getString(R.string.translate_text_13_T));
        list.add(model13);

        TranslateModel model14 = new TranslateModel(getResources().getString(R.string.translate_text_14_O), getResources().getString(R.string.translate_text_14_T));
        list.add(model14);

        TranslateModel model15 = new TranslateModel(getResources().getString(R.string.translate_text_15_O), getResources().getString(R.string.translate_text_15_T));
        list.add(model15);

        TranslateModel model16 = new TranslateModel(getResources().getString(R.string.translate_text_16_O), getResources().getString(R.string.translate_text_16_T));
        list.add(model16);

        TranslateModel model17 = new TranslateModel(getResources().getString(R.string.translate_text_17_O), getResources().getString(R.string.translate_text_17_T));
        list.add(model17);

        TranslateModel model18 = new TranslateModel(getResources().getString(R.string.translate_text_18_O), getResources().getString(R.string.translate_text_18_T));
        list.add(model18);

        TranslateModel model19 = new TranslateModel(getResources().getString(R.string.translate_text_19_O), getResources().getString(R.string.translate_text_19_T));
        list.add(model19);

        TranslateModel model20 = new TranslateModel(getResources().getString(R.string.translate_text_20_O), getResources().getString(R.string.translate_text_20_T));
        list.add(model20);

        Collections.shuffle(list);
    }
}