package com.guillemserralorenz.twitchcompanion.api

import com.google.gson.annotations.SerializedName

class OAuthToken {

    @SerializedName("access_token")
    val accessToken: String? = null

    @SerializedName("token_type")
    val tokenType: String? = null

    val authorization: String
        get() = "$tokenType $accessToken"
}