package com.example.ordinacija.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.ordinacija.Data.PacijentiResultVM;
import com.example.ordinacija.GlavniActivity;
import com.example.ordinacija.Helper.MyApiRequest;
import com.example.ordinacija.Helper.MyFragmentsUtils;
import com.example.ordinacija.Helper.MyRunnable;
import com.example.ordinacija.R;

import java.util.ArrayList;

public class Pacijenti_ListaFragment extends Fragment {

    private ListView lista;
    private SearchView srch;
    private static PacijentiResultVM podaci;
    private static PacijentiResultVM novi;

    public static Pacijenti_ListaFragment newInstance() {
        Pacijenti_ListaFragment fragment = new Pacijenti_ListaFragment();
        Bundle args = new Bundle();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_pacijenti__lista, container, false);
        ((GlavniActivity) getActivity())
                .setActionBarTitle("Pacijenti");
        lista = view.findViewById(R.id.lista);
        srch = view.findViewById(R.id.srch);
        PopuniListuTask2();
       srch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                PopuniListu2(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                PopuniListu2(newText);
                return false;
            }
        });
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

            PacijentiResultVM.Row x=novi.rows.get(position);

                MyFragmentsUtils.OtvoriFragmentKaoReplace(getActivity(),R.id.mjestoZaFragment,PacijentKartonFragment.newInstance(x));
            }
        });
        return view;
    }

    public void PopuniListuTask2() {
        MyApiRequest.get(getActivity(), "api/Pacijenti/getPacijenti", new MyRunnable<PacijentiResultVM>() {

            @Override
            public void run(PacijentiResultVM x) {
                podaci = x;
                PopuniListu2("");
            }
        });}
    

    public void PopuniListu2(String s) {
        novi=getKorisniciByIme(s);
        lista.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return novi.rows.size();
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
                PacijentiResultVM.Row x= novi.rows.get(position);
                ImeLista.setText(x.Ime+" "+x.Prezime);
                AdresaLista.setText(x.Adresa);

                return convertView;
            }
        });
    }
    public PacijentiResultVM getKorisniciByIme(String query) {
        PacijentiResultVM rezultat=new PacijentiResultVM();
        rezultat.rows=new ArrayList<>();
        for (PacijentiResultVM.Row x:podaci.rows)
        {
            if((x.Ime+" "+x.Prezime).toLowerCase().startsWith(query) || (x.Ime+" "+x.Prezime).startsWith(query) || (x.Prezime+" "+x.Ime).toLowerCase().startsWith(query) || (x.Prezime+" "+x.Ime).startsWith(query))
                rezultat.rows.add(x);
        }

        return rezultat;
    }

}
