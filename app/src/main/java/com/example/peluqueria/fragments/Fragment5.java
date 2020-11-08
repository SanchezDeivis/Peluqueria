package com.example.peluqueria.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.peluqueria.R;
import com.example.peluqueria.ui.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;


public class Fragment5 extends Fragment {

    private FirebaseAuth mAuth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment5, container, false);
        mAuth=FirebaseAuth.getInstance();

        mAuth.signOut();
        startActivity(new Intent(getActivity(), LoginActivity.class));
        getActivity().finish();

       /*System.exit(0);*/
        return view;
    }


}
