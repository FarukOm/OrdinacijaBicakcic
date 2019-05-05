package com.example.ordinacija.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ordinacija.GlavniActivity;
import com.example.ordinacija.R;


public class ContactUsFragment extends Fragment {

    public static ContactUsFragment newInstance() {
        ContactUsFragment fragment = new ContactUsFragment();
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_contact_us, container, false);
        ((GlavniActivity) getActivity())
                .setActionBarTitle("Kontakt");
        return view;
    }
}
