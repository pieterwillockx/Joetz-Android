package com.fabantowapi.joetz_android.model;

import java.util.ArrayList;
import java.util.List;

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
    private List<Medewerker> medewerkers;
    private List<Contactpersoon> contactpersonen;
    private List<Lid> leden;
    private List<Kamp> kampen;

    public Adres(String naamGebouw, String straatNaam, int huisNummer, int bus, String gemeente, int postcode,List<Lid> leden,List<Kamp> kampen) {
        this.naamGebouw = naamGebouw;
        this.straatNaam = straatNaam;
        this.huisNummer = huisNummer;
        this.bus = bus;
        this.gemeente = gemeente;
        this.postcode = postcode;
        this.leden = leden;
        this.kampen = kampen;
        medewerkers = new ArrayList<>();
        contactpersonen = new ArrayList<>();

    }

    public List<Kamp> getKampen() {
        return kampen;
    }

    public void addKamp(Kamp kamp){
        kampen.add(kamp);
    }
    public void addLid(Lid lid){
        leden.add(lid);
    }

    public List<Lid> getLeden() {
        return leden;
    }

    public void addMedewerker(Medewerker medewerker){
        medewerkers.add(medewerker);

    }
    public void addContactPersoon(Contactpersoon contactpersoon){
        contactpersonen.add(contactpersoon);
    }

    public List<Medewerker> getMedewerkers() {
        return medewerkers;
    }

    public List<Contactpersoon> getContactpersonen() {
        return contactpersonen;
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
