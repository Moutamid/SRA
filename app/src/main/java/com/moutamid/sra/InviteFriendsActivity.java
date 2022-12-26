package com.moutamid.sra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

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

        binding.back.setOnClickListener(v -> {
            onBackPressed();
            finish();
        });

        binding.copyBtn.setOnClickListener(v -> {
            String str = Constants.auth().getCurrentUser().getUid();
            ((ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("Copied Text", str));
            Toast.makeText(this, "Copied To Clipboard", Toast.LENGTH_SHORT).show();
        });

    }
}