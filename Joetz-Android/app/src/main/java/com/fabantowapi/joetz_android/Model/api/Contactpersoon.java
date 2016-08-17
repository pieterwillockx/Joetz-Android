package com.fabantowapi.joetz_android.model.api;

import android.content.ContentValues;
import android.database.Cursor;

import com.fabantowapi.joetz_android.database.ContactpersoonTable;
import com.fabantowapi.joetz_android.database.UserTable;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Pieter on 15-8-2016.
 */
public class Contactpersoon {
    @SerializedName("email")
    private String email;
    @SerializedName("firstname")
    private String firstname;
    @SerializedName("lastname")
    private String lastname;
    @SerializedName("rijksregisternummer")
    private String rijksregisternummer;
    @SerializedName("telefoonnummer")
    private String telefoonnummer;
    @SerializedName("aansluitnummer")
    private String aansluitnummer;
    @SerializedName("betalend")
    private boolean betalend;
    @SerializedName("ouder")
    private boolean ouder;
    @SerializedName("adres")
    private Adres adres;

    public Contactpersoon(String email, String firstname, String lastname, String rijksregisternummer, String telefoonnummer, String aansluitnummer, boolean betalend, boolean ouder, Adres adres){
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.rijksregisternummer = rijksregisternummer;
        this.telefoonnummer = telefoonnummer;
        this.aansluitnummer = aansluitnummer;
        this.betalend = betalend;
        this.ouder = ouder;
        this.adres = adres;
    }

    public String getEmail() {
        return email;
    }
    public String getFirstname() { return firstname; }
    public String getLastname() { return lastname; }
    public String getTelefoonnummer() { return telefoonnummer; }
    public String getRijksregisternummer() { return rijksregisternummer; }
    public String getAansluitnummer() { return aansluitnummer; }
    public boolean isBetalend() { return betalend; }
    public boolean isOuder() { return ouder; }
    public Adres getAdres() { return adres; }

    public ContentValues getContentValues(){
        ContentValues cv = new ContentValues();

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

    public static Contactpersoon constructFromCursor(Cursor cursor){
        int emailIndex = cursor.getColumnIndex(ContactpersoonTable.COLUMN_EMAIL_FULL);
        int firstnameIndex = cursor.getColumnIndex(ContactpersoonTable.COLUMN_VOORNAAM_FULL);
        int lastnameIndex = cursor.getColumnIndex(ContactpersoonTable.COLUMN_NAAM_FULL);
        int rijksregisternummerIndex = cursor.getColumnIndex(ContactpersoonTable.COLUMN_RIJKSREGISTERNUMMER_FULL);
        int telefoonnummerIndex = cursor.getColumnIndex(ContactpersoonTable.COLUMN_TELEFOONNUMMER_FULL);
        int aansluitnummerIndex = cursor.getColumnIndex(ContactpersoonTable.COLUMN_AANSLUITNUMMER_FULL);
        int betalendIndex = cursor.getColumnIndex(ContactpersoonTable.COLUMN_BETALEND_FULL);
        int ouderIndex = cursor.getColumnIndex(ContactpersoonTable.COLUMN_OUDER_FULL);
        int naamgebouwIndex = cursor.getColumnIndex(ContactpersoonTable.COLUMN_NAAMGEBOUW_FULL);
        int straatIndex = cursor.getColumnIndex(ContactpersoonTable.COLUMN_STRAAT_FULL);
        int huisnummerIndex = cursor.getColumnIndex(ContactpersoonTable.COLUMN_HUISNUMMER_FULL);
        int busIndex = cursor.getColumnIndex(ContactpersoonTable.COLUMN_BUS_FULL);
        int gemeenteIndex = cursor.getColumnIndex(ContactpersoonTable.COLUMN_GEMEENTE_FULL);
        int postcodeIndex = cursor.getColumnIndex(ContactpersoonTable.COLUMN_POSTCODE_FULL);

        cursor.moveToFirst();

        String email = cursor.getString(emailIndex);
        String firstname = cursor.getString(firstnameIndex);
        String lastname = cursor.getString(lastnameIndex);
        String rijksregisternummer = cursor.getString(rijksregisternummerIndex);
        String telefoonnummer = cursor.getString(telefoonnummerIndex);
        String aansluitnummer = cursor.getString(aansluitnummerIndex);
        boolean betalend = cursor.getInt(betalendIndex) != 0;
        boolean ouder = cursor.getInt(ouderIndex) != 0;
        String naamgebouw = cursor.getString(naamgebouwIndex);
        String straat = cursor.getString(straatIndex);
        int huisnummer = cursor.getInt(huisnummerIndex);
        String bus = cursor.getString(busIndex);
        String gemeente = cursor.getString(gemeenteIndex);
        int postcode = cursor.getInt(postcodeIndex);

        Adres adres = new Adres(naamgebouw, straat, huisnummer, bus, gemeente, postcode);

        Contactpersoon contactpersoon = new Contactpersoon(email, firstname, lastname, rijksregisternummer, telefoonnummer, aansluitnummer, betalend, ouder, adres);

        return contactpersoon;
    }
}
