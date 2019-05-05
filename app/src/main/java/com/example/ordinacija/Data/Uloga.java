package com.example.ordinacija.Data;

public enum Uloga{
     Zapolenik(1), Korisnik(2);

     private final int id;
     Uloga(int id) { this.id = id; }
     public int getValue() { return id; }
}
