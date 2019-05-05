package com.example.ordinacija;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.ordinacija.Data.Global;
import com.example.ordinacija.Data.Uloga;
import com.example.ordinacija.Fragments.ContactUsFragment;
import com.example.ordinacija.Fragments.FaqFragment;
import com.example.ordinacija.Fragments.KorisnikTermini_Fragment;
import com.example.ordinacija.Fragments.NaslovnaFragment;
import com.example.ordinacija.Fragments.Naslovna_KorisnikFragment;
import com.example.ordinacija.Fragments.Pacijenti_ListaFragment;
import com.example.ordinacija.Fragments.TerminiFragment;
import com.example.ordinacija.Helper.MyApiRequest;
import com.example.ordinacija.Helper.MyFragmentsUtils;

public class GlavniActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glavni);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        filterNavItems(navigationView.getMenu());
        navigationView.setNavigationItemSelectedListener(this);

        if(Global.prijavljeniKorisnik.Uloga== Uloga.Zapolenik.getValue()) {
            MyFragmentsUtils.OtvoriFragmentKaoReplace(this,R.id.mjestoZaFragment,NaslovnaFragment.newInstance());

        }
        else{
            MyFragmentsUtils.OtvoriFragmentKaoReplace(this,R.id.mjestoZaFragment,Naslovna_KorisnikFragment.newInstance());

        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.glavni, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_naslovna) {
            MyFragmentsUtils.OtvoriFragmentKaoReplace(this,R.id.mjestoZaFragment, NaslovnaFragment.newInstance());
            // Handle the camera action
        }
        else if (id == R.id.nav_naslovna2) {
            MyFragmentsUtils.OtvoriFragmentKaoReplace(this,R.id.mjestoZaFragment, Naslovna_KorisnikFragment.newInstance());

        }

        else if (id == R.id.nav_KorisnikTermini) {
            MyFragmentsUtils.OtvoriFragmentKaoReplace(this,R.id.mjestoZaFragment, KorisnikTermini_Fragment.newInstance());

        }
        else if (id == R.id.nav_Kontakt) {
            MyFragmentsUtils.OtvoriFragmentKaoReplace(this,R.id.mjestoZaFragment, ContactUsFragment.newInstance());

        }
        else if (id == R.id.nav_faq) {
            MyFragmentsUtils.OtvoriFragmentKaoReplace(this,R.id.mjestoZaFragment, FaqFragment.newInstance());

        }
        else if (id == R.id.nav_pacijenti) {
            MyFragmentsUtils.OtvoriFragmentKaoReplace(this,R.id.mjestoZaFragment, Pacijenti_ListaFragment.newInstance());

        }
        else if (id == R.id.nav_termini) {
            MyFragmentsUtils.OtvoriFragmentKaoReplace(this,R.id.mjestoZaFragment, TerminiFragment.newInstance());

        } else if (id == R.id.nav_logout) {
            MyApiRequest.delete(this,"api/Autentifikacija/Logout",null);
            startActivity(new Intent(this, LoginActivity.class));

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void filterNavItems(Menu menu){

        if(Global.prijavljeniKorisnik.Uloga== Uloga.Zapolenik.getValue()){
            menu.findItem(R.id.nav_faq).setVisible(false);
            menu.findItem(R.id.nav_Kontakt).setVisible(false);
            menu.findItem(R.id.nav_KorisnikTermini).setVisible(false);
            menu.findItem(R.id.nav_pacijenti).setVisible(true);
            menu.findItem(R.id.nav_termini).setVisible(true);
            menu.findItem(R.id.nav_logout).setVisible(true);
            menu.findItem(R.id.nav_naslovna).setVisible(true);
            menu.findItem(R.id.nav_naslovna2).setVisible(false);

        }
        else if(Global.prijavljeniKorisnik.Uloga== Uloga.Korisnik.getValue()){
            menu.findItem(R.id.nav_pacijenti).setVisible(false);
            menu.findItem(R.id.nav_termini).setVisible(false);
            menu.findItem(R.id.nav_faq).setVisible(true);
            menu.findItem(R.id.nav_KorisnikTermini).setVisible(true);
            menu.findItem(R.id.nav_logout).setVisible(true);
            menu.findItem(R.id.nav_naslovna).setVisible(false);
            menu.findItem(R.id.nav_naslovna2).setVisible(true);

        }

    }
}
