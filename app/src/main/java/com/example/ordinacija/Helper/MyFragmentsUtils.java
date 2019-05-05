package com.example.ordinacija.Helper;

import android.app.Activity;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class MyFragmentsUtils {
    public static void OtvoriFragmentKaoReplace(Activity activity, int id, Fragment fragment){
        FragmentManager fragmentManager=((FragmentActivity)activity).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(id,fragment);
        fragmentTransaction.commit();
    }
    public static void OtvoriFragmentKaoDialog(Activity activity, DialogFragment fragment){
        final FragmentManager fragmentManager=((FragmentActivity)activity).getSupportFragmentManager();
        fragment.show(fragmentManager,"tag");
    }
}
