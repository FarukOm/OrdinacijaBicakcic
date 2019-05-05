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

import com.example.ordinacija.Data.AutentifikacijaResultVM;
import com.example.ordinacija.Data.Global;
import com.example.ordinacija.Data.PacijentiResultVM;
import com.example.ordinacija.Data.TerminiResultVM;
import com.example.ordinacija.GlavniActivity;
import com.example.ordinacija.Helper.MyApiRequest;
import com.example.ordinacija.Helper.MyRunnable;
import com.example.ordinacija.R;

public class NaslovnaFragment extends Fragment {


    private ListView lista;
    private TerminiResultVM podaci;
    public static NaslovnaFragment newInstance() {
        NaslovnaFragment fragment = new NaslovnaFragment();
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
        View view= inflater.inflate(R.layout.fragment_naslovna, container, false);
        ((GlavniActivity) getActivity())
                .setActionBarTitle("Ordinacija Bičakčić");
        AutentifikacijaResultVM korisnik= Global.prijavljeniKorisnik;
        TextView imeNaslov=view.findViewById(R.id.naslovIme);
        imeNaslov.setText(korisnik.Ime+" "+korisnik.Prezime);
        lista = view.findViewById(R.id.listaTermina);
        PopuniListuTask();
        return view;
    }

    private void PopuniListuTask() {
        MyApiRequest.get(getActivity(), "api/Termini/getDanasnjeTermine", new MyRunnable<TerminiResultVM>() {

            @Override
            public void run(TerminiResultVM x) {
                podaci = x;
                PopuniListu();
            }
        });
    }

    private void PopuniListu() {
        lista.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return podaci.rows.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LayoutInflater inflater=(LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView=inflater.inflate(R.layout.lista_template,parent,false);
                TextView ImeLista=convertView.findViewById(R.id.txtFirstLine);
                TextView AdresaLista=convertView.findViewById(R.id.txtSecondLine);
                TerminiResultVM.Row d=podaci.rows.get(position);
                ImeLista.setText(d.ImePrezime);
                AdresaLista.setText(d.Vrijeme);

                return convertView;
            }
        });
    }

}
