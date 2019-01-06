package com.guillemserralorenz.twitchcompanion.api

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TwitterApi {

    @FormUrlEncoded
    @POST("oauth2/token")
    fun postCredentials(@Field("grant_type") grantType: String): Call<OAuthToken>

    @GET("/1.1/users/show.json")
    fun getUserDetails(@Query("screen_name") name: String): Call<UserDetails>

    companion object {

        val BASE_URL = "https://api.twitter.com/"
    }
}