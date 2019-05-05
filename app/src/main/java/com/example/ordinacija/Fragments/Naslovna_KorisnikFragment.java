package com.example.ordinacija.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ordinacija.Data.AutentifikacijaResultVM;
import com.example.ordinacija.Data.Global;
import com.example.ordinacija.Data.TerminiResultVM;
import com.example.ordinacija.Data.UslugeResultVM;
import com.example.ordinacija.GlavniActivity;
import com.example.ordinacija.Helper.MyApiRequest;
import com.example.ordinacija.Helper.MyRunnable;
import com.example.ordinacija.R;

import java.text.SimpleDateFormat;


public class Naslovna_KorisnikFragment extends Fragment {
    private TerminiResultVM podaci;
    private TextView podaciView;
    private TextView imeNaslov;
    private SimpleDateFormat formatter;
    private TextView txtDanasnjiTermini;

    public static Naslovna_KorisnikFragment newInstance() {
        Naslovna_KorisnikFragment fragment = new Naslovna_KorisnikFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_naslovna__korisnik, container, false);
        ((GlavniActivity) getActivity())
                .setActionBarTitle("Ordinacija Bičakčić");
        AutentifikacijaResultVM korisnik = Global.prijavljeniKorisnik;
        imeNaslov = view.findViewById(R.id.naslovIme);
        txtDanasnjiTermini = view.findViewById(R.id.Title1);
        imeNaslov.setText(korisnik.Ime + " " + korisnik.Prezime);
        podaciView = view.findViewById(R.id.terminOpis);
        formatter = new SimpleDateFormat("yyyy/MM/dd");

        PopuniPodatke();

        return view;
    }

    private void PopuniPodatke() {

        MyApiRequest.get(getActivity(), "api/Termini/getDanasnjiTerminByKorisnik/" + Global.prijavljeniKorisnik.KorisnikID, new MyRunnable<TerminiResultVM>() {

            @Override
            public void run(TerminiResultVM x) {
                podaci = x;

                if(podaci.rows.size() != 0){

                    for (TerminiResultVM.Row xs:podaci.rows)
                    {
                        podaciView.setText(formatter.format(xs.Datum)+ " - " + xs.Vrijeme + " -> " + xs.Usluga);
                    }
                }
                else{
                    txtDanasnjiTermini.setText("Danas nemate nijedan rezervisan termin.");
                }
            }
        });
    }
}
