package com.fabantowapi.joetz_android.model.api;

import android.content.ContentValues;
import android.database.Cursor;

import com.fabantowapi.joetz_android.database.UserTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pieter on 15-8-2016.
 */
public class User {
    private String email;
    private String username;
    private String firstname;
    private String lastname;
    private Contactpersoon contactpersoon1;
    private Contactpersoon contactpersoon2;
    private String role;
    private String dateJoined;
    private String naamgebouw;
    private String straat;
    private int huisnummer;
    private String bus;
    private String gemeente;
    private int postcode;

    public User(String email, String username, String firstname, String lastname, Contactpersoon contactpersoon1, Contactpersoon contactpersoon2, String role, String dateJoined, String naamgebouw, String straat, int huisnummer, String bus, String gemeente, int postcode) {
        this.email = email;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.contactpersoon1 = contactpersoon1;
        this.contactpersoon2 = contactpersoon2;
        this.role = role;
        this.dateJoined = dateJoined;
        this.naamgebouw = naamgebouw;
        this.straat = straat;
        this.huisnummer = huisnummer;
        this.bus = bus;
        this.gemeente = gemeente;
        this.postcode = postcode;
    }

    public String getEmail() {
        return email;
    }

    public ContentValues getContentValues(){
        ContentValues cv = new ContentValues();

        cv.put(UserTable.COLUMN_EMAIL, this.email);
        cv.put(UserTable.COLUMN_USERNAME, this.username);
        cv.put(UserTable.COLUMN_FIRSTNAME, this.firstname);
        cv.put(UserTable.COLUMN_LASTNAME, this.lastname);
        cv.put(UserTable.COLUMN_ROLE, this.role);
        cv.put(UserTable.COLUMN_DATE_JOINED, this.dateJoined);
        cv.put(UserTable.COLUMN_NAAMGEBOUW, this.naamgebouw);
        cv.put(UserTable.COLUMN_STRAAT, this.straat);
        cv.put(UserTable.COLUMN_HUISNUMMER, this.huisnummer);
        cv.put(UserTable.COLUMN_BUS, this.bus);
        cv.put(UserTable.COLUMN_GEMEENTE, this.gemeente);
        cv.put(UserTable.COLUMN_POSTCODE, this.postcode);

        return cv;
    }

    public List<Contactpersoon> getContactpersonen(){
        List<Contactpersoon> contactpersonen = new ArrayList<>();
        contactpersonen.add(contactpersoon1);
        contactpersonen.add(contactpersoon2);

        return contactpersonen;
    }

    public static User constructFromCursor(Cursor cursor){
        int emailIndex = cursor.getColumnIndex(UserTable.COLUMN_EMAIL_FULL);
        int usernameIndex = cursor.getColumnIndex(UserTable.COLUMN_USERNAME_FULL);
        int firstnameIndex = cursor.getColumnIndex(UserTable.COLUMN_FIRSTNAME_FULL);
        int lastnameIndex = cursor.getColumnIndex(UserTable.COLUMN_LASTNAME_FULL);
        int roleIndex = cursor.getColumnIndex(UserTable.COLUMN_ROLE_FULL);
        int dateJoinedIndex = cursor.getColumnIndex(UserTable.COLUMN_DATE_JOINED_FULL);
        int naamgebouwIndex = cursor.getColumnIndex(UserTable.COLUMN_NAAMGEBOUW_FULL);
        int straatIndex = cursor.getColumnIndex(UserTable.COLUMN_STRAAT_FULL);
        int huisnummerIndex = cursor.getColumnIndex(UserTable.COLUMN_HUISNUMMER_FULL);
        int busIndex = cursor.getColumnIndex(UserTable.COLUMN_BUS_FULL);
        int gemeenteIndex = cursor.getColumnIndex(UserTable.COLUMN_GEMEENTE_FULL);
        int postcodeIndex = cursor.getColumnIndex(UserTable.COLUMN_POSTCODE_FULL);

        String email = cursor.getString(emailIndex);
        String username = cursor.getString(usernameIndex);
        String firstname = cursor.getString(firstnameIndex);
        String lastname = cursor.getString(lastnameIndex);
        String role = cursor.getString(roleIndex);
        String dateJoined = cursor.getString(dateJoinedIndex);
        String naamgebouw = cursor.getString(naamgebouwIndex);
        String straat = cursor.getString(straatIndex);
        int huisnummer = cursor.getInt(huisnummerIndex);
        String bus = cursor.getString(busIndex);
        String gemeente = cursor.getString(gemeenteIndex);
        int postcode = cursor.getInt(postcodeIndex);

        User user = new User(email, username, firstname, lastname, null, null, role, dateJoined, naamgebouw, straat, huisnummer, bus, gemeente, postcode);

        return user;
    }
}
