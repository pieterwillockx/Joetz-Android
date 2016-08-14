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
    private Date dateJoined;

    public class Adres{
        @SerializedName("naamgebouw")
        private String naamgebouw;
        @SerializedName("straat")
        private String straat;
        @SerializedName("huisnummer")
        private int huisnummer;
        @SerializedName("bus")
        private String bus;
        @SerializedName("gemeente")
        private String gemeente;
        @SerializedName("postcode")
        private int postcode;

        public String getNaamgebouw() { return naamgebouw; }
        public String getStraat() { return straat; }
        public int getHuisnummer() { return huisnummer; }
        public String getBus() { return bus; }
        public String getGemeente() { return gemeente; }
        public int getPostcode() { return postcode; }
    }

    public class Contactpersoon{
        @SerializedName("email")
        private String email;
        @SerializedName("firstname")
        private String firstname;
        @SerializedName("lastname")
        private String lastname;
        @SerializedName("rijksregisternummer")
        private long rijksregisternummer;
        @SerializedName("telefoonnummer")
        private String telefoonnummer;
        @SerializedName("aansluitnummer")
        private long aansluitnummer;
        @SerializedName("betalend")
        private boolean betalend;
        @SerializedName("ouder")
        private boolean ouder;
        @SerializedName("adres")
        private Adres adres;

        public String getEmail() { return email; }
        public String getFirstname() { return firstname; }
        public String getLastname() { return lastname; }
        public long getRijksregisternummer() { return rijksregisternummer; }
        public String getTelefoonnummer() { return telefoonnummer; }
        public long getAansluitnummer() { return aansluitnummer; }
        public boolean isBetalend() { return betalend; }
        public boolean isOuder() { return ouder; }
        public Adres getAdres() { return adres; }
    }

    public String getEmail() { return email; }
    public String getUsername() { return username; }
    public String getFirstname() { return firstname; }
    public String getLastname() { return lastname; }
    public Adres getAdres() { return adres; }
    public Contactpersoon getContactpersoon1() { return contactpersoon1; }
    public Contactpersoon getContactpersoon2() { return contactpersoon2; }
    public String getRole() { return role; }
    public Date getDateJoined() { return dateJoined; }
}
