package com.example.ordinacija.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ordinacija.GlavniActivity;
import com.example.ordinacija.R;

public class PregledPrikazFragment extends Fragment {

    public static final String IME_KEY="IME_KEY";
    public static final String DATUM_KEY = "DATUM_KEY";
    public static final String VRIJEME_KEY = "VRIJEME_KEY";
    public static final String OPIS_KEY = "OPIS_KEY";
    public static final String USLUGA_KEY = "USLUGA_KEY";
    private String imePrez;
    private String datumNovi;
    private String vrijemeNovi;
    private String opisNovi;
    private String uslugaNovi;


    public static PregledPrikazFragment newInstance(String imePrezimePregled,String datumPregled,String vrijemePregled,String opisPregled,String uslugaPregled) {
        PregledPrikazFragment fragment = new PregledPrikazFragment();
        Bundle args = new Bundle();
        args.putSerializable(IME_KEY, imePrezimePregled);
        args.putSerializable(DATUM_KEY,datumPregled);
        args.putSerializable(VRIJEME_KEY,vrijemePregled);
        args.putSerializable(OPIS_KEY,opisPregled);
        args.putSerializable(USLUGA_KEY,uslugaPregled);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            imePrez=(String) getArguments().getSerializable(IME_KEY);
            datumNovi=(String)getArguments().getSerializable(DATUM_KEY);
            vrijemeNovi=(String)getArguments().getSerializable(VRIJEME_KEY);
            opisNovi=(String)getArguments().getSerializable(OPIS_KEY);
            uslugaNovi=(String)getArguments().getSerializable(USLUGA_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_pregled_prikaz, container, false);
        ((GlavniActivity) getActivity())
                .setActionBarTitle("Prikaz pregleda");
        TextView ime=view.findViewById(R.id.ime);
        TextView datum=view.findViewById(R.id.datum);
        TextView vrijeme=view.findViewById(R.id.vrijeme);
        TextView opis=view.findViewById(R.id.opis);
        TextView usluga=view.findViewById(R.id.uslugaUnos);
        ime.setText(imePrez);
        datum.setText(datumNovi);
        vrijeme.setText(vrijemeNovi);
        opis.setText(opisNovi);
        usluga.setText(uslugaNovi);
        return  view;
    }



}
