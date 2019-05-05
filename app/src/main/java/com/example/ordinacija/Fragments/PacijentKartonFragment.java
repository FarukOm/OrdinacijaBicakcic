package com.example.ordinacija.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ordinacija.Data.PacijentiResultVM;
import com.example.ordinacija.Data.PreglediResultVM;
import com.example.ordinacija.GlavniActivity;
import com.example.ordinacija.Helper.MyApiRequest;
import com.example.ordinacija.Helper.MyFragmentsUtils;
import com.example.ordinacija.Helper.MyRunnable;
import com.example.ordinacija.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PacijentKartonFragment extends Fragment {

    public static final String PACIJENT_KEY = "PACIJENT_KEY";
    private PacijentiResultVM.Row pacijent;
    private PreglediResultVM podaci;
    private ListView lista;
    private TextView ime;

    public PacijentKartonFragment() {
        // Required empty public constructor
    }
    public static PacijentKartonFragment newInstance(PacijentiResultVM.Row pacijent) {
        PacijentKartonFragment fragment = new PacijentKartonFragment();
        Bundle args = new Bundle();
        args.putSerializable(PACIJENT_KEY,pacijent);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pacijent= (PacijentiResultVM.Row) getArguments().getSerializable(PACIJENT_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_pacijent_karton, container, false);
        ((GlavniActivity) getActivity())
                .setActionBarTitle("Karton -"+" "+pacijent.Ime+" "+pacijent.Prezime);
        ime = view.findViewById(R.id.imePrezimeUnos);
        TextView datum=view.findViewById(R.id.datumUnos);
        TextView adresa=view.findViewById(R.id.adresaUnos);
        TextView telefon=view.findViewById(R.id.telefonUnos);
        FloatingActionButton fab =view.findViewById(R.id.fab);
        lista = view.findViewById(R.id.lista);
        ime.setText(pacijent.Ime+" "+pacijent.Prezime);
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
        datum.setText(df.format(pacijent.DatumRodjenja));
        adresa.setText(pacijent.Adresa);
        telefon.setText(pacijent.Telefon);
        PopuniListuTask();
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PreglediResultVM.Row x=podaci.rows.get(position);
                String imePrezimePregled= ime.getText().toString();
                DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
                String datumPregled=df.format(x.Datum);
                String vrijemePregled=x.Vrijeme;
                String opisPregled=x.Opis;
                String uslugaPregled=x.Usluga;
                MyFragmentsUtils.OtvoriFragmentKaoReplace(getActivity(),R.id.mjestoZaFragment,PregledPrikazFragment.newInstance(imePrezimePregled,datumPregled,vrijemePregled,opisPregled,uslugaPregled));
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyFragmentsUtils.OtvoriFragmentKaoReplace(getActivity(),R.id.mjestoZaFragment,NoviPregledFragment.newInstance(pacijent.PacijentID,pacijent.Ime,pacijent.Prezime));
            }
        });
        return view;
    }

    private void PopuniListuTask() {
        String pacijentID=String.valueOf(pacijent.PacijentID);
        MyApiRequest.get(getActivity(), "api/Pregledi/getPreglediByPacijent/"+pacijentID, new MyRunnable<PreglediResultVM>() {

            @Override
            public void run(PreglediResultVM x) {
                podaci = x;
                PopuniListu();
            }
        });}

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
                PreglediResultVM.Row x=podaci.rows.get(position);
                ImeLista.setText(x.Usluga);
                DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
                AdresaLista.setText(df.format(x.Datum)+" "+x.Vrijeme);

                return convertView;
            }
        });
    }

}
