package com.fabantowapi.joetz_android.model.api;

import com.fabantowapi.joetz_android.model.Adres;
import com.fabantowapi.joetz_android.model.Contactpersoon;
import com.fabantowapi.joetz_android.model.User;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Pieter on 14-8-2016.
 */
public class GetUserResponse {
    @SerializedName("id")
    private String id;
    @SerializedName("email")
    private String email;
    @SerializedName("username")
    private String username;
    @SerializedName("firstname")
    private String firstname;
    @SerializedName("lastname")
    private String lastname;
    @SerializedName("rijksregisternummer")
    private String rijksregisternummer;
    @SerializedName("geboortedatum")
    private String geboortedatum;
    @SerializedName("codegerechtigde")
    private String codegerechtigde;
    @SerializedName("adres")
    private Adres adres;
    @SerializedName("contactpersoon1")
    private Contactpersoon contactpersoon1;
    @SerializedName("contactpersoon2")
    private Contactpersoon contactpersoon2;
    @SerializedName("role")
    private String role;
    @SerializedName("dateJoined")
    private String dateJoined;

    public String getId() { return id; }
    public String getEmail() { return email; }
    public String getUsername() { return username; }
    public String getFirstname() { return firstname; }
    public String getLastname() { return lastname; }
    public String getRijksregisternummer() { return rijksregisternummer; }
    public String getGeboortedatum() { return geboortedatum; }
    public String getCodegerechtigde() { return codegerechtigde; }
    public Adres getAdres() { return adres; }
    public Contactpersoon getContactpersoon1() { return contactpersoon1; }
    public Contactpersoon getContactpersoon2() { return contactpersoon2; }
    public String getRole() { return role; }
    public String getDateJoined() { return dateJoined; }

    public User getUser(){
        User user = new User(id, email, username, firstname, lastname, rijksregisternummer, geboortedatum, codegerechtigde, contactpersoon1.getEmail(), contactpersoon2.getEmail(), contactpersoon1, contactpersoon2, role, dateJoined, adres);
        return user;
    }
}
