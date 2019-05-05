package com.example.ordinacija.Fragments;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ordinacija.Data.Global;
import com.example.ordinacija.Data.PacijentiResultVM;
import com.example.ordinacija.Data.TerminPostVM;
import com.example.ordinacija.Data.TerminiResultVM;
import com.example.ordinacija.GlavniActivity;
import com.example.ordinacija.Helper.MyApiRequest;
import com.example.ordinacija.Helper.MyFragmentsUtils;
import com.example.ordinacija.Helper.MyRunnable;
import com.example.ordinacija.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class KorisnikTermini_Fragment extends Fragment {

    private ListView lista;
    private static TerminiResultVM termini;
    private FloatingActionButton noviTermin;

    public static KorisnikTermini_Fragment newInstance() {
        KorisnikTermini_Fragment fragment = new KorisnikTermini_Fragment();
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
        View view = inflater.inflate(R.layout.fragment_korisnik_termini_, container, false);
        ((GlavniActivity) getActivity())
                .setActionBarTitle("Termini");

        lista = view.findViewById(R.id.lista);

        noviTermin = view.findViewById(R.id.fab);

        noviTermin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyFragmentsUtils.OtvoriFragmentKaoReplace(getActivity(), R.id.mjestoZaFragment, KorisnikNoviTermin_Fragment.newInstance());

            }
        });
        PopuniListu();

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                Date date = Calendar.getInstance().getTime();


                if(termini.rows.get(position).Datum.after(date)){

                    AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                    dialog.setMessage("Da li želite otkazati odabrani termin?");
                    dialog.setPositiveButton("Da", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MyApiRequest.put(getActivity(), "api/Termini/putOtkazanoByTerminId" + termini.rows.get(position).TerminID, termini.rows.get(position), new MyRunnable<TerminPostVM>() {
                                public void run(TerminPostVM x) {
                                }
                            });
                            Toast.makeText(getActivity(), "Termin otkazan!", Toast.LENGTH_SHORT).show();
                            MyFragmentsUtils.OtvoriFragmentKaoReplace(getActivity(), R.id.mjestoZaFragment, KorisnikTermini_Fragment.newInstance());
                        }
                    });
                    dialog.setNegativeButton("Ne", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MyFragmentsUtils.OtvoriFragmentKaoReplace(getActivity(), R.id.mjestoZaFragment, KorisnikTermini_Fragment.newInstance());
                        }
                    });
                    dialog.show();


                }

                else {

                    Toast.makeText(getActivity(), "Nije moguce otkazati termin!", Toast.LENGTH_SHORT).show();

                }
                return false;

            }
        });



        return view;
    }

    private void PopuniListu() {
        MyApiRequest.get(getActivity(), "api/Termini/GetTerminByPacijent/" + Global.prijavljeniKorisnik.KorisnikID, new MyRunnable<TerminiResultVM>() {

            @Override
            public void run(TerminiResultVM x) {
                termini = x;
                PopuniListu2();
            }
        });


    }

    private void PopuniListu2() {

        lista.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return termini.rows.size();
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


                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.lista_template3, parent, false);

                TextView Usluga = convertView.findViewById(R.id.txtFirstLine);
                TextView Datum = convertView.findViewById(R.id.txtSecondLine);
                TextView Vrijeme = convertView.findViewById(R.id.txtThirdLine);

                TerminiResultVM.Row x = termini.rows.get(position);

                Usluga.setText(x.Usluga);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
                Datum.setText(formatter.format(x.Datum));
                Vrijeme.setText(x.Vrijeme);

                return convertView;
            }
        });
    }

}
