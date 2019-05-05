package com.example.ordinacija.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ordinacija.Data.Global;
import com.example.ordinacija.Data.PacijentiResultVM;
import com.example.ordinacija.Data.PregledPostVM;
import com.example.ordinacija.Data.TerminiResultVM;
import com.example.ordinacija.Data.UslugeResultVM;
import com.example.ordinacija.GlavniActivity;
import com.example.ordinacija.Helper.MyApiRequest;
import com.example.ordinacija.Helper.MyFragmentsUtils;
import com.example.ordinacija.Helper.MyRunnable;
import com.example.ordinacija.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NoviPregledFragment extends Fragment {

    public static final String PACIJENT_KEY = "PACIJENT_KEY";
    public static final String IME_KEY = "IME_KEY";
    public static final String PREZIME_KEY = "PREZIME_KEY";
    private int pacijentID;
    private String ime;
    private String prezime;
    private UslugeResultVM usluge=new UslugeResultVM();
    private Spinner spinner;
    private TextView datum;
    private TextView vrijeme;
    private TextView opis;
    private DateFormat df;
    private PacijentiResultVM pacijenti;

    public static NoviPregledFragment newInstance(int pacijentID, String ime,String prezime) {
        NoviPregledFragment fragment = new NoviPregledFragment();
        Bundle args = new Bundle();
        args.putSerializable(PACIJENT_KEY, pacijentID);
        args.putSerializable(IME_KEY, ime);
        args.putSerializable(PREZIME_KEY, prezime);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pacijentID=(int)getArguments().getSerializable(PACIJENT_KEY);
            ime=(String)getArguments().getSerializable(IME_KEY);
            prezime=(String)getArguments().getSerializable(PREZIME_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_novi_pregled, container, false);
        ((GlavniActivity) getActivity())
                .setActionBarTitle("Novi pregled");
        TextView imePrezime=view.findViewById(R.id.imePrezimePacijent);
        datum = view.findViewById(R.id.datum);
        vrijeme = view.findViewById(R.id.vrijeme);
        opis = view.findViewById(R.id.opis);
        spinner = view.findViewById(R.id.usluga);
        Button btnSnimi=view.findViewById(R.id.btnSnimi2);

        Date datumSad=new Date();
        df = DateFormat.getDateInstance(DateFormat.SHORT);
        DateFormat df2 = DateFormat.getTimeInstance(DateFormat.SHORT);
        String datumTrenutno= df.format(datumSad);
        String vrijemeTrenutno=df2.format(datumSad);

        imePrezime.setText(ime+" "+prezime);
        datum.setText(datumTrenutno);
        vrijeme.setText(vrijemeTrenutno);
        DobaviPodatkeUsluga();
        btnSnimi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                do_btnSnimi();
            }
        });
        return view;
    }

    private void do_btnSnimi() {
        int korisnikID= Global.prijavljeniKorisnik.KorisnikID;
        int uslugaID=usluge.rows.get(spinner.getSelectedItemPosition()).UslugaID;
        PregledPostVM pregled=new PregledPostVM(datum.getText().toString(),vrijeme.getText().toString(),opis.getText().toString(),korisnikID,pacijentID,uslugaID);

         pregled.UslugaID=uslugaID;
        MyApiRequest.post(getActivity(), "api/Pregledi/postPregled", pregled, new MyRunnable<PregledPostVM>() {
            public void run(PregledPostVM x) {
                Toast.makeText(getActivity(), "Pregled uspješno spašen.", Toast.LENGTH_SHORT).show();
            }
        });
        startActivity(new Intent(getActivity(), GlavniActivity.class));

        //MyFragmentsUtils.OtvoriFragmentKaoReplace(getActivity(),R.id.mjestoZaFragment,Pacijenti_ListaFragment.newInstance());
    }

    public void DobaviPodatkeUsluga() {
        usluge.rows=new ArrayList<>();
        MyApiRequest.get(getActivity(), "api/Usluge/getUsluge", new MyRunnable<UslugeResultVM>() {
            @Override
            public void run(UslugeResultVM x) {
                usluge.rows = x.rows;
                PopuniSpinner();
            }
        }
        );
    }

    public void PopuniSpinner() {

        List<String> uslugeLista=new ArrayList<>();
        for (UslugeResultVM.Row x:usluge.rows)
        {
            uslugeLista.add(x.Naziv);
        }
        ArrayAdapter<String> dataAdapter=new ArrayAdapter<>(getActivity(),R.layout.support_simple_spinner_dropdown_item,uslugeLista);
        dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

    }


}