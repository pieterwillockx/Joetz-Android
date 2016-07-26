package com.fabantowapi.joetz_android.Model;

/**
 * Created by a_176_000 on 26-7-2016.
 */
public class Adres {

    private String naamGebouw;
    private String straatNaam;
    private int huisNummer;
    private int bus;
    private String gemeente;
    private int postcode;

    public Adres(String naamGebouw, String straatNaam, int huisNummer, int bus, String gemeente, int postcode) {
        this.naamGebouw = naamGebouw;
        this.straatNaam = straatNaam;
        this.huisNummer = huisNummer;
        this.bus = bus;
        this.gemeente = gemeente;
        this.postcode = postcode;
    }

    public String getNaamGebouw() {
        return naamGebouw;
    }

    public String getStraatNaam() {
        return straatNaam;
    }

    public int getHuisNummer() {
        return huisNummer;
    }

    public int getBus() {
        return bus;
    }

    public String getGemeente() {
        return gemeente;
    }

    public int getPostcode() {
        return postcode;
    }
}
