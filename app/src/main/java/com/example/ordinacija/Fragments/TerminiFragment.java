package com.example.ordinacija.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ordinacija.Data.PacijentiResultVM;
import com.example.ordinacija.Data.PregledPostVM;
import com.example.ordinacija.Data.TerminPostVM;
import com.example.ordinacija.Data.TerminiResultVM;
import com.example.ordinacija.GlavniActivity;
import com.example.ordinacija.Helper.MyApiRequest;
import com.example.ordinacija.Helper.MyFragmentsUtils;
import com.example.ordinacija.Helper.MyRunnable;
import com.example.ordinacija.R;

import java.text.DateFormat;


public class TerminiFragment extends Fragment {

    private TerminiResultVM novi;
    private TerminiResultVM stari;
    private ListView listaNovi;
    private ListView listaStari;

    public static TerminiFragment newInstance() {
        TerminiFragment fragment = new TerminiFragment();
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

        View view=inflater.inflate(R.layout.fragment_termini, container, false);
        ((GlavniActivity) getActivity())
                .setActionBarTitle("Termini");
        listaNovi = view.findViewById(R.id.listaNovih);
        listaStari = view.findViewById(R.id.listaEvidentiranih);
        DobaviPodatkeTask();
        listaNovi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder dialog=new AlertDialog.Builder(getActivity());
                dialog.setMessage("Evidentiranje termina");
                dialog.setPositiveButton("Odobri", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MyApiRequest.put(getActivity(), "api/Termini/putOdobreno",novi.rows.get(position), new MyRunnable<TerminPostVM>() {
                            public void run(TerminPostVM x) {
                            }
                        });
                        Toast.makeText(getActivity(), "Termin odobren!", Toast.LENGTH_SHORT).show();
                        MyFragmentsUtils.OtvoriFragmentKaoReplace(getActivity(),R.id.mjestoZaFragment,TerminiFragment.newInstance());
                    }
                });
                dialog.setNegativeButton("Odbij", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MyApiRequest.put(getActivity(), "api/Termini/putOdbijeno",novi.rows.get(position), new MyRunnable<TerminPostVM>() {
                            public void run(TerminPostVM x) {
                            }
                        });
                        Toast.makeText(getActivity(), "Termin odbijen!", Toast.LENGTH_SHORT).show();
                        MyFragmentsUtils.OtvoriFragmentKaoReplace(getActivity(),R.id.mjestoZaFragment,TerminiFragment.newInstance());
                    }
                });
                dialog.show();
            }
        });
        listaStari.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                if(stari.rows.get(position).IsOdobren) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                    dialog.setMessage("Da li želite otkazati odabrani termin?");
                    dialog.setPositiveButton("Da", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MyApiRequest.put(getActivity(), "api/Termini/putOtkazano", stari.rows.get(position), new MyRunnable<TerminPostVM>() {
                                public void run(TerminPostVM x) {
                                }
                            });
                            Toast.makeText(getActivity(), "Termin otkazan!", Toast.LENGTH_SHORT).show();
                            MyFragmentsUtils.OtvoriFragmentKaoReplace(getActivity(), R.id.mjestoZaFragment, TerminiFragment.newInstance());
                        }
                    });
                    dialog.setNegativeButton("Ne", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MyFragmentsUtils.OtvoriFragmentKaoReplace(getActivity(), R.id.mjestoZaFragment, TerminiFragment.newInstance());
                        }
                    });
                    dialog.show();
                }
                }

        });
        return view;
    }

    private void DobaviPodatkeTask() {
        MyApiRequest.get(getActivity(), "api/Termini/getNoviTermini", new MyRunnable<TerminiResultVM>() {
            public void run(TerminiResultVM x) {
                novi=x;
                PopuniListuNovih();
            }
        });
        MyApiRequest.get(getActivity(), "api/Termini/getEvidentiraniTermini", new MyRunnable<TerminiResultVM>() {
            public void run(TerminiResultVM y) {
                stari=y;
                PopuniListuEvidentiranih();
            }
        });
    }

    private void PopuniListuEvidentiranih() {
        listaStari.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return stari.rows.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @SuppressLint("ViewHolder")
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LayoutInflater inflater=(LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView=inflater.inflate(R.layout.lista_template2,parent,false);
                TextView ImeLista=convertView.findViewById(R.id.txtFirstLine);
                TextView PrezimeLista=convertView.findViewById(R.id.txtSecondLine);
                TextView UslugaLista=convertView.findViewById(R.id.txtThirdLine);

                TerminiResultVM.Row x= stari.rows.get(position);
                ImeLista.setText(x.ImePrezime);
                DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
                String datumTrenutno= df.format(x.Datum);
                PrezimeLista.setText(datumTrenutno+" "+x.Vrijeme);
                if(x.IsDeleted){
                    UslugaLista.setText(x.Usluga+" - Otkazano");
                }
                if(x.IsOdobren){
                    UslugaLista.setText(x.Usluga+" - Odobreno");
                }
                if(!x.IsOdobren && !x.IsDeleted){
                    UslugaLista.setText(x.Usluga+" - Odbijeno");
                }

                return convertView;
            }
        });
    }

    private void PopuniListuNovih() {
        listaNovi.setAdapter(new BaseAdapter() {
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

            @SuppressLint("ViewHolder")
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LayoutInflater inflater=(LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView=inflater.inflate(R.layout.lista_template2,parent,false);
                TextView ImeLista=convertView.findViewById(R.id.txtFirstLine);
                TextView PrezimeLista=convertView.findViewById(R.id.txtSecondLine);
                TextView UslugaLista=convertView.findViewById(R.id.txtThirdLine);

                TerminiResultVM.Row x= novi.rows.get(position);
                ImeLista.setText(x.ImePrezime);
                DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
                String datumTrenutno= df.format(x.Datum);
                PrezimeLista.setText(datumTrenutno+" "+x.Vrijeme);
                UslugaLista.setText(x.Usluga);
                return convertView;
            }
        });
    }

}
