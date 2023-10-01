package com.example.atmapimodel;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.atmapimodel.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {

    FragmentDashboardBinding binding;
    SharedPreferences sharedPreferences;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(getLayoutInflater());

        sharedPreferences = getActivity().getSharedPreferences("UserDetails", Context.MODE_PRIVATE);

        binding.userName.setText(sharedPreferences.getString("user_first_name", null));
        binding.userEmail.setText(sharedPreferences.getString("user_email", null));
        binding.userAmount.setText(sharedPreferences.getString("user_amount", null));

        binding.deleteUser.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.deleteUserFragment);
        });
        binding.depositAmount.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.depositMoneyFragment);
        });
        binding.withdrawMoney.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.withdrawMoneyFragment);
        });

        return binding.getRoot();
    }
}