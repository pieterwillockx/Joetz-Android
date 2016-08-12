package com.fabantowapi.joetz_android.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by a_176_000 on 26-7-2016.
 */
public class Activiteit implements Serializable {

    private String naam;
    private String locatie;
    private Date begin;
    private Date einde;
    private List<Medewerker> medewerkers;

    public Activiteit(String naam, Date einde, Date begin, String locatie) {
        this.naam = naam;
        this.einde = einde;
        this.begin = begin;

        this.locatie = locatie;
        medewerkers = new ArrayList<>();

    }

    public List<Medewerker> getMedewerkers() {
        return medewerkers;
    }

    public void addDeelnemer(Medewerker deelnemer){
        medewerkers.add(deelnemer);

    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEinde() {
        return einde;
    }

    public void setEinde(Date einde) {
        this.einde = einde;
    }
}
