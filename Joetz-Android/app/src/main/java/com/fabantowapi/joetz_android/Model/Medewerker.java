package com.fabantowapi.joetz_android.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a_176_000 on 26-7-2016.
 */
public class Medewerker extends Gebruiker{

    private SoortMedewerker soortMedewerker;
    private List<Activiteit> activiteiten;
    private Adres adres;
    private List<Kamp> kampen;

    public Medewerker(String email, String wachtwoord, String naam, String voornaam, Boolean geactiveerd, String rijksregisternummer, SoortMedewerker soortMedewerker,Adres adres) {
        super(email, wachtwoord, naam, voornaam, geactiveerd, rijksregisternummer);
        this.soortMedewerker = soortMedewerker;
        this.adres = adres;
        activiteiten = new ArrayList<>();
        kampen = new ArrayList<>();

    }

    public List<Kamp> getKampen() {
        return kampen;
    }

    public void addKamp(Kamp kamp){

        kampen.add(kamp);
    }
    public SoortMedewerker getSoortMedewerker() {
        return soortMedewerker;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public List<Activiteit> getActiviteiten() {
        return activiteiten;
    }

    public void setSoortMedewerker(SoortMedewerker soortMedewerker) {
        this.soortMedewerker = soortMedewerker;
    }

    public void addActiviteit(Activiteit a){
        activiteiten.add(a);

    }

}
