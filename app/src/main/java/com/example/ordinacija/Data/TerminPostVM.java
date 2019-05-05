package com.example.ordinacija.Data;

import java.io.Serializable;
import java.util.Date;

public class TerminPostVM implements Serializable {
    public int TerminID;
    public String Datum;
    public String Vrijeme;
    public String Napomena;
    public boolean IsOdobren;
    public boolean IsEvidentiran;
    public boolean IsDeleted;
    public String ImePrezime;
    public int PacijentID;
    public String Usluga;

    public TerminPostVM(String Datum,String Vrijeme,String Opis,int PacijentID,String  UslugaID){
        this.Datum=Datum;
        this.Vrijeme=Vrijeme;
        this.Napomena=Opis;
        this.PacijentID=PacijentID;
        this.Usluga=UslugaID;
    }
}
