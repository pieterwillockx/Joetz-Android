package com.fabantowapi.joetz_android.model;

import android.media.Image;

/**
 * Created by a_176_000 on 30-7-2016.
 */
public class Artikel {

    private int artikelId;
    private String artikelTitel;
    private String artikelInhoud;
    private String artikelImageUrl;

    public Artikel(int artikelId, String artikelTitel, String artikelInhoud, String artikelImageUrl) {
        this.artikelId = artikelId;
        this.artikelTitel = artikelTitel;
        this.artikelInhoud = artikelInhoud;
        this.artikelImageUrl = artikelImageUrl;
    }

    public int getArtikelId() {
        return artikelId;
    }

    public void setArtikelId(int artikelId) {
        this.artikelId = artikelId;
    }

    public String getArtikelTitel() {
        return artikelTitel;
    }

    public void setArtikelTitel(String artikelTitel) {
        this.artikelTitel = artikelTitel;
    }

    public String getArtikelInhoud() {
        return artikelInhoud;
    }

    public void setArtikelInhoud(String artikelInhoud) {
        this.artikelInhoud = artikelInhoud;
    }

    public String getArtikelImageUrl() {
        return artikelImageUrl;
    }

    public void setArtikelImageUrl(String artikelImageUrl) {
        this.artikelImageUrl = artikelImageUrl;
    }
}
