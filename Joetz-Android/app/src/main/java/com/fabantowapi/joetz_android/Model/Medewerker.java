package com.fabantowapi.joetz_android.model;

/**
 * Created by a_176_000 on 26-7-2016.
 */
public class Medewerker extends Gebruiker{

    private SoortMedewerker soortMedewerker;

    public Medewerker(String email, String wachtwoord, String naam, String voornaam, Boolean geactiveerd, String rijksregisternummer, SoortMedewerker soortMedewerker) {
        super(email, wachtwoord, naam, voornaam, geactiveerd, rijksregisternummer);
        this.soortMedewerker = soortMedewerker;
    }
    public SoortMedewerker getSoortMedewerker() {
        return soortMedewerker;
    }

    public void setSoortMedewerker(SoortMedewerker soortMedewerker) {
        this.soortMedewerker = soortMedewerker;
    }


}
