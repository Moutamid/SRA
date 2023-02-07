package com.moutamid.sra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.moutamid.sra.adapter.TeamAdapter;
import com.moutamid.sra.databinding.ActivityMyTeamBinding;
import com.moutamid.sra.models.TeamModel;
import com.moutamid.sra.models.UserModel;
import com.moutamid.sra.utils.Constants;

import java.util.ArrayList;

public class MyTeamActivity extends AppCompatActivity {
    ActivityMyTeamBinding binding;
    ArrayList<TeamModel> teamList;
    TeamAdapter adapter;
    ArrayList<String> teamKeys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyTeamBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        teamList = new ArrayList<>();
        teamKeys = new ArrayList<>();

        binding.back.setOnClickListener(v -> {onBackPressed(); finish(); });

        String key = getIntent().getStringExtra("key");
        String name = getIntent().getStringExtra("name");

        //Toast.makeText(this, name + "  " + key, Toast.LENGTH_SHORT).show();

        binding.toolbarTittle.setText(name);

        binding.teamRC.setLayoutManager(new LinearLayoutManager(this));
        binding.teamRC.setHasFixedSize(false);


        Constants.databaseReference().child("team").child(key)
                .get().addOnSuccessListener(dataSnapshot -> {
                    teamKeys.clear();
                    for (DataSnapshot snapshot1 : dataSnapshot.getChildren()) {
                        TeamModel k = snapshot1.getValue(TeamModel.class);
                        teamKeys.add(k.getKey());
                    }
                    //Toast.makeText(this, ""+teamKeys.size(), Toast.LENGTH_SHORT).show();
                    updateList();
                }).addOnFailureListener(e -> {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                });

    }

    private void updateList() {
        for (String key : teamKeys) {
            Constants.databaseReference().child("users").child(key).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    UserModel user = snapshot.getValue(UserModel.class);
                    teamList.add(new TeamModel(user.getID(), user.getUsername(), user.getAssets()));
                    adapter = new TeamAdapter(MyTeamActivity.this, teamList);
                    binding.teamRC.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
}