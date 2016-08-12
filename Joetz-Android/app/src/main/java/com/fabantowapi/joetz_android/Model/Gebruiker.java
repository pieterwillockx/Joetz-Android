package com.fabantowapi.joetz_android.model;

/**
 * Created by a_176_000 on 26-7-2016.
 */
public abstract class Gebruiker {

    private String email;
    private String wachtwoord;
    private String naam;
    private String voornaam;
    private Boolean geactiveerd;
    private String rijksregisternummer;

    public Gebruiker(){}
    public Gebruiker(String email, String wachtwoord, String naam, String voornaam, Boolean geactiveerd, String rijksregisternummer) {
        this.email = email;
        this.wachtwoord = wachtwoord;
        this.naam = naam;
        this.voornaam = voornaam;
        this.geactiveerd = geactiveerd;
        this.rijksregisternummer = rijksregisternummer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public Boolean getGeactiveerd() {
        return geactiveerd;
    }

    public void setGeactiveerd(Boolean geactiveerd) {
        this.geactiveerd = geactiveerd;
    }

    public String getRijksregisternummer() {
        return rijksregisternummer;
    }

    public void setRijksregisternummer(String rijksregisternummer) {
        this.rijksregisternummer = rijksregisternummer;
    }
}
