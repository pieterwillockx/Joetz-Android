package com.fabantowapi.joetz_android.model.api;

import android.content.ContentValues;
import android.database.Cursor;

import com.fabantowapi.joetz_android.contentproviders.ContactpersoonContentProvider;
import com.fabantowapi.joetz_android.database.ContactpersoonTable;
import com.fabantowapi.joetz_android.database.UserTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pieter on 15-8-2016.
 */
public class User {
    private String id;
    private String email;
    private String username;
    private String firstname;
    private String lastname;
    private String rijksregisternummer;
    private String geboortedatum;
    private String codegerechtigde;
    private String contactpersoon1Email;
    private String contactpersoon2Email;
    private Contactpersoon contactpersoon1;
    private Contactpersoon contactpersoon2;
    private String role;
    private String dateJoined;
    private Adres adres;

    public User(String id, String email, String username, String firstname, String lastname, String rijksregisternummer, String geboortedatum, String codegerechtigde, String contactpersoon1Email, String contactpersoon2Email, Contactpersoon contactpersoon1, Contactpersoon contactpersoon2, String role, String dateJoined, Adres adres) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.rijksregisternummer = rijksregisternummer;
        this.geboortedatum = geboortedatum;
        this.codegerechtigde = codegerechtigde;
        this.contactpersoon1Email = contactpersoon1Email;
        this.contactpersoon2Email = contactpersoon2Email;
        this.contactpersoon1 = contactpersoon1;
        this.contactpersoon2 = contactpersoon2;
        this.role = role;
        this.dateJoined = dateJoined;
        this.adres = adres;
    }

    public void setFirstname(String firstname) { this.firstname = firstname; }
    public void setLastname(String lastname) { this.lastname = lastname; }
    public void setRijksregisternummer(String rijksregisternummer) { this.rijksregisternummer = rijksregisternummer; }
    public void setGeboortedatum(String geboortedatum) { this.geboortedatum = geboortedatum; }

    public void setCodegerechtigde(String codegerechtigde) { this.codegerechtigde = codegerechtigde; }

    public void setContactpersoon1(Contactpersoon contactpersoon1) {
        this.contactpersoon1 = contactpersoon1;
        this.contactpersoon1Email = contactpersoon1.getEmail();
    }
    public void setContactpersoon2(Contactpersoon contactpersoon2) {
        this.contactpersoon2 = contactpersoon2;
        this.contactpersoon2Email = contactpersoon2.getEmail();
    }

    public void setAdres(Adres adres){ this.adres = adres; }

    public String getId() { return id; }
    public String getEmail() {
        return email;
    }
    public String getFirstname() { return firstname; }
    public String getLastname() { return lastname; }
    public String getRijksregisternummer() { return rijksregisternummer; }
    public String getGeboortedatum() { return geboortedatum; }
    public String getCodegerechtigde() { return codegerechtigde; }
    public String getUsername() { return username; }
    public Contactpersoon getContactpersoon1() { return contactpersoon1; }
    public Contactpersoon getContactpersoon2() { return contactpersoon2; }
    public String getRole() { return role; }
    public String getDateJoined() { return dateJoined; }
    public Adres getAdres() { return adres; }

    public String getContactpersoon1Email() { return contactpersoon1Email; }
    public String getContactpersoon2Email() { return contactpersoon2Email; }

    public ContentValues getContentValues(){
        ContentValues cv = new ContentValues();

        cv.put(UserTable.COLUMN_ID, this.id);
        cv.put(UserTable.COLUMN_EMAIL, this.email);
        cv.put(UserTable.COLUMN_USERNAME, this.username);
        cv.put(UserTable.COLUMN_FIRSTNAME, this.firstname);
        cv.put(UserTable.COLUMN_LASTNAME, this.lastname);
        cv.put(UserTable.COLUMN_RIJKSREGISTERNUMMER, this.rijksregisternummer);
        cv.put(UserTable.COLUMN_GEBOORTEDATUM, this.geboortedatum);
        cv.put(UserTable.COLUMN_CODEGERECHTIGDE, this.codegerechtigde);
        cv.put(UserTable.COLUMN_ROLE, this.role);
        cv.put(UserTable.COLUMN_DATE_JOINED, this.dateJoined);
        cv.put(UserTable.COLUMN_NAAMGEBOUW, this.adres.getNaamgebouw());
        cv.put(UserTable.COLUMN_STRAAT, this.adres.getStraat());
        cv.put(UserTable.COLUMN_HUISNUMMER, this.adres.getHuisnummer());
        cv.put(UserTable.COLUMN_BUS, this.adres.getBus());
        cv.put(UserTable.COLUMN_GEMEENTE, this.adres.getGemeente());
        cv.put(UserTable.COLUMN_POSTCODE, this.adres.getPostcode());
        cv.put(UserTable.COLUMN_CONTACTPERSOON1_EMAIL, this.contactpersoon1Email);
        cv.put(UserTable.COLUMN_CONTACTPERSOON2_EMAIL, this.contactpersoon2Email);

        return cv;
    }

    public List<Contactpersoon> getContactpersonen(){
        List<Contactpersoon> contactpersonen = new ArrayList<>();
        contactpersonen.add(contactpersoon1);
        contactpersonen.add(contactpersoon2);

        return contactpersonen;
    }

    public static List<User> constructListFromCursor(Cursor cursor){
        List<User> users = new ArrayList<>();

        if(cursor != null && cursor.moveToFirst()){
            do{
                users.add(User.constructFromCursor(cursor));
            }
            while(cursor.moveToNext());
        }

        return users;
    }

    public static User constructFromCursor(Cursor cursor){
        int idIndex = cursor.getColumnIndex(UserTable.COLUMN_ID_FULL);
        int emailIndex = cursor.getColumnIndex(UserTable.COLUMN_EMAIL_FULL);
        int usernameIndex = cursor.getColumnIndex(UserTable.COLUMN_USERNAME_FULL);
        int firstnameIndex = cursor.getColumnIndex(UserTable.COLUMN_FIRSTNAME_FULL);
        int lastnameIndex = cursor.getColumnIndex(UserTable.COLUMN_LASTNAME_FULL);
        int roleIndex = cursor.getColumnIndex(UserTable.COLUMN_ROLE_FULL);
        int dateJoinedIndex = cursor.getColumnIndex(UserTable.COLUMN_DATE_JOINED_FULL);
        int contactpersoon1EmailIndex = cursor.getColumnIndex(UserTable.COLUMN_CONTACTPERSOON1_EMAIL_FULL);
        int contactpersoon2EmailIndex = cursor.getColumnIndex(UserTable.COLUMN_CONTACTPERSOON2_EMAIL_FULL);
        int naamgebouwIndex = cursor.getColumnIndex(UserTable.COLUMN_NAAMGEBOUW_FULL);
        int straatIndex = cursor.getColumnIndex(UserTable.COLUMN_STRAAT_FULL);
        int huisnummerIndex = cursor.getColumnIndex(UserTable.COLUMN_HUISNUMMER_FULL);
        int busIndex = cursor.getColumnIndex(UserTable.COLUMN_BUS_FULL);
        int gemeenteIndex = cursor.getColumnIndex(UserTable.COLUMN_GEMEENTE_FULL);
        int postcodeIndex = cursor.getColumnIndex(UserTable.COLUMN_POSTCODE_FULL);
        int rijksregisternummerIndex = cursor.getColumnIndex(UserTable.COLUMN_RIJKSREGISTERNUMMER_FULL);
        int geboortedatumIndex = cursor.getColumnIndex(UserTable.COLUMN_GEBOORTEDATUM_FULL);
        int codegerechtigdeIndex = cursor.getColumnIndex(UserTable.COLUMN_CODEGERECHTIGDE_FULL);

        if(cursor.getPosition() == -1){
            cursor.moveToFirst();
        }

        String id = cursor.getString(idIndex);
        String email = cursor.getString(emailIndex);
        String username = cursor.getString(usernameIndex);
        String firstname = cursor.getString(firstnameIndex);
        String lastname = cursor.getString(lastnameIndex);
        String role = cursor.getString(roleIndex);
        String dateJoined = cursor.getString(dateJoinedIndex);
        String contactpersoon1Email = cursor.getString(contactpersoon1EmailIndex);
        String contactpersoon2Email = cursor.getString(contactpersoon2EmailIndex);
        String naamgebouw = cursor.getString(naamgebouwIndex);
        String straat = cursor.getString(straatIndex);
        int huisnummer = cursor.getInt(huisnummerIndex);
        String bus = cursor.getString(busIndex);
        String gemeente = cursor.getString(gemeenteIndex);
        int postcode = cursor.getInt(postcodeIndex);
        String rijksregisternummer = cursor.getString(rijksregisternummerIndex);
        String geboortedatum = cursor.getString(geboortedatumIndex);
        String codegerechtigde = cursor.getString(codegerechtigdeIndex);

        Adres adres = new Adres(naamgebouw, straat, huisnummer, bus, gemeente, postcode);

        //String selection = ContactpersoonTable.TABLE_NAME + "." + ContactpersoonTable.COLUMN_EMAIL + " = ?";
        //String[] selectionArgs = new String[]{
        //        contactpersoon2Email
        //};
        //
        //Cursor contactpersoon1Cursor = ContactpersoonContentProvider.query(ContactpersoonContentProvider.CONTENT_URI, null, selection, selectionArgs, "");

        User user = new User(id, email, username, firstname, lastname, rijksregisternummer, geboortedatum, codegerechtigde, contactpersoon1Email, contactpersoon2Email, null, null, role, dateJoined, adres);

        return user;
    }

    @Override
    public String toString(){
        String out = "User " + this.firstname + " " + this.lastname + " with email " + this.email + ":\n";
        if(this.contactpersoon1 != null) {
            out += "Contactpersoon 1: " + contactpersoon1.getEmail() + "\n";
        }
        else{
            out += "Contactpersoon 1: Niet opgegeven\n";
        }
        if(this.contactpersoon2 != null){
            out += "Contactpersoon 2: " + contactpersoon2.getEmail() + "\n";
        }
        else{
            out += "Contactpersoon 1: Niet opgegeven\n";
        }

        return out;
    }
}
