package com.example.ordinacija.Data;

import java.io.Serializable;

public class AutentifikacijaResultVM implements Serializable
{
    public int KorisnikID;
    public String Ime ;
    public String Prezime ;
    public String KorisickoIme ;
    public String LozinkaSalt ;
    public String Telefon ;
    public String Email ;
    public String Token;
    public int Uloga;
}

