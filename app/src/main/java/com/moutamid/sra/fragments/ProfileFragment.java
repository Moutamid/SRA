package com.moutamid.sra.fragments;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.fxn.stash.Stash;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.moutamid.sra.AboutUsActivity;
import com.moutamid.sra.DepositActivity;
import com.moutamid.sra.InviteFriendsActivity;
import com.moutamid.sra.MyTeamActivity;
import com.moutamid.sra.R;
import com.moutamid.sra.SplashScreenActivity;
import com.moutamid.sra.WithdrawActivity;
import com.moutamid.sra.databinding.FragmentProfileBinding;
import com.moutamid.sra.models.UserModel;
import com.moutamid.sra.utils.Constants;

import java.util.Locale;

public class ProfileFragment extends Fragment {

    FragmentProfileBinding binding;
    Context context;
    TextView toolbarTittle;
    BottomNavigationView bottomNavigation;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public ProfileFragment(TextView toolbarTittle, BottomNavigationView bottomNavigation) {
        this.toolbarTittle = toolbarTittle;
        this.bottomNavigation = bottomNavigation;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        context = view.getContext();

        Constants.databaseReference().child("users").child(Constants.auth().getCurrentUser().getUid())
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                binding.username.setText(snapshot.getValue(UserModel.class).getUsername());
                                binding.id.setText(Constants.auth().getCurrentUser().getUid());
                                String u = String.format(Locale.getDefault(), "%.2f", snapshot.getValue(UserModel.class).getAssets());
                                binding.totalAssetsCount.setText("$" + u);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

        binding.id.setOnClickListener(v -> {
            String str = Constants.auth().getCurrentUser().getUid();
            ((ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("Copied Text", str));
            Toast.makeText(context, "Copied To Clipboard", Toast.LENGTH_SHORT).show();
        });

        binding.logout.setOnClickListener(v -> {
            Constants.auth().signOut();
            Stash.clear("first");
            startActivity(new Intent(context, SplashScreenActivity.class));
            getActivity().finish();
        });

        binding.deposit.setOnClickListener(v -> {
            startActivity(new Intent(context, DepositActivity.class));
        });

        binding.invite.setOnClickListener(v -> {
            startActivity(new Intent(context, InviteFriendsActivity.class));
        });

        binding.withdraw.setOnClickListener(v -> {
            Intent i = new Intent(context, WithdrawActivity.class);
            i.putExtra("amount", binding.totalAssetsCount.getText().toString());
            startActivity(i);
        });

        binding.team.setOnClickListener(v -> {
            Intent i = new Intent(context, MyTeamActivity.class);
            i.putExtra("name", "My Team");
            i.putExtra("key", Constants.auth().getCurrentUser().getUid());
            startActivity(i);
        });

        binding.history.setOnClickListener(v -> {
            toolbarTittle.setText("History");
            bottomNavigation.setSelectedItemId(R.id.nav_history);
        });

        binding.about.setOnClickListener(v -> {
            startActivity(new Intent(context, AboutUsActivity.class));
        });

        return view;
    }
}