package com.example.ordinacija.Data;

import java.util.Date;

public class PregledPostVM {
    public String Datum;
    public String Vrijeme;
    public String Opis;
    public int ZaposlenikID;
    public int PacijentID;
    public int UslugaID;

    public PregledPostVM(String Datum,String Vrijeme,String Opis,int ZaposlenikID,int PacijentID,int UslugaID){
    this.Datum=Datum;
    this.Vrijeme=Vrijeme;
    this.Opis=Opis;
    this.ZaposlenikID=ZaposlenikID;
    this.PacijentID=PacijentID;
    this.UslugaID=UslugaID;
    }
}
