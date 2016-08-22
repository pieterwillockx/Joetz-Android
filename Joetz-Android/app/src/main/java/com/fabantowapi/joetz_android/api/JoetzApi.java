package com.fabantowapi.joetz_android.api;

import retrofit.http.GET;
import retrofit.http.PUT;
import retrofit.http.Path;
import rx.Observable;

import com.fabantowapi.joetz_android.model.api.AddUserToCampRequest;
import com.fabantowapi.joetz_android.model.api.CreateActivityRequest;
import com.fabantowapi.joetz_android.model.api.CreateCampRequest;
import com.fabantowapi.joetz_android.model.api.EditAddressRequest;
import com.fabantowapi.joetz_android.model.api.EditContactPersonRequest;
import com.fabantowapi.joetz_android.model.api.EditUserDetailsRequest;
import com.fabantowapi.joetz_android.model.api.EditUserRequest;
import com.fabantowapi.joetz_android.model.api.EditUserRoleRequest;
import com.fabantowapi.joetz_android.model.api.LoginRequest;
import com.fabantowapi.joetz_android.model.api.LogoutRequest;
import com.fabantowapi.joetz_android.model.api.RegisterRequest;

import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by Pieter on 26-7-2016.
 */
public interface JoetzApi {
    @POST("/authentication/login")
    Observable<Response> logIn(@Body LoginRequest request);

    @POST("/authentication/logout")
    Observable<Response> logOut(@Body LogoutRequest request);

    @POST("/user")
    Observable<Response> register(@Body RegisterRequest request);

    @GET("/user/{email}")
    Observable<Response> getUser(@Path("email") String email);

    @GET("/activiteit")
    Observable<Response> getActivities();

    @GET("/user")
    Observable<Response> getUsers();

    @GET("/kamp")
    Observable<Response> getCamps();

    @GET("/artikel")
    Observable<Response> getArticles();

    @PUT("/user/{email}/profile")
    Observable<Response> editUser(@Path("email") String email, @Body EditUserRequest request);

    @PUT("/user/{email}/adress")
    Observable<Response> editAddress(@Path("email") String email, @Body EditAddressRequest request);

    @PUT("/user/{email}/contactpersoon1")
    Observable<Response> editContactperson1(@Path("email") String email, @Body EditContactPersonRequest request);

    @PUT("/user/{email}/contactpersoon2")
    Observable<Response> editContactperson2(@Path("email") String email, @Body EditContactPersonRequest request);

    @PUT("/user/{email}/details")
    Observable<Response> editUserDetails(@Path("email") String email, @Body EditUserDetailsRequest request);

    @POST("/activiteit/{activityId}/aanwezigen/{email}")
    Observable<Response> addUserToActivity(@Path("activityId") String activityId, @Path("email") String email);

    @POST("/kamp/{campId}/medewerkers/{email}")
    Observable<Response> addContributorToCamp(@Path("campId") String campId, @Path("email") String email);

    @POST("/inschrijving")
    Observable<Response> addUserToCamp(@Body AddUserToCampRequest request);

    @PUT("/user/{email}/role")
    Observable<Response> editUserRole(@Path("email") String email, @Body EditUserRoleRequest request);

    @POST("/activiteit")
    Observable<Response> createActivity(@Body CreateActivityRequest request);

    @POST("/kamp")
    Observable<Response> createCamp(@Body CreateCampRequest request);
}
