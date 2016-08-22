package com.fabantowapi.joetz_android.model.api;

import android.content.ContentValues;

import com.fabantowapi.joetz_android.database.ArticleTable;
import com.google.gson.annotations.SerializedName;


public class GetArticleResponse {
    @SerializedName("id")
    private String id;
    @SerializedName("titel")
    private String titel;
    @SerializedName("tekst")
    private String tekst;
    @SerializedName("foto")
    private String foto;

    public GetArticleResponse(String id, String titel, String tekst,String foto) {
        this.id = id;
        this.titel = titel;
        this.tekst = tekst;
        this.foto = foto;
    }

    public ContentValues getContentValues(){
        ContentValues cv = new ContentValues();

        cv.put(ArticleTable.COLUMN_ID, this.id);
        cv.put(ArticleTable.COLUMN_TITEL, this.titel);
        cv.put(ArticleTable.COLUMN_TEKST, this.tekst);
        cv.put(ArticleTable.COLUMN_FOTO_URL, this.foto);

        return cv;
    }


}
