package com.example.ordinacija;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ordinacija.Data.AutentifikacijaLoginPostVM;
import com.example.ordinacija.Data.AutentifikacijaResultVM;
import com.example.ordinacija.Data.Global;
import com.example.ordinacija.Helper.MyApiRequest;
import com.example.ordinacija.Helper.MyRunnable;

public class LoginActivity extends AppCompatActivity {

    private TextView username;
    private TextView lozinka;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.Username);
        lozinka = findViewById(R.id.Lozinka);

        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                do_btnLoginClick();
            }
        });
        findViewById(R.id.btnRegistracija).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginActivity.this,RegistracijaActivity.class));
            }
        });

    }

    private void do_btnLoginClick() {
        if(Validacija()){
            String strUserName= username.getText().toString();
            String strPassword= lozinka.getText().toString();

            AutentifikacijaLoginPostVM model = new AutentifikacijaLoginPostVM(strUserName,strPassword);

            MyApiRequest.get(this, "api/Autentifikacija/LoginCheck/" + strUserName + "/" + strPassword, new MyRunnable<AutentifikacijaResultVM>() {
                @Override
                public void run(AutentifikacijaResultVM x) {
                    checkLogin(x);
                }
            });
        }
        else{
            AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
            alertDialog.setTitle("Greska");
            alertDialog.setMessage("Unesite korisnicko ime i lozinku.");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
    }
    }
        private boolean Validacija() {
            if (username.getText().toString().isEmpty())
                return false;
            if (lozinka.getText().toString().isEmpty())
                return false;
            return true;
        }
    private void checkLogin(AutentifikacijaResultVM x) {
        if ("PogresniPodaci".equals(x.Ime)) {
            View parentLayout = findViewById(android.R.id.content);
            Snackbar.make(parentLayout, "Pogrešno korisničko ime/lozinka.", Snackbar.LENGTH_LONG).show();
        } else {
            Global.prijavljeniKorisnik = x;
            startActivity(new Intent(this, GlavniActivity.class));
        }
    }
    public void onBackPressed() {

        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory(Intent.CATEGORY_HOME);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);

    }
}
