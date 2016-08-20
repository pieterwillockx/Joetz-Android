package com.fabantowapi.joetz_android.model.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Pieter on 19-8-2016.
 */
public class CreateCampRequest {
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
    @SerializedName("naamgebouw")
    private String naamgebouw;
    @SerializedName("straat")
    private String straat;
    @SerializedName("huisnummer")
    private int huisnummer;
    @SerializedName("bus")
    private String bus;
    @SerializedName("gemeente")
    private String gemeente;
    @SerializedName("postcode")
    private int postcode;

    public CreateCampRequest(String naam, String omschrijving, String startDatum, String eindDatum, int aantalDagen, int aantalNachten, String vervoer, int prijs, int maxLeeftijd, int minLeeftijd, int maxDeelnemers, String contact, String sfeerfoto, String naamgebouw, String straat, int huisnummer, String bus, String gemeente, int postcode) {
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
        this.naamgebouw = naamgebouw;
        this.straat = straat;
        this.huisnummer = huisnummer;
        this.bus = bus;
        this.gemeente = gemeente;
        this.postcode = postcode;
    }
}
