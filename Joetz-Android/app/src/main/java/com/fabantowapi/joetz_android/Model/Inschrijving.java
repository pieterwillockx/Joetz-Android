package com.fabantowapi.joetz_android.Model;

/**
 * Created by a_176_000 on 26-7-2016.
 */
public class Inschrijving {

    private String extraInformatie;
    private Boolean goedGekeurd;
    private Boolean betaald;

    public String getExtraInformatie() {
        return extraInformatie;
    }

    public void setExtraInformatie(String extraInformatie) {
        this.extraInformatie = extraInformatie;
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
