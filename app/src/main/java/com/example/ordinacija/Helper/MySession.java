package com.example.ordinacija.Helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.ordinacija.Data.AutentifikacijaResultVM;

public class MySession {
    private static final String PREFS_NAME = "SP";
    private static String nekikey="Key_korisnik";

    public static AutentifikacijaResultVM getKorisnik()
    {
        SharedPreferences sharedPreferences = MyApp.getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String strJson = sharedPreferences.getString(nekikey, "");
        if (strJson.length() == 0)
            return null;

        AutentifikacijaResultVM x = MyGson.build().fromJson(strJson, AutentifikacijaResultVM.class);
        return x;
    }

    public static void setKorisnik(AutentifikacijaResultVM x)
    {
        String strJson = x!=null? MyGson.build().toJson(x):"";

        SharedPreferences sharedPreferences = MyApp.getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(nekikey, strJson);
        editor.apply();
    }
}
