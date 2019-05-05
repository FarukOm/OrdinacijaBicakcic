package com.example.ordinacija.Data;

import java.io.Serializable;
import java.util.List;

public class UslugeResultVM implements Serializable {
    public static class Row implements Serializable {
        public int UslugaID;
        public String Naziv;
        public String Opis;
    }
    public List<UslugeResultVM.Row> rows;
}
