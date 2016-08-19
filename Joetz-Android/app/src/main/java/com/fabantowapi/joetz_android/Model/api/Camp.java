package com.fabantowapi.joetz_android.model.api;

import android.content.ContentValues;
import android.database.Cursor;

import com.fabantowapi.joetz_android.database.CampTable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pieter on 18-8-2016.
 */
public class Camp implements Serializable {
    private String id;
    private String naam;
    private String omschrijving;
    private String startDatum;
    private String eindDatum;
    private int aantalDagen;
    private int aantalNachten;
    private String vervoer;
    private int prijs;
    private int maxLeeftijd;
    private int minLeeftijd;
    private int maxDeelnemers;
    private String contact;
    private String sfeerfoto;
    private String[] fotos;
    private List<User> medewerkers;
    private Adres adres;

    public Camp(String id, String naam, String omschrijving, String startDatum, String eindDatum, int aantalDagen, int aantalNachten, String vervoer, int prijs, int maxLeeftijd, int minLeeftijd, int maxDeelnemers, String contact, String sfeerfoto, String[] fotos, List<User> medewerkers, Adres adres) {
        this.id = id;
        this.naam = naam;
        this.omschrijving = omschrijving;
        this.startDatum = startDatum;
        this.eindDatum = eindDatum;
        this.aantalDagen = aantalDagen;
        this.aantalNachten = aantalNachten;
        this.vervoer = vervoer;
        this.prijs = prijs;
        this.maxLeeftijd = maxLeeftijd;
        this.minLeeftijd = minLeeftijd;
        this.maxDeelnemers = maxDeelnemers;
        this.contact = contact;
        this.sfeerfoto = sfeerfoto;
        this.fotos = fotos;

        if(medewerkers == null){
            this.medewerkers = new ArrayList<>();
        }
        else{
            this.medewerkers = medewerkers;
        }

        this.adres = adres;
    }

    public String getId() {
        return id;
    }
    public String getNaam() { return naam; }
    public String getOmschrijving() { return omschrijving; }
    public String getStartDatum() { return startDatum; }
    public String getEindDatum() { return eindDatum; }
    public int getAantalDagen() { return aantalDagen; }
    public int getAantalNachten() { return aantalNachten; }
    public String getVervoer() { return vervoer; }
    public int getPrijs() { return prijs; }
    public int getMaxLeeftijd() { return maxLeeftijd; }
    public int getMinLeeftijd() { return minLeeftijd; }
    public int getMaxDeelnemers() { return maxDeelnemers; }
    public String getContact() { return contact; }
    public String getSfeerfoto() { return sfeerfoto; }
    public String[] getFotos() { return fotos; }
    public List<User> getMedewerkers() { return medewerkers; }
    public Adres getAdres() { return adres; }

    public void addMedewerker(User medewerker){
        this.medewerkers.add(medewerker);
    }

    public static List<Camp> constructListFromCursor(Cursor cursor){
        List<Camp> camps = new ArrayList<>();

        if(cursor != null && cursor.moveToFirst()){
            do{
                camps.add(Camp.constructFromCursor(cursor));
            }
            while(cursor.moveToNext());
        }

        return camps;
    }

    public ContentValues getContentValues(){
        ContentValues cv = new ContentValues();

        cv.put(CampTable.COLUMN_ID, this.id);
        cv.put(CampTable.COLUMN_NAAM, this.naam);
        cv.put(CampTable.COLUMN_OMSCHIRIJVING, this.omschrijving);
        cv.put(CampTable.COLUMN_STARTDATUM, this.startDatum);
        cv.put(CampTable.COLUMN_EINDDATUM, this.eindDatum);
        cv.put(CampTable.COLUMN_AANTALNACHTEN, this.aantalNachten);
        cv.put(CampTable.COLUMN_AANTALDAGEN, this.aantalDagen);
        cv.put(CampTable.COLUMN_VERVOER, this.vervoer);
        cv.put(CampTable.COLUMN_PRIJS, this.prijs);
        cv.put(CampTable.COLUMN_MAXLEEFTIJD, this.maxLeeftijd);
        cv.put(CampTable.COLUMN_MINLEEFTIJD, this.minLeeftijd);
        cv.put(CampTable.COLUMN_MAXDEELNEMERS, this.maxDeelnemers);
        cv.put(CampTable.COLUMN_CONTACT, this.contact);
        cv.put(CampTable.COLUMN_SFEERFOTO, this.sfeerfoto);
        cv.put(CampTable.COLUMN_NAAMGEBOUW, this.adres.getNaamgebouw());
        cv.put(CampTable.COLUMN_STRAAT, this.adres.getStraat());
        cv.put(CampTable.COLUMN_HUISNUMMER, this.adres.getHuisnummer());
        cv.put(CampTable.COLUMN_BUS, this.adres.getBus());
        cv.put(CampTable.COLUMN_GEMEENTE, this.adres.getGemeente());
        cv.put(CampTable.COLUMN_POSTCODE, this.adres.getPostcode());

        return cv;
    }

    public static Camp constructFromCursor(Cursor cursor){
        int idIndex = cursor.getColumnIndex(CampTable.COLUMN_ID_FULL);
        int naamIndex = cursor.getColumnIndex(CampTable.COLUMN_NAAM_FULL);
        int omschrijvingIndex = cursor.getColumnIndex(CampTable.COLUMN_OMSCHIRIJVING_FULL);
        int startDatumIndex = cursor.getColumnIndex(CampTable.COLUMN_STARTDATUM_FULL);
        int eindDatumIndex = cursor.getColumnIndex(CampTable.COLUMN_EINDDATUM_FULL);
        int aantalDagenIndex = cursor.getColumnIndex(CampTable.COLUMN_AANTALDAGEN_FULL);
        int aantalNachtenIndex = cursor.getColumnIndex(CampTable.COLUMN_AANTALNACHTEN_FULL);
        int vervoerIndex = cursor.getColumnIndex(CampTable.COLUMN_VERVOER_FULL);
        int prijsIndex = cursor.getColumnIndex(CampTable.COLUMN_PRIJS_FULL);
        int maxLeeftijdIndex = cursor.getColumnIndex(CampTable.COLUMN_MAXLEEFTIJD_FULL);
        int minLeeftijdIndex = cursor.getColumnIndex(CampTable.COLUMN_MINLEEFTIJD_FULL);
        int maxDeelnemersIndex = cursor.getColumnIndex(CampTable.COLUMN_MAXDEELNEMERS_FULL);
        int contactIndex = cursor.getColumnIndex(CampTable.COLUMN_CONTACT_FULL);
        int sfeerfotoIndex = cursor.getColumnIndex(CampTable.COLUMN_SFEERFOTO_FULL);
        int naamgebouwIndex = cursor.getColumnIndex(CampTable.COLUMN_NAAMGEBOUW_FULL);
        int straatIndex = cursor.getColumnIndex(CampTable.COLUMN_STRAAT_FULL);
        int huisnummerIndex = cursor.getColumnIndex(CampTable.COLUMN_HUISNUMMER_FULL);
        int busIndex = cursor.getColumnIndex(CampTable.COLUMN_BUS_FULL);
        int gemeenteIndex = cursor.getColumnIndex(CampTable.COLUMN_GEMEENTE_FULL);
        int postcodeIndex = cursor.getColumnIndex(CampTable.COLUMN_POSTCODE_FULL);

        if(cursor.getPosition() == -1){
            cursor.moveToFirst();
        }

        String id = cursor.getString(idIndex);
        String naam = cursor.getString(naamIndex);
        String omschrijving = cursor.getString(omschrijvingIndex);
        String startDatum = cursor.getString(startDatumIndex);
        String eindDatum = cursor.getString(eindDatumIndex);
        int aantalNachten = cursor.getInt(aantalNachtenIndex);
        int aantalDagen = cursor.getInt(aantalDagenIndex);
        String vervoer = cursor.getString(vervoerIndex);
        int prijs = cursor.getInt(prijsIndex);
        int maxLeeftijd = cursor.getInt(maxLeeftijdIndex);
        int minLeeftijd = cursor.getInt(minLeeftijdIndex);
        int maxDeelnemers = cursor.getInt(maxDeelnemersIndex);
        String contact = cursor.getString(contactIndex);
        String sfeerfoto = cursor.getString(sfeerfotoIndex);

        String naamgebouw = cursor.getString(naamgebouwIndex);
        String straat = cursor.getString(straatIndex);
        int huisnummer = cursor.getInt(huisnummerIndex);
        String bus = cursor.getString(busIndex);
        String gemeente = cursor.getString(gemeenteIndex);
        int postcode = cursor.getInt(postcodeIndex);

        Adres adres = new Adres(naamgebouw, straat, huisnummer, bus, gemeente, postcode);

        return new Camp(id, naam, omschrijving, startDatum, eindDatum, aantalDagen, aantalNachten, vervoer, prijs, maxLeeftijd, minLeeftijd, maxDeelnemers, contact, sfeerfoto, null, null, adres);
    }
}
