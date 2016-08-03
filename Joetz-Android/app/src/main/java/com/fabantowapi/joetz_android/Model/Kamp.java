package com.fabantowapi.joetz_android.model;

import android.media.Image;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by a_176_000 on 26-7-2016.
 */
public class Kamp implements Serializable {

    private String naam;
    private String omschrijving;
    private Date startDatum;
    private Date eindeDatum;
    private int aantalDagen;
    private int aantalNachten;
    private String vervoer;
    private String formule;
    private Double basisPrijs;
    private Double bondPrijs;
    private int kortingen;
    private int inbegrepenInPrijs;
    private int maxLeeftijd;
    private int minLeeftijd;
    private int maxDeelnemers;
    private String contact;
    private Image sfeerFoto;
    private List<Image> sfeerImages;
    private List<Medewerker> medewerkers;
    private List<Inschrijving> inschrijvingen;
    private Adres adres;

    public Kamp(String naam, String omschrijving, Date startDatum, Date eindeDatum, int aantalDagen, int aantalNachten, String vervoer, String formule, Double basisPrijs, Double bondPrijs, int kortingen, int inbegrepenInPrijs, int maxLeeftijd, int minLeeftijd, int maxDeelnemers, String contact, Image sfeerFoto, List<Image> sfeerImages,Adres adres) {
        this.naam = naam;
        this.omschrijving = omschrijving;
        this.startDatum = startDatum;
        this.eindeDatum = eindeDatum;
        this.aantalDagen = aantalDagen;
        this.aantalNachten = aantalNachten;
        this.vervoer = vervoer;
        this.formule = formule;
        this.basisPrijs = basisPrijs;
        this.bondPrijs = bondPrijs;
        this.kortingen = kortingen;
        this.inbegrepenInPrijs = inbegrepenInPrijs;
        this.maxLeeftijd = maxLeeftijd;
        this.minLeeftijd = minLeeftijd;
        this.maxDeelnemers = maxDeelnemers;
        this.contact = contact;
        this.sfeerFoto = sfeerFoto;
        this.sfeerImages = sfeerImages;
        inschrijvingen = new ArrayList<>();
        medewerkers = new ArrayList<>();
       this.adres = adres;

    }

    public Adres getAdres() {
        return adres;
    }

    public void addMedewerker(Medewerker medewerker){
        medewerkers.add(medewerker);

    }

    public void addInschrijving(Inschrijving inschrijving){
        inschrijvingen.add(inschrijving);

    }


    public List<Medewerker> getMedewerkers() {
        return medewerkers;
    }

    public List<Inschrijving> getInschrijvingen() {
        return inschrijvingen;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public Date getStartDatum() {
        return startDatum;
    }

    public void setStartDatum(Date startDatum) {
        this.startDatum = startDatum;
    }

    public Date getEindeDatum() {
        return eindeDatum;
    }

    public void setEindeDatum(Date eindeDatum) {
        this.eindeDatum = eindeDatum;
    }

    public int getAantalDagen() {
        return aantalDagen;
    }

    public void setAantalDagen(int aantalDagen) {
        this.aantalDagen = aantalDagen;
    }

    public int getAantalNachten() {
        return aantalNachten;
    }

    public void setAantalNachten(int aantalNachten) {
        this.aantalNachten = aantalNachten;
    }

    public String getVervoer() {
        return vervoer;
    }

    public void setVervoer(String vervoer) {
        this.vervoer = vervoer;
    }

    public String getFormule() {
        return formule;
    }

    public void setFormule(String formule) {
        this.formule = formule;
    }

    public Double getBasisPrijs() {
        return basisPrijs;
    }

    public void setBasisPrijs(Double basisPrijs) {
        this.basisPrijs = basisPrijs;
    }

    public Double getBondPrijs() {
        return bondPrijs;
    }

    public void setBondPrijs(Double bondPrijs) {
        this.bondPrijs = bondPrijs;
    }

    public int getKortingen() {
        return kortingen;
    }

    public void setKortingen(int kortingen) {
        this.kortingen = kortingen;
    }

    public int getInbegrepenInPrijs() {
        return inbegrepenInPrijs;
    }

    public void setInbegrepenInPrijs(int inbegrepenInPrijs) {
        this.inbegrepenInPrijs = inbegrepenInPrijs;
    }

    public int getMaxLeeftijd() {
        return maxLeeftijd;
    }

    public void setMaxLeeftijd(int maxLeeftijd) {
        this.maxLeeftijd = maxLeeftijd;
    }

    public int getMinLeeftijd() {
        return minLeeftijd;
    }

    public void setMinLeeftijd(int minLeeftijd) {
        this.minLeeftijd = minLeeftijd;
    }

    public int getMaxDeelnemers() {
        return maxDeelnemers;
    }

    public void setMaxDeelnemers(int maxDeelnemers) {
        this.maxDeelnemers = maxDeelnemers;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Image getSfeerFoto() {
        return sfeerFoto;
    }

    public void setSfeerFoto(Image sfeerFoto) {
        this.sfeerFoto = sfeerFoto;
    }

    public List<Image> getSfeerImages() {
        return sfeerImages;
    }

    public void setSfeerImages(List<Image> sfeerImages) {
        this.sfeerImages = sfeerImages;
    }
}
