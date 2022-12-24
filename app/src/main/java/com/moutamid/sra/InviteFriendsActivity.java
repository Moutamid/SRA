package com.moutamid.sra;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.moutamid.sra.databinding.ActivityInviteFriendsBinding;
import com.moutamid.sra.utils.Constants;

public class InviteFriendsActivity extends AppCompatActivity {
    ActivityInviteFriendsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInviteFriendsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.token.setText(Constants.auth().getCurrentUser().getUid());

    }
}