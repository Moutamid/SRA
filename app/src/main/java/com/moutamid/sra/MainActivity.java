package com.moutamid.sra;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.fxn.stash.Stash;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.moutamid.sra.databinding.ActivityMainBinding;
import com.moutamid.sra.fragments.HistoryFragment;
import com.moutamid.sra.fragments.HomeFragment;
import com.moutamid.sra.fragments.ProfileFragment;
import com.moutamid.sra.models.UserModel;
import com.moutamid.sra.utils.Constants;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    ActivityMainBinding binding;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Constants.databaseReference().child("users").child(Constants.auth().getCurrentUser().getUid())
                        .get()
                .addOnSuccessListener(dataSnapshot -> {
                    UserModel model = dataSnapshot.getValue(UserModel.class);

                    if (!model.isReceivePrice()){
                        Map<String, Object> update = new HashMap<>();
                        update.put("assets", 5);
                        update.put("receivePrice", true);
                        Constants.databaseReference().child("users").child(Constants.auth().getCurrentUser().getUid())
                                .updateChildren(update)
                                .addOnSuccessListener(unused -> {
                                    updateReferal(model.getInvitationCode());
                                }).addOnFailureListener(e -> {
                                    progressDialog.dismiss();
                                });
                    } else {
                        progressDialog.dismiss();
                    }

                }).addOnFailureListener(e -> {
                    progressDialog.dismiss();
                });

        binding.bottomNavigation.setOnNavigationItemSelectedListener(this);
        binding.bottomNavigation.setSelectedItemId(R.id.nav_home);
    }

    private void updateReferal(String ID) {
        Map<String, Object> update = new HashMap<>();
        try {
            Constants.databaseReference().child("users").child(ID)
                    .get().addOnSuccessListener(dataSnapshot -> {
                        if (dataSnapshot.exists()) {
                            UserModel model = dataSnapshot.getValue(UserModel.class);
                            double assets = 0;
                            try {
                                assets = model.getAssets();
                            } catch (Exception e){
                                e.printStackTrace();
                            }
                            update.put("assets", assets + 5);
                            Constants.databaseReference().child("users").child(ID)
                                    .updateChildren(update).addOnSuccessListener(unused -> {
                                        progressDialog.dismiss();
                                    }).addOnFailureListener(e -> {

                                    });
                        }
                    }).addOnFailureListener(e -> {
                        progressDialog.dismiss();
                    });
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                binding.toolbarTittle.setText("Home");
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout , new HomeFragment()).commit();
                return true;

            case R.id.nav_history:
                binding.toolbarTittle.setText("History");
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new HistoryFragment()).commit();
                return true;

            case R.id.nav_support:
                Uri uri = new Uri.Builder().scheme("http").authority("telegram.me").appendEncodedPath("+13366851283").build();
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, uri).setPackage("org.telegram.messenger"));
                } catch (Exception e){
                    e.printStackTrace();
                }
                return true;

            case R.id.nav_profile:
                binding.toolbarTittle.setText("Profile");
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new ProfileFragment(binding.toolbarTittle, binding.bottomNavigation)).commit();
                return true;

        }
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.bottomNavigation.setSelectedItemId(R.id.nav_home);
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String mDate = format.format(date);
        float d = Stash.getFloat(mDate, 0.0F);
        if (d == 0.0F) {
            Stash.put(mDate, 0.0F);
        }
    }

}