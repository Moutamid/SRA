package com.moutamid.sra;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Toast;

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
        Constants.checkApp(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        boolean first = Stash.getBoolean("first", false);

        Toast.makeText(this, ""+first, Toast.LENGTH_SHORT).show();

        if (!first){
            Constants.databaseReference().child("users").child(Constants.auth().getCurrentUser().getUid())
                    .get()
                    .addOnSuccessListener(dataSnapshot -> {
                        UserModel model = dataSnapshot.getValue(UserModel.class);
                        double assets = model.getAssets();
                        if (!model.isReceivePrice()){
                            Map<String, Object> update = new HashMap<>();
                            update.put("assets", assets + 5);
                            update.put("promotionValue", 5);
                            //update.put("receivePrice", true);
                            Constants.databaseReference().child("users").child(Constants.auth().getCurrentUser().getUid())
                                    .updateChildren(update)
                                    .addOnSuccessListener(unused -> {
                                        Stash.put("first", true);
                                        progressDialog.dismiss();
                                        // updateReferal(model.getInvitationCode());
                                    }).addOnFailureListener(e -> {
                                        progressDialog.dismiss();
                                    });
                        } else {
                            progressDialog.dismiss();
                        }

                    }).addOnFailureListener(e -> {
                        progressDialog.dismiss();
                    });
        } else {
        new Handler().postDelayed(() -> progressDialog.dismiss(), 6000);
    }

        binding.bottomNavigation.setOnNavigationItemSelectedListener(this);
        binding.bottomNavigation.setSelectedItemId(R.id.nav_home);
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