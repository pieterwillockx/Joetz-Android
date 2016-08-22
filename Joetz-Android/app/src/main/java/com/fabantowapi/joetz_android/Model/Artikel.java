package com.fabantowapi.joetz_android.model;

import android.database.Cursor;
import android.text.Html;

import com.fabantowapi.joetz_android.database.ArticleTable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by a_176_000 on 30-7-2016.
 */
public class Artikel implements Serializable {

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


    public static List<Artikel> constructListFromCursor(Cursor cursor){
        List<Artikel> articles = new ArrayList<>();

        if(cursor != null && cursor.moveToFirst()){
            do{
                articles.add(Artikel.constructFromCursor(cursor));
            }
            while(cursor.moveToNext());
        }

        return articles;
    }

    public static Artikel constructFromCursor(Cursor cursor){
        int idIndex = cursor.getColumnIndex(ArticleTable.COLUMN_ID_FULL);
        int titelIndex = cursor.getColumnIndex(ArticleTable.COLUMN_TITEL_FULL);
        int tekstIndex = cursor.getColumnIndex(ArticleTable.COLUMN_TEKST_FULL);
        int fotoUrlIndex = cursor.getColumnIndex(ArticleTable.COLUMN_FOTO_URL_FULL);
        if(cursor.getPosition() == -1){
            cursor.moveToFirst();
        }

        int id = cursor.getInt(idIndex);
        String titel = cursor.getString(titelIndex);
        String tekst = cursor.getString(tekstIndex);
        String fotoUrl = cursor.getString(fotoUrlIndex);

        return new Artikel(id, titel, tekst,fotoUrl);
    }
}
