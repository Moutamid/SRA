package com.moutamid.sra;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.moutamid.sra.databinding.ActivityMainBinding;
import com.moutamid.sra.fragments.HistoryFragment;
import com.moutamid.sra.fragments.HomeFragment;
import com.moutamid.sra.fragments.ProfileFragment;
import com.moutamid.sra.models.UserModel;
import com.moutamid.sra.utils.Constants;

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
                        update.put("assets", 25);
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
        Constants.databaseReference().child("users").child(ID)
                .get().addOnSuccessListener(dataSnapshot -> {
                    UserModel model = dataSnapshot.getValue(UserModel.class);
                    double assets = 0;
                    try {
                        assets = model.getAssets();
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    update.put("assets", assets + 25);
                    Constants.databaseReference().child("users").child(ID)
                            .updateChildren(update).addOnSuccessListener(unused -> {
                                progressDialog.dismiss();
                            }).addOnFailureListener(e -> {

                            });
                }).addOnFailureListener(e -> {
                    progressDialog.dismiss();
                });
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
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse(String.format("https://api.whatsapp.com/send?phone=%s&text=%s",
                                "+14375380650", ""))));
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
    }
}