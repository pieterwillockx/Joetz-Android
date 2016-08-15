package com.fabantowapi.joetz_android.model.api;

import android.content.ContentValues;

import com.fabantowapi.joetz_android.database.ContactpersoonTable;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Pieter on 15-8-2016.
 */
public class Contactpersoon {
    private int id;
    @SerializedName("email")
    private String email;
    @SerializedName("firstname")
    private String firstname;
    @SerializedName("lastname")
    private String lastname;
    @SerializedName("rijksregisternummer")
    private long rijksregisternummer;
    @SerializedName("telefoonnummer")
    private String telefoonnummer;
    @SerializedName("aansluitnummer")
    private long aansluitnummer;
    @SerializedName("betalend")
    private boolean betalend;
    @SerializedName("ouder")
    private boolean ouder;
    @SerializedName("adres")
    private Adres adres;

    public ContentValues getContentValues(int contactpersoonId){
        ContentValues cv = new ContentValues();

        cv.put(ContactpersoonTable.COLUMN_ID, contactpersoonId);
        cv.put(ContactpersoonTable.COLUMN_EMAIL, this.email);
        cv.put(ContactpersoonTable.COLUMN_VOORNAAM, this.firstname);
        cv.put(ContactpersoonTable.COLUMN_NAAM, this.lastname);
        cv.put(ContactpersoonTable.COLUMN_RIJKSREGISTERNUMMER, this.rijksregisternummer);
        cv.put(ContactpersoonTable.COLUMN_TELEFOONNUMMER, this.telefoonnummer);
        cv.put(ContactpersoonTable.COLUMN_AANSLUITNUMMER, this.aansluitnummer);
        cv.put(ContactpersoonTable.COLUMN_BETALEND, this.betalend);
        cv.put(ContactpersoonTable.COLUMN_OUDER, this.ouder);
        cv.put(ContactpersoonTable.COLUMN_NAAMGEBOUW, this.adres.getNaamgebouw());
        cv.put(ContactpersoonTable.COLUMN_STRAAT, this.adres.getStraat());
        cv.put(ContactpersoonTable.COLUMN_HUISNUMMER, this.adres.getHuisnummer());
        cv.put(ContactpersoonTable.COLUMN_BUS, this.adres.getBus());
        cv.put(ContactpersoonTable.COLUMN_GEMEENTE, this.adres.getGemeente());
        cv.put(ContactpersoonTable.COLUMN_POSTCODE, this.adres.getPostcode());

        return cv;
    }
}
