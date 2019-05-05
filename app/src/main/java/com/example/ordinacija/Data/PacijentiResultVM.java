package com.example.ordinacija.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PacijentiResultVM implements Serializable {



    public static class Row implements Serializable {
        public int PacijentID;
        public String Ime;
        public String Prezime;
        public Date DatumRodjenja;
        public String Telefon;
        public String Adresa;
        public String Email;
        public  Row(){

        }

    }
    public List<Row> rows;
    public PacijentiResultVM() {
    }

}
