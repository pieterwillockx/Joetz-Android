package com.fabantowapi.joetz_android.model;
import android.database.Cursor;

import com.fabantowapi.joetz_android.database.ArticleTable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by a_176_000 on 30-7-2016.
 */
public class Article implements Serializable {

    private int artikelId;
    private String artikelTitel;
    private String artikelInhoud;
    private String artikelImageUrl;

    public Article(int artikelId, String artikelTitel, String artikelInhoud, String artikelImageUrl) {
        this.artikelId = artikelId;
        this.artikelTitel = artikelTitel;
        this.artikelInhoud = artikelInhoud;
        this.artikelImageUrl = artikelImageUrl;
    }

    public String getArtikelTitel() {
        return artikelTitel;
    }

    public String getArtikelInhoud() {
        return artikelInhoud;
    }

    public String getArtikelImageUrl() {
        return artikelImageUrl;
    }


    public static List<Article> constructListFromCursor(Cursor cursor){
        List<Article> articles = new ArrayList<>();

        if(cursor != null && cursor.moveToFirst()){
            do{
                articles.add(Article.constructFromCursor(cursor));
            }
            while(cursor.moveToNext());
        }

        return articles;
    }

    public static Article constructFromCursor(Cursor cursor){
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

        return new Article(id, titel, tekst,fotoUrl);
    }
}