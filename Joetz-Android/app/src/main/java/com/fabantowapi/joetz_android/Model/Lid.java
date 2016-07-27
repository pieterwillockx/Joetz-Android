package com.fabantowapi.joetz_android.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by a_176_000 on 26-7-2016.
 */
public class Lid extends Gebruiker{

    private Date geboorteDatum;
    private int codeGerechtigde;
    private List<Contactpersoon> contactpersonen;
    private List<Inschrijving> inschrijvingen;
    private Adres adres;


    public Lid(String email, String wachtwoord, String naam, String voornaam, Boolean geactiveerd, String rijksregisternummer, Date geboorteDatum, int codeGerechtigde,Adres adres) {
        super(email, wachtwoord, naam, voornaam, geactiveerd, rijksregisternummer);
        this.geboorteDatum = geboorteDatum;
        this.codeGerechtigde = codeGerechtigde;
        contactpersonen = new ArrayList<>();
        inschrijvingen = new ArrayList<>();
        this.adres = adres;
    }

    public void addInschrijving(Inschrijving inschrijving){
        inschrijvingen.add(inschrijving);

    }
    public void addContactpersoon(Contactpersoon contactpersoon){
        contactpersonen.add(contactpersoon);
    }

    public Date getGeboorteDatum() {
        return geboorteDatum;
    }

    public int getCodeGerechtigde() {
        return codeGerechtigde;
    }

    public List<Contactpersoon> getContactpersonen() {
        return contactpersonen;
    }

    public List<Inschrijving> getInschrijvingen() {
        return inschrijvingen;
    }
}
