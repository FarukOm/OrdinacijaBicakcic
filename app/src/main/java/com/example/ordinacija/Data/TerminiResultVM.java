package com.example.ordinacija.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class TerminiResultVM {
    public static class Row implements Serializable {
        public int TerminID;
        public Date Datum;
        public String Vrijeme;
        public String Usluga;
        public String Napomena;
        public boolean IsOdobren;
        public boolean IsEvidentiran;
        public boolean IsDeleted;
        public int PacijentID;
        public String ImePrezime;
    }
    public List<TerminiResultVM.Row> rows;
}
