package com.moutamid.sra.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.moutamid.sra.InviteFriendsActivity;
import com.moutamid.sra.R;
import com.moutamid.sra.databinding.FragmentHomeBinding;
import com.moutamid.sra.models.UserModel;
import com.moutamid.sra.utils.Constants;


public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;
    Context context;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        context = view.getContext();

        Constants.databaseReference().child("users").child(Constants.auth().getCurrentUser().getUid())
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                UserModel model = snapshot.getValue(UserModel.class);
                                binding.username.setText(model.getUsername());
                                binding.totalAssetsCount.setText("$" + model.getAssets());
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

        binding.inviteFriends.setOnClickListener(v -> {
            startActivity(new Intent(context, InviteFriendsActivity.class));
        });

        return view;
    }
}