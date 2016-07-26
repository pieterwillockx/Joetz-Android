package com.fabantowapi.joetz_android.model;

import java.util.Date;

/**
 * Created by a_176_000 on 26-7-2016.
 */
public class Lid extends Gebruiker{

    private Date geboorteDatum;
    private int codeGerechtigde;


    public Lid(String email, String wachtwoord, String naam, String voornaam, Boolean geactiveerd, String rijksregisternummer, Date geboorteDatum, int codeGerechtigde) {
        super(email, wachtwoord, naam, voornaam, geactiveerd, rijksregisternummer);
        this.geboorteDatum = geboorteDatum;
        this.codeGerechtigde = codeGerechtigde;
    }
}
