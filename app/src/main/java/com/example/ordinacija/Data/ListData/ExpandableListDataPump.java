package com.example.ordinacija.Data.ListData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> faq1 = new ArrayList<String>();
        faq1.add("U zavisnosti od stanja desni i zuba. Ako imate određenih problema onda treba da " +
                "idete na preglede po savetu stomatologa, ako su vam zubi zdravi onda na svakih 6-12 meseci."
        );

        List<String> faq2 = new ArrayList<String>();
        faq2.add("Četkice sa tankim sintetičkim vlaknima sa oznakom SOFT ili MEDIUM, da možete prići zubima sa svih strana."
        );

        List<String> faq3 = new ArrayList<String>();
        faq3.add("Jednako je bitno kao i pranje zuba četkicom. Većina karijesa (zubnih kvarova) se javljaju upravo između zuba," +
                " a to su teško dostupna mesta za četkicu. " +
                "Čisćenjem koncem uklanjamo zaostalu hranu, naslage i bakterije  između zuba," +
                " a dodirne površine zuba činimo manje lepljivim."
        );

        List<String> faq4 = new ArrayList<String>();
        faq4.add("Uklanjanje kamenca nije štetno po zube ni u kom smislu, a potrebno ga je ukloniti ukoliko je došlo do taloženja." +
                " Kamenac oko vrata zuba potiskuje gingivu, izaziva njeno zapaljenje i smanjuje mogućnost oralne higijene.");

        List<String> faq5 = new ArrayList<String>();
        faq5.add("Ako primijetite da vas povremeno ili kontinuirano bole zubi ili zubno meso, krvarite prilikom četkanja zubi ili vam zubno meso izgleda crveno i " +
                "upaljeno, riječ je o ranim simptomima parodontne bolesti. Uz navedene simptome, možete osjetiti i neugodan zadah ili klimavost, odnosno veću pomičnost zubi.");

        List<String> faq6 = new ArrayList<String>();
        faq6.add("Idealno bi bilo da dijete prvi put posjeti stomatologa prije nego što budu potrebne neke intervencije ili popravci, a to je negdje poslije prvog rođendana." +
                " Na taj način preduhitrit ćete i da dijete od prijatelja u vrtiću ili školi čuje negativne komentare o odlasku kod zubara i razvije nepotrebni strah.");

        expandableListDetail.put("Koliko često treba posećivati stomatologa?", faq1);
        expandableListDetail.put("Koje četkice za zube je najbolje koristiti?", faq2);
        expandableListDetail.put("Koliko je bitno koristiti konac za zube?", faq3);
        expandableListDetail.put("Da li treba uklanjati kamenac sa zuba?", faq4);
        expandableListDetail.put("Koji su to simptomi paradontitisa?", faq5);
        expandableListDetail.put("Kada dijete prvi put odvesti stomatologu?", faq6);
        return expandableListDetail;
    }
}