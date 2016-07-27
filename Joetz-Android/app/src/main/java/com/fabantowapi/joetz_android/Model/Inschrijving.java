package com.fabantowapi.joetz_android.model;

/**
 * Created by a_176_000 on 26-7-2016.
 */
public class Inschrijving {

    private String extraInformatie;
    private Boolean goedGekeurd;
    private Boolean betaald;
    private Lid lid;
    private Kamp kamp;

    public String getExtraInformatie() {
        return extraInformatie;
    }

    public void setExtraInformatie(String extraInformatie, Lid lid,Kamp kamp) {
        this.extraInformatie = extraInformatie;
        this.lid = lid;
        this.kamp = kamp;
    }

    public Lid getLid() {
        return lid;
    }

    public Kamp getKamp() {
        return kamp;
    }

    public Boolean getGoedGekeurd() {
        return goedGekeurd;
    }

    public void setGoedGekeurd(Boolean goedGekeurd) {
        this.goedGekeurd = goedGekeurd;
    }

    public Boolean getBetaald() {
        return betaald;
    }

    public void setBetaald(Boolean betaald) {
        this.betaald = betaald;
    }
}
