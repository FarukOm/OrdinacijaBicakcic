package com.example.ordinacija;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ordinacija.Data.KorisnikPostVM;
import com.example.ordinacija.Helper.MyApiRequest;
import com.example.ordinacija.Helper.MyApp;
import com.example.ordinacija.Helper.MyRunnable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.ordinacija.Helper.MyApp.getContext;

public class RegistracijaActivity extends AppCompatActivity {

    private EditText ime;
    private EditText prezime;
    private EditText adresa;
    private EditText telefon;
    private EditText email;
    private EditText korisnik;
    private EditText lozinka;
    private Button btnSnimi;
    private Button btnDatePick;
    private EditText datum;
    private Calendar myCalendar;
    private DatePickerDialog datePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registracija);
        ime = findViewById(R.id.Ime);
        prezime = findViewById(R.id.Prezime);
        telefon = findViewById(R.id.Telefon);
        email = findViewById(R.id.Email);
        korisnik = findViewById(R.id.Korisik);
        lozinka = findViewById(R.id.Lozinka);
        btnSnimi = findViewById(R.id.btnSnimi);
        btnDatePick=findViewById(R.id.btnDatePick);
        adresa=findViewById(R.id.Adresa);
        datum=findViewById(R.id.Datum);
        btnDatePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCalendar=Calendar.getInstance();
                int day=myCalendar.get(Calendar.DAY_OF_MONTH);
                int month=myCalendar.get(Calendar.MONTH);
                int year=myCalendar.get(Calendar.YEAR);

                datePickerDialog=new DatePickerDialog(RegistracijaActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        datum.setText(day+"/"+month+"/"+year);
                    }
                } ,day,month,year);
                datePickerDialog.show();
            }
        });
        btnSnimi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                do_btnSnimi();
            }
        });

    }

    private void do_btnSnimi() {
        if(Validacija())
        {
            KorisnikPostVM model = new KorisnikPostVM();
            model.Ime = ime.getText().toString();
            model.Prezime = prezime.getText().toString();
            model.Email = email.getText().toString();
            model.Telefon = telefon.getText().toString();
            model.KorisnickoIme = korisnik.getText().toString();
            model.LozinkaSalt = lozinka.getText().toString();
            model.Adresa=adresa.getText().toString();
            Date date1= null;
            try {
                date1 = new SimpleDateFormat("dd/MM/yyyy").parse(datum.getText().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            model.DatumRodjenja= date1;
            MyApiRequest.post(this, "api/Korisnici/", model, new MyRunnable<KorisnikPostVM>(){
                @Override
                public void run(KorisnikPostVM x)
                {
                    Toast.makeText(RegistracijaActivity.this, "Uspješna registracija!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }
    }
    private boolean Validacija() {

        AlertDialog adb = new AlertDialog.Builder(RegistracijaActivity.this).create();
        adb.setTitle("Greška!");
        adb.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        if(ime.getText().toString().length() < 3)
        {
            adb.setMessage("Ime mora sadržavati minimalno 3 karaktera!");
            adb.show();
            return false;
        }

        if(prezime.getText().toString().length() < 3)
        {
            adb.setMessage("Prezime mora sadržavati minimalno 3 karaktera!");
            adb.show();
            return false;
        }

        if(Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()==false)
        {
            adb.setMessage("Email nije u ispravnom formatu!");
            adb.show();
            return false;
        }

        if(telefon.getText().toString().length() < 6)
        {
            adb.setMessage("Telefonski broj mora sadržavati minimalno 6 karaktera!");
            adb.show();
            return false;
        }

        if(korisnik.getText().toString().length() < 4)
        {
            adb.setMessage("Korisničko ime mora sadržavati minimalno 4 karaktera!");
            adb.show();
            return false;
        }

        if(true)//lozinka
        {
            Pattern pattern;
            Matcher matcher;

            final String PASSWORD_PATTERN = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{4,}$";

            pattern = Pattern.compile(PASSWORD_PATTERN);
            matcher = pattern.matcher(lozinka.getText().toString());

            if(matcher.matches() == false)
            {
                adb.setMessage("Password mora sadržavati minimalno 6 karaktera, najmanje jedan broj i kombinaciju malih/velikih slova!");
                adb.show();
                return false;
            }
        }

        return true;
    }
}
