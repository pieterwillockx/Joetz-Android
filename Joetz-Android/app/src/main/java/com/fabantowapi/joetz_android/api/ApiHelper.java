package com.fabantowapi.joetz_android.api;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;

import rx.Observable;

import com.fabantowapi.joetz_android.contentproviders.ActivityContentProvider;
import com.fabantowapi.joetz_android.contentproviders.ArticleContentProvider;
import com.fabantowapi.joetz_android.contentproviders.CampContentProvider;
import com.fabantowapi.joetz_android.contentproviders.ContactpersoonContentProvider;
import com.fabantowapi.joetz_android.contentproviders.UserActivityContentProvider;
import com.fabantowapi.joetz_android.contentproviders.ContributorCampContentProvider;
import com.fabantowapi.joetz_android.contentproviders.UserCampContentProvider;
import com.fabantowapi.joetz_android.contentproviders.UserContentProvider;
import com.fabantowapi.joetz_android.database.ActiviteitTable;
import com.fabantowapi.joetz_android.database.CampTable;
import com.fabantowapi.joetz_android.database.ContributorCampTable;
import com.fabantowapi.joetz_android.database.UserActivityTable;
import com.fabantowapi.joetz_android.database.UserCampTable;
import com.fabantowapi.joetz_android.database.UserTable;
import com.fabantowapi.joetz_android.model.api.Activity;
import com.fabantowapi.joetz_android.model.api.AddUserToCampRequest;
import com.fabantowapi.joetz_android.model.api.Camp;
import com.fabantowapi.joetz_android.model.api.CreateActivityRequest;
import com.fabantowapi.joetz_android.model.api.CreateCampRequest;
import com.fabantowapi.joetz_android.model.api.EditUserRoleRequest;
import com.fabantowapi.joetz_android.model.api.GetActivityResponse;
import com.fabantowapi.joetz_android.model.api.Adres;
import com.fabantowapi.joetz_android.model.api.Contactpersoon;
import com.fabantowapi.joetz_android.model.api.EditAddressRequest;
import com.fabantowapi.joetz_android.model.api.EditContactPersonRequest;
import com.fabantowapi.joetz_android.model.api.EditUserDetailsRequest;
import com.fabantowapi.joetz_android.model.api.EditUserRequest;
import com.fabantowapi.joetz_android.model.api.GetArticleResponse;
import com.fabantowapi.joetz_android.model.api.GetCampResponse;
import com.fabantowapi.joetz_android.model.api.GetUserResponse;
import com.fabantowapi.joetz_android.model.api.LoginRequest;
import com.fabantowapi.joetz_android.model.api.LoginResponse;
import com.fabantowapi.joetz_android.model.api.LogoutRequest;
import com.fabantowapi.joetz_android.model.api.RegisterRequest;
import com.fabantowapi.joetz_android.model.api.User;
import com.fabantowapi.joetz_android.utils.CookieHelper;
import com.fabantowapi.joetz_android.utils.PreferencesHelper;
import com.fabantowapi.joetz_android.utils.SharedHelper;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Date;

import retrofit.RestAdapter;
import retrofit.converter.ConversionException;
import retrofit.converter.GsonConverter;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by Pieter on 26-7-2016.
 */
public class ApiHelper {
    private static JoetzApi service;

    private static JoetzApi getService(Context context){
        if(service == null){

            String domain = "37.139.13.237:8085";

            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint("http://" + domain)
                    .build();

            service = restAdapter.create(JoetzApi.class);
        }

        return service;
    }

    private static void resetService() { service = null; }

    public static Observable<Object> logIn(Context context, String login, String password, boolean permanent){
        ApiHelper.resetService();

        return ApiHelper.getService(context).logIn(new LoginRequest(login, password, permanent))
                .flatMap(response ->
                {
                    String applicationCookie = CookieHelper.getApplicationCookie(response.getHeaders());
                    PreferencesHelper.saveApplicationCookie(context, applicationCookie);

                    LoginResponse loginResponse;

                    try{
                        GsonConverter converter = new GsonConverter(new Gson());
                        loginResponse = (LoginResponse) converter.fromBody(response.getBody(), LoginResponse.class);
                    }catch (ConversionException e){
                        return Observable.error(e);
                    }

                    if(loginResponse != null && response.getStatus() == 200){
                        return Observable.just(loginResponse);
                    }
                    else{
                        int status = response.getStatus();
                        String error;

                        switch(status){
                            case 400 : error = "Bad Request"; break;
                            case 401 : error = "Unauthorized"; break;
                            default : error = "Unknown Error"; break;
                        }

                        return Observable.error(new IOException(error));
                    }
                })
                .doOnNext(loginResponse -> {
                    PreferencesHelper.saveAccessToken(context, loginResponse.getAccessToken());
                    PreferencesHelper.saveRefreshToken(context, loginResponse.getRefreshToken());
                })
                .flatMap(loginResponse -> Observable.empty())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<Object> logOut(Context context, String refreshToken){
        ApiHelper.resetService();

        return ApiHelper.getService(context).logOut(new LogoutRequest(refreshToken))
                .flatMap(response -> {
                    String applicationCookie = CookieHelper.getApplicationCookie(response.getHeaders());
                    PreferencesHelper.saveApplicationCookie(context, applicationCookie);

                    if(response.getStatus() == 200){
                        return Observable.empty();
                    }
                    else{
                        int status = response.getStatus();
                        String error;

                        switch(status){
                            case 400 : error = "Bad Request"; break;
                            case 401 : error = "Unauthorized"; break;
                            default: error = "Unknown Error"; break;
                        }

                        return Observable.error(new IOException(error));
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<Object> register(Context context, String email, String firstname, String lastname, String password, String username){
        ApiHelper.resetService();

        return ApiHelper.getService(context).register(new RegisterRequest(email, firstname, lastname, password, username))
                .flatMap(response ->
                {
                    String applicationCookie = CookieHelper.getApplicationCookie(response.getHeaders());
                    PreferencesHelper.saveApplicationCookie(context, applicationCookie);

                    if(response.getStatus() == 200){
                            return Observable.empty();
                    }
                    else{
                        int status = response.getStatus();
                        String error;

                        switch(status){
                            case 400 : error = "Bad Request"; break;
                            case 401 : error = "Unauthorized"; break;
                            default : error = "Unknown Error"; break;
                        }

                        return Observable.error(new IOException(error));
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<Object> getUser(Context context, String email){
        ApiHelper.resetService();

        return ApiHelper.getService(context).getUser(email)
                .flatMap(response -> {
                    String applicationCookie = CookieHelper.getApplicationCookie(response.getHeaders());
                    PreferencesHelper.saveApplicationCookie(context, applicationCookie);

                    GetUserResponse getUserResponse;

                    try{
                        GsonConverter converter = new GsonConverter(new Gson());
                        getUserResponse = (GetUserResponse) converter.fromBody(response.getBody(), GetUserResponse.class);
                    }catch(ConversionException e){
                        return Observable.error(e);
                    }

                    if(getUserResponse != null && response.getStatus() == 200){
                        return Observable.just(getUserResponse.getUser());
                    }
                    else{
                        int status = response.getStatus();
                        String error;

                        switch(status){
                            case 400 : error = "Bad Request"; break;
                            case 401 : error = "Unauthorized"; break;
                            default : error = "Unknown Error"; break;
                        }

                        return Observable.error(new IOException(error));
                    }
                })
                .doOnNext(user -> {
                    ContentResolver contentResolver = context.getContentResolver();
                    contentResolver.delete(UserContentProvider.CONTENT_URI, null, null);
                    contentResolver.delete(ContactpersoonContentProvider.CONTENT_URI, null, null);

                    ContentValues cvUser = user.getContentValues();
                    contentResolver.insert(UserContentProvider.CONTENT_URI, cvUser);
                })
                .flatMap(user -> Observable.from(user.getContactpersonen()))
                .doOnNext(contactpersoon -> {
                    ContentResolver contentResolver = context.getContentResolver();

                    ContentValues cv = contactpersoon.getContentValues();
                    contentResolver.insert(ContactpersoonContentProvider.CONTENT_URI, cv);
                })
                .toList()
                .flatMap(user -> Observable.empty())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<Object> getUsers(Context context){
        ApiHelper.resetService();

        return ApiHelper.getService(context).getUsers()
                .flatMap(response -> {
                    String applicationCookie = CookieHelper.getApplicationCookie(response.getHeaders());
                    PreferencesHelper.saveApplicationCookie(context, applicationCookie);

                    GetUserResponse[] users;

                    try{
                        GsonConverter converter = new GsonConverter(new Gson());
                        users = (GetUserResponse[]) converter.fromBody(response.getBody(), GetUserResponse[].class);
                    }
                    catch(ConversionException e){
                        return Observable.error(e);
                    }

                    if(users != null && response.getStatus() == 200){
                        return Observable.just(users);
                    }
                    else{
                        int statusId = response.getStatus();
                        String error;

                        switch(statusId){
                            case 400 : error = "Bad Request"; break;
                            case 401 : error = "Unauthorized"; break;
                            default : error = "Unknown Error"; break;
                        }

                        return Observable.error(new IOException(error));
                    }
                })
                .doOnNext(users -> {
                    ContentResolver contentResolver = context.getContentResolver();
                    contentResolver.delete(UserContentProvider.CONTENT_URI, null, null);
                    contentResolver.delete(ContactpersoonContentProvider.CONTENT_URI, null, null);
                })
                .flatMap(users -> Observable.from(users))
                .doOnNext(userResponse -> {
                    User user = userResponse.getUser();
                    ContentResolver contentResolver = context.getContentResolver();
                    ContentValues cvUser = user.getContentValues();
                    contentResolver.insert(UserContentProvider.CONTENT_URI, cvUser);

                    ContentValues cvContactperson1 = user.getContactpersoon1().getContentValues();
                    contentResolver.insert(ContactpersoonContentProvider.CONTENT_URI, cvContactperson1);

                    ContentValues cvContactperson2 = user.getContactpersoon2().getContentValues();
                    contentResolver.insert(ContactpersoonContentProvider.CONTENT_URI, cvContactperson2);
                })
                .toList()
                .flatMap(users -> Observable.empty())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<Object> getActivities(Context context){
        ApiHelper.resetService();

        return ApiHelper.getService(context).getActivities()
                .flatMap(response -> {
                    String applicationCookie = CookieHelper.getApplicationCookie(response.getHeaders());
                    PreferencesHelper.saveApplicationCookie(context, applicationCookie);

                    GetActivityResponse[] activities;

                    try{
                        GsonConverter converter = new GsonConverter(new Gson());
                        activities = (GetActivityResponse[]) converter.fromBody(response.getBody(), GetActivityResponse[].class);
                    }
                    catch(ConversionException e){
                        return Observable.error(e);
                    }

                    if(activities != null && response.getStatus() == 200){
                        return Observable.just(activities);
                    }
                    else{
                        int statusId = response.getStatus();
                        String error;

                        switch(statusId){
                            case 400 : error = "Bad Request"; break;
                            case 401 : error = "Unauthorized"; break;
                            default : error = "Unknown Error"; break;
                        }

                        return Observable.error(new IOException(error));
                    }
                })
                .doOnNext(activityResponses -> {
                    ContentResolver contentResolver = context.getContentResolver();
                    contentResolver.delete(ActivityContentProvider.CONTENT_URI, null, null);
                    contentResolver.delete(UserActivityContentProvider.CONTENT_URI, null, null);
                })
                .flatMap(activityResponses -> Observable.from(activityResponses))
                .doOnNext(activity -> {
                    ContentResolver contentResolver = context.getContentResolver();
                    ContentValues cvActivity = activity.getContentValues();
                    contentResolver.insert(ActivityContentProvider.CONTENT_URI, cvActivity);

                    ContentValues[] cvUserActivities = activity.getUserActivityContentValues();
                    contentResolver.bulkInsert(UserActivityContentProvider.CONTENT_URI, cvUserActivities);
                })
                .toList()
                .flatMap(activityResponses -> Observable.empty())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<Object> getArticles(Context context){
        ApiHelper.resetService();

        return ApiHelper.getService(context).getArticles()
                .flatMap(response -> {
                    String applicationCookie = CookieHelper.getApplicationCookie(response.getHeaders());
                    PreferencesHelper.saveApplicationCookie(context, applicationCookie);

                    GetArticleResponse[] articles;

                    try{
                        GsonConverter converter = new GsonConverter(new Gson());
                        articles = (GetArticleResponse[]) converter.fromBody(response.getBody(), GetArticleResponse[].class);
                    }
                    catch(ConversionException e){
                        return Observable.error(e);
                    }

                    if(articles != null && response.getStatus() == 200){
                        return Observable.just(articles);
                    }
                    else{
                        int statusId = response.getStatus();
                        String error;

                        switch(statusId){
                            case 400 : error = "Bad Request"; break;
                            case 401 : error = "Unauthorized"; break;
                            default : error = "Unknown Error"; break;
                        }

                        return Observable.error(new IOException(error));
                    }
                })
                .doOnNext(articleResponses -> {
                    ContentResolver contentResolver = context.getContentResolver();
                    contentResolver.delete(ArticleContentProvider.CONTENT_URI, null, null);
                })
                .flatMap(articleResponses -> Observable.from(articleResponses))
                .doOnNext(article -> {
                    ContentResolver contentResolver = context.getContentResolver();
                    ContentValues cvArticle = article.getContentValues();
                    contentResolver.insert(ArticleContentProvider.CONTENT_URI, cvArticle);
                })
                .toList()
                .flatMap(articleResponses -> Observable.empty())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public static Observable<Object> getCamps(Context context){
        ApiHelper.resetService();

        return ApiHelper.getService(context).getCamps()
                .flatMap(response -> {
                    String applicationCookie = CookieHelper.getApplicationCookie(response.getHeaders());
                    PreferencesHelper.saveApplicationCookie(context, applicationCookie);

                    GetCampResponse[] camps;

                    try{
                        GsonConverter converter = new GsonConverter(new Gson());
                        camps = (GetCampResponse[]) converter.fromBody(response.getBody(), GetCampResponse[].class);
                    }
                    catch(ConversionException e){
                        return Observable.error(e);
                    }

                    if(camps != null && response.getStatus() == 200){
                        return Observable.just(camps);
                    }
                    else{
                        int statusId = response.getStatus();
                        String error;

                        switch(statusId){
                            case 400 : error = "Bad Request"; break;
                            case 401 : error = "Unauthorized"; break;
                            default : error = "Unknown Error"; break;
                        }

                        return Observable.error(new IOException(error));
                    }
                })
                .doOnNext(campResponses -> {
                    ContentResolver contentResolver = context.getContentResolver();
                    contentResolver.delete(CampContentProvider.CONTENT_URI, null, null);
                    contentResolver.delete(ContributorCampContentProvider.CONTENT_URI, null, null);
                })
                .flatMap(campResponses -> Observable.from(campResponses))
                .doOnNext(camp -> {
                    ContentResolver contentResolver = context.getContentResolver();
                    ContentValues cvCamp = camp.getContentValues();
                    contentResolver.insert(CampContentProvider.CONTENT_URI, cvCamp);

                    ContentValues[] cvContributorCamp = camp.getContributorCampContentValues();
                    contentResolver.bulkInsert(ContributorCampContentProvider.CONTENT_URI, cvContributorCamp);
                })
                .toList()
                .flatMap(campResponses -> Observable.empty())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<Object> editUser(Context context, String email, String firstname, String lastname, String securitynumber, String birthdate, User currentUser){
        ApiHelper.resetService();

        String date = SharedHelper.formatDateForAPI(birthdate);

        return ApiHelper.getService(context).editUser(email, new EditUserRequest(firstname, lastname, securitynumber, date))
                .flatMap(response -> {
                    String applicationCookie = CookieHelper.getApplicationCookie(response.getHeaders());
                    PreferencesHelper.saveApplicationCookie(context, applicationCookie);

                    if(response.getStatus() == 200){
                        currentUser.setFirstname(firstname);
                        currentUser.setLastname(lastname);
                        currentUser.setRijksregisternummer(securitynumber);
                        currentUser.setGeboortedatum(birthdate);

                        return Observable.just(currentUser);
                    }
                    else{
                        int status = response.getStatus();
                        String error;

                        switch(status){
                            case 400 : error = "Bad Request"; break;
                            case 401 : error = "Unauthorized"; break;
                            default : error = "Unknown Error"; break;
                        }

                        return Observable.error(new IOException(error));
                    }
                })
                .doOnNext(user -> {
                    String where = UserTable.TABLE_NAME + "." + UserTable.COLUMN_EMAIL + " = ?";
                    String[] selectionArgs = new String[]{
                            user.getEmail()
                    };

                    ContentResolver contentResolver = context.getContentResolver();
                    ContentValues cvUser = user.getContentValues();

                    contentResolver.update(UserContentProvider.CONTENT_URI, cvUser, where, selectionArgs);
                })
                .flatMap(user -> Observable.empty())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<Object> editAddress(Context context, String email, String naamgebouw, String straat, int huisnummer, String bus, String gemeente, int postcode, User currentUser){
        ApiHelper.resetService();

        return ApiHelper.getService(context).editAddress(email, new EditAddressRequest(postcode, straat, huisnummer, bus, gemeente, naamgebouw))
                .flatMap(response -> {
                    String applicationCookie = CookieHelper.getApplicationCookie(response.getHeaders());
                    PreferencesHelper.saveApplicationCookie(context, applicationCookie);

                    if(response.getStatus() == 200){
                        Adres adres = new Adres(naamgebouw, straat, huisnummer, bus, gemeente, postcode);
                        currentUser.setAdres(adres);

                        return Observable.just(currentUser);
                    }
                    else{
                        int status = response.getStatus();
                        String error;

                        switch(status){
                            case 400 : error = "Bad Request"; break;
                            case 401 : error = "Unauthorized"; break;
                            default : error = "Unknown Error"; break;
                        }

                        return Observable.error(new IOException(error));
                    }
                })
                .doOnNext(user -> {
                    String where = UserTable.TABLE_NAME + "." + UserTable.COLUMN_EMAIL + " = ?";
                    String[] selectionArgs = new String[]{
                            user.getEmail()
                    };

                    ContentResolver contentResolver = context.getContentResolver();
                    ContentValues cvUser = user.getContentValues();

                    contentResolver.update(UserContentProvider.CONTENT_URI, cvUser, where, selectionArgs);
                })
                .flatMap(user -> Observable.empty())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<Object> editContactperson1(Context context, String userEmail, String firstname, String lastname, String email, String telephonenumber, String securitynumber, String joinnumber, boolean paying, boolean parent, Adres address, User currentUser){
        ApiHelper.resetService();

        return ApiHelper.getService(context).editContactperson1(userEmail, new EditContactPersonRequest(firstname, lastname, email, telephonenumber, securitynumber, joinnumber, paying, parent, address.getNaamgebouw(), address.getStraat(), address.getHuisnummer(), address.getBus(), address.getGemeente(), address.getPostcode()))
                .flatMap(response -> {
                    String applicationCookie = CookieHelper.getApplicationCookie(response.getHeaders());
                    PreferencesHelper.saveApplicationCookie(context, applicationCookie);

                    if(response.getStatus() == 200){
                        currentUser.setContactpersoon1(new Contactpersoon(email, firstname, lastname, securitynumber, telephonenumber, joinnumber, paying, parent, address));

                        return Observable.just(currentUser);
                    }
                    else{
                        int status = response.getStatus();
                        String error;

                        switch(status){
                            case 400 : error = "Bad Request"; break;
                            case 401 : error = "Unauthorized"; break;
                            default : error = "Unknown Error"; break;
                        }

                        return Observable.error(new IOException(error));
                    }
                })
                .doOnNext(user -> {
                    String where = UserTable.TABLE_NAME + "." + UserTable.COLUMN_EMAIL + " = ?";
                    String[] selectionArgs = new String[]{
                            user.getEmail()
                    };

                    ContentResolver contentResolver = context.getContentResolver();
                    ContentValues cvUser = user.getContentValues();

                    contentResolver.update(UserContentProvider.CONTENT_URI, cvUser, where, selectionArgs);
                })
                .flatMap(user -> Observable.from(user.getContactpersonen()))
                .doOnNext(contactpersoon -> {
                    ContentResolver contentResolver = context.getContentResolver();

                    if(contactpersoon != null) {
                        ContentValues cv = contactpersoon.getContentValues();
                        contentResolver.insert(ContactpersoonContentProvider.CONTENT_URI, cv);
                    }
                })
                .toList()
                .flatMap(user -> Observable.empty())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<Object> editContactperson2(Context context, String userEmail, String firstname, String lastname, String email, String telephonenumber, String securitynumber, String joinnumber, boolean paying, boolean parent, Adres address, User currentUser){
        ApiHelper.resetService();

        return ApiHelper.getService(context).editContactperson2(userEmail, new EditContactPersonRequest(firstname, lastname, email, telephonenumber, securitynumber, joinnumber, paying, parent, address.getNaamgebouw(), address.getStraat(), address.getHuisnummer(), address.getBus(), address.getGemeente(), address.getPostcode()))
                .flatMap(response -> {
                    String applicationCookie = CookieHelper.getApplicationCookie(response.getHeaders());
                    PreferencesHelper.saveApplicationCookie(context, applicationCookie);

                    if(response.getStatus() == 200){
                        currentUser.setContactpersoon2(new Contactpersoon(email, firstname, lastname, securitynumber, telephonenumber, joinnumber, paying, parent, address));

                        return Observable.just(currentUser);
                    }
                    else{
                        int status = response.getStatus();
                        String error;

                        switch(status){
                            case 400 : error = "Bad Request"; break;
                            case 401 : error = "Unauthorized"; break;
                            default : error = "Unknown Error"; break;
                        }

                        return Observable.error(new IOException(error));
                    }
                })
                .doOnNext(user -> {
                    String where = UserTable.TABLE_NAME + "." + UserTable.COLUMN_EMAIL + " = ?";
                    String[] selectionArgs = new String[]{
                            user.getEmail()
                    };

                    ContentResolver contentResolver = context.getContentResolver();
                    ContentValues cvUser = user.getContentValues();

                    contentResolver.update(UserContentProvider.CONTENT_URI, cvUser, where, selectionArgs);
                })
                .flatMap(user -> Observable.from(user.getContactpersonen()))
                .doOnNext(contactpersoon -> {
                    ContentResolver contentResolver = context.getContentResolver();

                    ContentValues cv = contactpersoon.getContentValues();
                    contentResolver.insert(ContactpersoonContentProvider.CONTENT_URI, cv);
                })
                .toList()
                .flatMap(user -> Observable.empty())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<Object> editUserRole(Context context, String userEmail, String role, User currentUser){
        ApiHelper.resetService();

        return ApiHelper.getService(context).editUserRole(userEmail, new EditUserRoleRequest(role))
                .flatMap(response -> {
                    String applicationCookie = CookieHelper.getApplicationCookie(response.getHeaders());
                    PreferencesHelper.saveApplicationCookie(context, applicationCookie);

                    if(response.getStatus() == 200){
                        currentUser.setRole(role);

                        return Observable.just(currentUser);
                    }
                    else{
                        int status = response.getStatus();
                        String error;

                        switch(status){
                            case 400 : error = "Bad Request"; break;
                            case 401 : error = "Unauthorized"; break;
                            default : error = "Unknown Error"; break;
                        }

                        return Observable.error(new IOException(error));
                    }
                })
                .doOnNext(user -> {
                    String where = UserTable.TABLE_NAME + "." + UserTable.COLUMN_EMAIL + " = ?";
                    String[] selectionArgs = new String[]{
                            user.getEmail()
                    };

                    ContentResolver contentResolver = context.getContentResolver();
                    ContentValues cvUser = user.getContentValues();

                    contentResolver.update(UserContentProvider.CONTENT_URI, cvUser, where, selectionArgs);
                })
                .flatMap(user -> Observable.empty())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<Object> editUserDetails(Context context, String email, String codegerechtigde, User currentUser){
        ApiHelper.resetService();

        return ApiHelper.getService(context).editUserDetails(email, new EditUserDetailsRequest(codegerechtigde))
                .flatMap(response -> {
                    String applicationCookie = CookieHelper.getApplicationCookie(response.getHeaders());
                    PreferencesHelper.saveApplicationCookie(context, applicationCookie);

                    if(response.getStatus() == 200){
                        currentUser.setCodegerechtigde(codegerechtigde);

                        return Observable.just(currentUser);
                    }
                    else {
                        int status = response.getStatus();
                        String error;

                        switch (status) {
                            case 400:
                                error = "Bad Request";
                                break;
                            case 401:
                                error = "Unauthorized";
                                break;
                            default:
                                error = "Unknown Error";
                                break;
                        }

                        return Observable.error(new IOException(error));
                    }
                })
                .doOnNext(user -> {
                    String where = UserTable.TABLE_NAME + "." + UserTable.COLUMN_EMAIL + " = ?";
                    String[] selectionArgs = new String[]{
                            user.getEmail()
                    };

                    ContentResolver contentResolver = context.getContentResolver();
                    ContentValues cvUser = user.getContentValues();

                    contentResolver.update(UserContentProvider.CONTENT_URI, cvUser, where, selectionArgs);
                })
                .flatMap(user -> Observable.empty())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<Object> addUserToActivity(Context context, String activityId, String email, Activity currentActivity, User currentUser){
        ApiHelper.resetService();

        return ApiHelper.getService(context).addUserToActivity(activityId, email)
                .flatMap(response -> {
                    String applicationCookie = CookieHelper.getApplicationCookie(response.getHeaders());
                    PreferencesHelper.saveApplicationCookie(context, applicationCookie);

                    if(response.getStatus() == 200){
                        currentActivity.addAanwezige(currentUser);
                        return Observable.just(currentActivity);
                    }
                    else {
                        int status = response.getStatus();
                        String error;

                        switch (status) {
                            case 400:
                                error = "Bad Request";
                                break;
                            case 401:
                                error = "Unauthorized";
                                break;
                            default:
                                error = "Unknown Error";
                                break;
                        }

                        return Observable.error(new IOException(error));
                    }
                })
                .doOnNext(activity -> {
                    String where = ActiviteitTable.TABLE_NAME + "." + ActiviteitTable.COLUMN_ID + " = ?";
                    String[] selectionArgs = new String[]{
                            activity.getId()
                    };

                    ContentResolver contentResolver = context.getContentResolver();
                    ContentValues cvActivity = activity.getContentValues();

                    contentResolver.update(ActivityContentProvider.CONTENT_URI, cvActivity, where, selectionArgs);

                    ContentValues cvUserActivity = new ContentValues();
                    cvUserActivity.put(UserActivityTable.COLUMN_ACTIVITY_ID, activityId);
                    cvUserActivity.put(UserActivityTable.COLUMN_USER_ID, currentUser.getId());

                    contentResolver.insert(UserActivityContentProvider.CONTENT_URI, cvUserActivity);
                })
                .flatMap(activity -> Observable.empty())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<Object> addContributorToCamp(Context context, String campId, String email, Camp currentCamp, User currentUser){
        ApiHelper.resetService();

        return ApiHelper.getService(context).addContributorToCamp(campId, email)
                .flatMap(response -> {
                    String applicationCookie = CookieHelper.getApplicationCookie(response.getHeaders());
                    PreferencesHelper.saveApplicationCookie(context, applicationCookie);

                    if(response.getStatus() == 200){
                        currentCamp.addMedewerker(currentUser);
                        return Observable.just(currentCamp);
                    }
                    else {
                        int status = response.getStatus();
                        String error;

                        switch (status) {
                            case 400:
                                error = "Bad Request";
                                break;
                            case 401:
                                error = "Unauthorized";
                                break;
                            default:
                                error = "Unknown Error";
                                break;
                        }

                        return Observable.error(new IOException(error));
                    }
                })
                .doOnNext(camp -> {
                    String where = CampTable.TABLE_NAME + "." + CampTable.COLUMN_ID + " = ?";
                    String[] selectionArgs = new String[]{
                            camp.getId()
                    };

                    ContentResolver contentResolver = context.getContentResolver();
                    ContentValues cvCamp = camp.getContentValues();

                    contentResolver.update(CampContentProvider.CONTENT_URI, cvCamp, where, selectionArgs);

                    ContentValues cvContributorCamp = new ContentValues();
                    cvContributorCamp.put(ContributorCampTable.COLUMN_CAMP_ID, campId);
                    cvContributorCamp.put(ContributorCampTable.COLUMN_CONTRIBUTOR_ID, currentUser.getId());

                    contentResolver.insert(ContributorCampContentProvider.CONTENT_URI, cvContributorCamp);
                })
                .flatMap(camp -> Observable.empty())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<Object> addUserToCamp(Context context, String campId, String userId, String extraInfo, Camp currentCamp, User currentUser){
        ApiHelper.resetService();

        return ApiHelper.getService(context).addUserToCamp(new AddUserToCampRequest(extraInfo, false, false, userId, campId))
                .flatMap(response -> {
                    String applicationCookie = CookieHelper.getApplicationCookie(response.getHeaders());
                    PreferencesHelper.saveApplicationCookie(context, applicationCookie);

                    if(response.getStatus() == 200){
                        currentCamp.addAanwezige(currentUser);
                        return Observable.just(currentCamp);
                    }
                    else {
                        int status = response.getStatus();
                        String error;

                        switch (status) {
                            case 400:
                                error = "Bad Request";
                                break;
                            case 401:
                                error = "Unauthorized";
                                break;
                            default:
                                error = "Unknown Error";
                                break;
                        }

                        return Observable.error(new IOException(error));
                    }
                })
                .doOnNext(camp -> {
                    String where = CampTable.TABLE_NAME + "." + CampTable.COLUMN_ID + " = ?";
                    String[] selectionArgs = new String[]{
                            camp.getId()
                    };

                    ContentResolver contentResolver = context.getContentResolver();
                    ContentValues cvCamp = camp.getContentValues();

                    contentResolver.update(CampContentProvider.CONTENT_URI, cvCamp, where, selectionArgs);

                    ContentValues cvUserCamp = new ContentValues();
                    cvUserCamp.put(UserCampTable.COLUMN_CAMP_ID, campId);
                    cvUserCamp.put(UserCampTable.COLUMN_USER_ID, currentUser.getId());

                    contentResolver.insert(UserCampContentProvider.CONTENT_URI, cvUserCamp);
                })
                .flatMap(camp -> Observable.empty())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<Object> createActivity(Context context, String name, Date date, String location, boolean entireDay, Date begin, Date end){
        ApiHelper.resetService();

        String dateString = SharedHelper.parseDateToDateString(date);
        String beginString = SharedHelper.parseDateToDateString(begin);
        String endString = SharedHelper.parseDateToDateString(end);

        return ApiHelper.getService(context).createActivity(new CreateActivityRequest(name, dateString, location, entireDay, beginString, endString))
                .flatMap(response -> {
                    String applicationCookie = CookieHelper.getApplicationCookie(response.getHeaders());
                    PreferencesHelper.saveApplicationCookie(context, applicationCookie);

                    String newActivityId;

                    try{
                        GsonConverter converter = new GsonConverter(new Gson());
                        newActivityId = (String) converter.fromBody(response.getBody(), String.class);
                    }
                    catch(ConversionException e){
                        return Observable.error(e);
                    }

                    if(response.getStatus() == 200){
                        return Observable.just(newActivityId);
                    }
                    else {
                        int status = response.getStatus();
                        String error;

                        switch (status) {
                            case 400:
                                error = "Bad Request";
                                break;
                            case 401:
                                error = "Unauthorized";
                                break;
                            default:
                                error = "Unknown Error";
                                break;
                        }

                        return Observable.error(new IOException(error));
                    }
                })
                .doOnNext(newActivityId -> {
                    Activity activity = new Activity(newActivityId, name, dateString, location, entireDay, beginString, endString, null);
                    ContentResolver contentResolver = context.getContentResolver();
                    ContentValues cvActivity = activity.getContentValues();

                    contentResolver.insert(ActivityContentProvider.CONTENT_URI, cvActivity);
                })
                .flatMap(response -> Observable.empty())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<Object> createCamp(Context context, String name, String description, Date begin, Date end, int amountOfDays, int amountOfNights, String transport, int price, int maxAge, int minAge, int maxParticipants, String contact, String photo, String buildingName, String street, int number, String extraField, String city, int postalCode){
        ApiHelper.resetService();

        String beginString = SharedHelper.parseDateToDateString(begin);
        String endString = SharedHelper.parseDateToDateString(end);

        return ApiHelper.getService(context).createCamp(new CreateCampRequest(name, description, beginString, endString, amountOfDays, amountOfNights, transport, price, maxAge, minAge, maxParticipants, contact, photo, buildingName, street, number, extraField, city, postalCode))
                .flatMap(response -> {
                    String applicationCookie = CookieHelper.getApplicationCookie(response.getHeaders());
                    PreferencesHelper.saveApplicationCookie(context, applicationCookie);

                    String newCampId;

                    try{
                        GsonConverter converter = new GsonConverter(new Gson());
                        newCampId = (String) converter.fromBody(response.getBody(), String.class);
                    }
                    catch(ConversionException e){
                        return Observable.error(e);
                    }

                    if(response.getStatus() == 200){
                        return Observable.just(newCampId);
                    }
                    else {
                        int status = response.getStatus();
                        String error;

                        switch (status) {
                            case 400:
                                error = "Bad Request";
                                break;
                            case 401:
                                error = "Unauthorized";
                                break;
                            default:
                                error = "Unknown Error";
                                break;
                        }

                        return Observable.error(new IOException(error));
                    }
                })
                .doOnNext(newCampId -> {
                    Adres adres = new Adres(buildingName, street, number, extraField, city, postalCode);
                    Camp camp = new Camp(newCampId, name, description, beginString, endString, amountOfDays, amountOfNights, transport, price, maxAge, minAge, maxParticipants, contact, photo, null, null, null, adres);

                    ContentResolver contentResolver = context.getContentResolver();
                    ContentValues cvCamp = camp.getContentValues();

                    contentResolver.insert(CampContentProvider.CONTENT_URI, cvCamp);
                })
                .flatMap(response -> Observable.empty())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
