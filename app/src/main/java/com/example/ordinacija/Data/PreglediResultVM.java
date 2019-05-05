package com.example.ordinacija.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PreglediResultVM implements Serializable{
    public static class Row implements Serializable {
        public int PregledID;
        public Date Datum;
        public String Vrijeme;
        public String Opis;
        public int ZaposlenikID;
        public int PacijentID;
        public String Usluga;
        public Row(){

        }
    }
    public List<Row> rows;

}
