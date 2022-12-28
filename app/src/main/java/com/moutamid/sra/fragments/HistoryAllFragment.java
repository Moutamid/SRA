package com.moutamid.sra.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.moutamid.sra.R;
import com.moutamid.sra.adapter.TransactionsAdapter;
import com.moutamid.sra.databinding.FragmentHistoryAllBinding;
import com.moutamid.sra.models.RequestModel;
import com.moutamid.sra.utils.Constants;

import java.util.ArrayList;

public class HistoryAllFragment extends Fragment {
    FragmentHistoryAllBinding binding;
    Context context;
    ArrayList<RequestModel> list;
    TransactionsAdapter adapter;

    public HistoryAllFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHistoryAllBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        context = view.getContext();

        list = new ArrayList<>();

        binding.recycler.setLayoutManager(new LinearLayoutManager(context));
        binding.recycler.setHasFixedSize(false);

        Constants.databaseReference().child("Request").child(Constants.auth().getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            for (DataSnapshot snapshot1 : snapshot.getChildren()){
                                RequestModel model = snapshot1.getValue(RequestModel.class);
                                list.add(model);
                            }
                            adapter = new TransactionsAdapter(context, list);
                            binding.recycler.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        return view;
    }
}