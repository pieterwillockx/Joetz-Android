package com.fabantowapi.joetz_android.model;

import java.util.List;

/**
 * Created by a_176_000 on 26-7-2016.
 */
public class Contactpersoon {

    private String naam;
    private String voornaam;
    private String rijksregisternummer;
    private String telefoonNummer;
    private String email;
    private int aansluitNummer;
    private Boolean betaland;
    private Boolean ouder;
    private Adres adres;
    private List<Lid> leden;


    public Contactpersoon(String naam, String voornaam, String rijksregisternummer, String telefoonNummer, String email, int aansluitNummer, Boolean betaland, Boolean ouder, List<Lid> leden, Adres adres) {
        this.naam = naam;
        this.voornaam = voornaam;
        this.rijksregisternummer = rijksregisternummer;
        this.telefoonNummer = telefoonNummer;
        this.email = email;
        this.aansluitNummer = aansluitNummer;
        this.betaland = betaland;
        this.ouder = ouder;
        this.leden = leden;
        this.adres = adres;
    }

    public Adres getAdres() {
        return adres;
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

    public String getRijksregisternummer() {
        return rijksregisternummer;
    }

    public void setRijksregisternummer(String rijksregisternummer) {
        this.rijksregisternummer = rijksregisternummer;
    }

    public String getTelefoonNummer() {
        return telefoonNummer;
    }

    public void setTelefoonNummer(String telefoonNummer) {
        this.telefoonNummer = telefoonNummer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAansluitNummer() {
        return aansluitNummer;
    }

    public void setAansluitNummer(int aansluitNummer) {
        this.aansluitNummer = aansluitNummer;
    }

    public Boolean getBetaland() {
        return betaland;
    }

    public void setBetaland(Boolean betaland) {
        this.betaland = betaland;
    }

    public Boolean getOuder() {
        return ouder;
    }

    public void setOuder(Boolean ouder) {
        this.ouder = ouder;
    }

    public List<Lid> getLeden() {
        return leden;
    }

    public void setLeden(List<Lid> leden) {
        this.leden = leden;
    }

    public void addLid(Lid lid){
        leden.add(lid);
    }
}
