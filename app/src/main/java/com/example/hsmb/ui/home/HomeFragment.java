package com.example.hsmb.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.hsmb.R;
import com.example.hsmb.databinding.FragmentHomeBinding;
import com.example.hsmb.ui.gallery.GalleryFragment;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    FragmentHomeBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
/*
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        Button location = root.findViewById(R.id.location);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new GalleryFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.locatonBooth, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();            }
        });

        return root;*/
        binding=FragmentHomeBinding.inflate(getLayoutInflater());
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment LocationBooth = new GalleryFragment();
                FragmentManager manager=getActivity().getSupportFragmentManager();
                FragmentTransaction Tre=manager.beginTransaction();
                Tre.replace(R.id.nav_host_fragment, LocationBooth);
                Tre.addToBackStack(null);
                Tre.commit();

            }
        });
    }

    //------ avoid memory leaks----------
    @Override
    public void onDestroy() {
        super.onDestroy();
        binding=null;
    }
}