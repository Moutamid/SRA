package com.moutamid.sra;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.moutamid.sra.databinding.ActivityCaptchaTaskBinding;
import com.moutamid.sra.models.CaptchaModel;
import com.moutamid.sra.utils.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CaptchaTaskActivity extends AppCompatActivity {
    ActivityCaptchaTaskBinding binding;
    ArrayList<CaptchaModel> list;
    int i = 0;
    int assets, income;
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
        income = getIntent().getIntExtra("income", 0);

        list = new ArrayList<>();

        addCaptcha();

        binding.back.setOnClickListener(v -> {
            onBackPressed();
            finish();
        });

        binding.counter.setText("Total Captcha Completed : " + i + "/"+ list.size());
        binding.image.setImageResource(list.get(i).getImage());

        binding.btnNext.setOnClickListener(v -> {
            if ((binding.captcha.getText().toString().length() >= 4 && binding.captcha.getText().toString().length() <= 6) &&
                !binding.captcha.getText().toString().isEmpty()) {
                if (binding.captcha.getText().toString().equals(list.get(i).getAnswer())) {
                    ++i;
                    if (i < list.size()) {
                        binding.counter.setText("Total Captcha Completed : " + i + "/" + list.size());
                        binding.image.setImageResource(list.get(i).getImage());
                        binding.captcha.setText("");
                    } else {
                        progressDialog.show();
                        Map<String, Object> map = new HashMap<>();
                        map.put("assets", (assets + income));
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
                    binding.captcha.setError("Please enter the correct captcha");
                    Toast.makeText(this, "Please enter the correct captcha", Toast.LENGTH_SHORT).show();
                }
            } else {
                binding.captcha.setError("Try Again");
            }
        });

    }

    public void addCaptcha() {
        CaptchaModel model1 = new CaptchaModel("HAASBA" , R.drawable.i1);
        list.add(model1);

        CaptchaModel model2 = new CaptchaModel("D6DA" , R.drawable.i2);
        list.add(model2);

        CaptchaModel model3 = new CaptchaModel("NTC5" , R.drawable.i3);
        list.add(model3);

        CaptchaModel model4 = new CaptchaModel("XY48W" , R.drawable.i4);
        list.add(model4);

        CaptchaModel model5 = new CaptchaModel("H5MAY" , R.drawable.i5);
        list.add(model5);

        CaptchaModel model6 = new CaptchaModel("4MBMPM" , R.drawable.i6);
        list.add(model6);

        CaptchaModel model7 = new CaptchaModel("BBN9C" , R.drawable.i7);
        list.add(model7);

        CaptchaModel model8 = new CaptchaModel("4VJR" , R.drawable.i8);
        list.add(model8);

        CaptchaModel model9 = new CaptchaModel("S8YWJB" , R.drawable.i9);
        list.add(model9);

        CaptchaModel model10 = new CaptchaModel("49T5" , R.drawable.i10);
        list.add(model10);

        CaptchaModel model11 = new CaptchaModel("PBHMV" , R.drawable.i11);
        list.add(model11);

        CaptchaModel model12 = new CaptchaModel("D6DA" , R.drawable.i12);
        list.add(model12);

        CaptchaModel model13 = new CaptchaModel("NTC5" , R.drawable.i13);
        list.add(model13);

        CaptchaModel model14 = new CaptchaModel("XY48W" , R.drawable.i14);
        list.add(model14);

        CaptchaModel model15 = new CaptchaModel("H5MAY" , R.drawable.i15);
        list.add(model15);

        CaptchaModel model16 = new CaptchaModel("E536" , R.drawable.i16);
        list.add(model16);

        CaptchaModel model17 = new CaptchaModel("DJXK54" , R.drawable.i17);
        list.add(model17);

        CaptchaModel model18 = new CaptchaModel("UH63WE" , R.drawable.i18);
        list.add(model18);

        CaptchaModel model19 = new CaptchaModel("SPJ9B" , R.drawable.i19);
        list.add(model19);

        CaptchaModel model20 = new CaptchaModel("EC99P" , R.drawable.i20);
        list.add(model20);

        CaptchaModel model21 = new CaptchaModel("5NPUJ" , R.drawable.i21);
        list.add(model21);

        CaptchaModel model22 = new CaptchaModel("9VAY5" , R.drawable.i22);
        list.add(model22);

        CaptchaModel model23 = new CaptchaModel("X983" , R.drawable.i23);
        list.add(model23);

        CaptchaModel model24 = new CaptchaModel("3CYT" , R.drawable.i24);
        list.add(model24);

        CaptchaModel model25 = new CaptchaModel("RS6AT4" , R.drawable.i25);
        list.add(model25);

        CaptchaModel model26 = new CaptchaModel("RJDUT" , R.drawable.i26);
        list.add(model26);

        CaptchaModel model27 = new CaptchaModel("BJD6WC" , R.drawable.i27);
        list.add(model27);

        CaptchaModel model28 = new CaptchaModel("DVX3" , R.drawable.i28);
        list.add(model28);

        CaptchaModel model29 = new CaptchaModel("RDB9XT" , R.drawable.i29);
        list.add(model29);

        CaptchaModel model30 = new CaptchaModel("NAA8" , R.drawable.i30);
        list.add(model30);

        CaptchaModel model31 = new CaptchaModel("RP4D49" , R.drawable.i31);
        list.add(model31);

        CaptchaModel model32 = new CaptchaModel("YED5BP" , R.drawable.i32);
        list.add(model32);

        CaptchaModel model33 = new CaptchaModel("KXCY" , R.drawable.i33);
        list.add(model33);

        CaptchaModel model34 = new CaptchaModel("9DR8HN" , R.drawable.i34);
        list.add(model34);

        CaptchaModel model35 = new CaptchaModel("PWWN" , R.drawable.i35);
        list.add(model35);

        CaptchaModel model36 = new CaptchaModel("JBXJV" , R.drawable.i36);
        list.add(model36);

        CaptchaModel model37 = new CaptchaModel("VYYA" , R.drawable.i37);
        list.add(model37);

        CaptchaModel model38 = new CaptchaModel("DX3NM" , R.drawable.i38);
        list.add(model38);

        CaptchaModel model39 = new CaptchaModel("JVUJAA" , R.drawable.i39);
        list.add(model39);

        CaptchaModel model40 = new CaptchaModel("T8SX" , R.drawable.i40);
        list.add(model40);

        CaptchaModel model41 = new CaptchaModel("3TB6U" , R.drawable.i41);
        list.add(model41);

        CaptchaModel model42 = new CaptchaModel("WH5MY" , R.drawable.i42);
        list.add(model42);

        CaptchaModel model43 = new CaptchaModel("5Y3E" , R.drawable.i43);
        list.add(model43);

        CaptchaModel model44 = new CaptchaModel("EAJVS9" , R.drawable.i44);
        list.add(model44);

        CaptchaModel model45 = new CaptchaModel("DRJTDW" , R.drawable.i45);
        list.add(model45);

        CaptchaModel model46 = new CaptchaModel("PKR4KU" , R.drawable.i46);
        list.add(model46);

        CaptchaModel model47 = new CaptchaModel("935N4" , R.drawable.i47);
        list.add(model47);

        CaptchaModel model48 = new CaptchaModel("UP36JP" , R.drawable.i48);
        list.add(model48);

        CaptchaModel model49 = new CaptchaModel("EK5TPS" , R.drawable.i49);
        list.add(model49);

        CaptchaModel model50 = new CaptchaModel("H5TH" , R.drawable.i50);
        list.add(model50);

        Collections.shuffle(list);
    }
}