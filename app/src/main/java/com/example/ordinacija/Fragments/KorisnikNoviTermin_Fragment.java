package com.example.ordinacija.Fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.ordinacija.Data.Global;
import com.example.ordinacija.Data.PregledPostVM;
import com.example.ordinacija.Data.TerminPostVM;
import com.example.ordinacija.Data.UslugeResultVM;
import com.example.ordinacija.GlavniActivity;
import com.example.ordinacija.Helper.MyApiRequest;
import com.example.ordinacija.Helper.MyFragmentsUtils;
import com.example.ordinacija.Helper.MyRunnable;
import com.example.ordinacija.R;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class KorisnikNoviTermin_Fragment extends Fragment {


    private EditText edittext;
    private Calendar myCalendar;
    private EditText edit_time;
    private EditText napomena;
    private UslugeResultVM usluge=new UslugeResultVM();
    private SearchableSpinner spinner1;
    private int uslugaID;

    public KorisnikNoviTermin_Fragment() {
        // Required empty public constructor
    }


    public static KorisnikNoviTermin_Fragment newInstance() {
        KorisnikNoviTermin_Fragment fragment = new KorisnikNoviTermin_Fragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edittext.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_korisnik_novi_termin_, container, false);

        myCalendar = Calendar.getInstance();

        edittext = (EditText) view.findViewById(R.id.txtDatum);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        edittext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        edit_time = (EditText) view.findViewById(R.id.txtVrijeme);

        edit_time.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        edit_time.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });
        napomena = view.findViewById(R.id.napomena);


        DobaviPodatkeUsluga();

        spinner1 = (SearchableSpinner) view.findViewById(R.id.spinner_search);

        spinner1.setTitle("Odaberite uslugu");

        Button btnRezervisi = view.findViewById(R.id.btnRezervisiTermin);
        
        
        btnRezervisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                do_btnRezervisiTermin();
                
            }
        });
        return view;
    }

    private void do_btnRezervisiTermin() {

        int korisnikID= Global.prijavljeniKorisnik.KorisnikID;
        int uslugaID=usluge.rows.get(spinner1.getSelectedItemPosition()).UslugaID;
        TerminPostVM pregled=new TerminPostVM(edittext.getText().toString(),edit_time.getText().toString(),napomena.getText().toString(),korisnikID,String.valueOf(uslugaID));

        MyApiRequest.post(getActivity(), "api/Termini/postTermin", pregled, new MyRunnable<PregledPostVM>() {
            public void run(PregledPostVM x) {
                Toast.makeText(getActivity(), "Termin uspješno rezervisan.", Toast.LENGTH_SHORT).show();
            }
        });
        startActivity(new Intent(getActivity(), GlavniActivity.class));

      //  MyFragmentsUtils.OtvoriFragmentKaoReplace(getActivity(),R.id.mjestoZaFragment,KorisnikTermini_Fragment.newInstance());

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
        spinner1.setAdapter(dataAdapter);

    }

}
