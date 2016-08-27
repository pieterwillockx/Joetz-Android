package com.fabantowapi.joetz_android.model.api;

import android.content.ContentValues;

import com.fabantowapi.joetz_android.database.CampTable;
import com.fabantowapi.joetz_android.database.ContributorCampTable;
import com.fabantowapi.joetz_android.model.Adres;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Pieter on 18-8-2016.
 */
public class GetCampResponse {
    @SerializedName("id")
    private String id;
    @SerializedName("naam")
    private String naam;
    @SerializedName("omschrijving")
    private String omschrijving;
    @SerializedName("startDatum")
    private String startDatum;
    @SerializedName("eindDatum")
    private String eindDatum;
    @SerializedName("aantalDagen")
    private int aantalDagen;
    @SerializedName("aantalNachten")
    private int aantalNachten;
    @SerializedName("vervoer")
    private String vervoer;
    @SerializedName("prijs")
    private int prijs;
    @SerializedName("maxLeeftijd")
    private int maxLeeftijd;
    @SerializedName("minLeeftijd")
    private int minLeeftijd;
    @SerializedName("maxDeelnemers")
    private int maxDeelnemers;
    @SerializedName("contact")
    private String contact;
    @SerializedName("sfeerfoto")
    private String sfeerfoto;
    @SerializedName("fotos")
    private String[] fotos;
    @SerializedName("medewerkers")
    private String[] medewerkers;
    @SerializedName("adres")
    private Adres adres;

    public GetCampResponse(String id, String naam, String omschrijving, String startDatum, String eindDatum, int aantalDagen, int aantalNachten, String vervoer, int prijs, int maxLeeftijd, int minLeeftijd, int maxDeelnemers, String contact, String sfeerfoto, String[] fotos, String[] medewerkers, Adres adres) {
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
        this.medewerkers = medewerkers;
        this.adres = adres;
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

    public ContentValues[] getContributorCampContentValues(){
        ContentValues[] cvArray = new ContentValues[this.medewerkers != null ? this.medewerkers.length : 0];

        for(int i = 0; i < this.medewerkers.length; i++){
            ContentValues cv = new ContentValues();

            cv.put(ContributorCampTable.COLUMN_CAMP_ID, this.id);
            cv.put(ContributorCampTable.COLUMN_CONTRIBUTOR_ID, this.medewerkers[i]);

            cvArray[i] = cv;
        }

        return cvArray;
    }
}
