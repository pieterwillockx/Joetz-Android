package com.fabantowapi.joetz_android.model.api;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Pieter on 14-8-2016.
 */
public class GetUserResponse {
    @SerializedName("email")
    private String email;
    @SerializedName("username")
    private String username;
    @SerializedName("firstname")
    private String firstname;
    @SerializedName("lastname")
    private String lastname;
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

    public String getEmail() { return email; }
    public String getUsername() { return username; }
    public String getFirstname() { return firstname; }
    public String getLastname() { return lastname; }
    public Adres getAdres() { return adres; }
    public Contactpersoon getContactpersoon1() { return contactpersoon1; }
    public Contactpersoon getContactpersoon2() { return contactpersoon2; }
    public String getRole() { return role; }
    public String getDateJoined() { return dateJoined; }

    public User getUser(){
        User user = new User(email, username, firstname, lastname, contactpersoon1, contactpersoon2, role, dateJoined, adres.getNaamgebouw(), adres.getStraat(), adres.getHuisnummer(), adres.getBus(), adres.getGemeente(), adres.getPostcode());
        return user;
    }
}
