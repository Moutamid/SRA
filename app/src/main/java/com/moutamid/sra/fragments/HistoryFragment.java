package com.moutamid.sra.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.moutamid.sra.R;
import com.moutamid.sra.databinding.FragmentHistoryBinding;
import com.moutamid.sra.models.UserModel;
import com.moutamid.sra.utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HistoryFragment extends Fragment {

    FragmentHistoryBinding binding;
    Context context;
    ProgressDialog progressDialog;
    ViewPagerAdapter viewPagerAdapter;

    public HistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHistoryBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        context = view.getContext();

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);

        progressDialog.show();

        new Handler().postDelayed(() -> {progressDialog.dismiss();}, 1000);

        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        binding.viewPager.setAdapter(viewPagerAdapter);

        binding.tablayout.setupWithViewPager(binding.viewPager);

        Constants.databaseReference().child("users").child(Constants.auth().getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        UserModel model = snapshot.getValue(UserModel.class);
                        String s = String.format(Locale.getDefault(), "%.2f", model.getAssets());
                        binding.totalAssetsCount.setText("$" + s);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        return view;
    }


    public class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            if (position == 0)
                fragment = new HistoryAllFragment();
            else if (position == 1)
                fragment = new HistoryPendingFragment();
            if (position == 2)
                fragment = new HistoryCompleteFragment();
            else if (position == 3)
                fragment = new HistoryCanceledFragment();

            return fragment;
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String title = null;
            if (position == 0)
                title = "All";
            else if (position == 1)
                title = "Pending";
            if (position == 2)
                title = "Complete";
            else if (position == 3)
                title = "Canceled";
            return title;
        }
    }

}