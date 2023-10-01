package com.example.atmapimodel;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.atmapimodel.databinding.FragmentFetchUserBinding;

public class FetchUserFragment extends Fragment {

    FragmentFetchUserBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFetchUserBinding.inflate(getLayoutInflater());


        return binding.getRoot();
    }
}