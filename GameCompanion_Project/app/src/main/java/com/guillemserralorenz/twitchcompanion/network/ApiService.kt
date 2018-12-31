package com.guillemserralorenz.twitchcompanion.network

import com.guillemserralorenz.twitchcompanion.models.TWStream
import com.guillemserralorenz.twitchcompanion.models.TWStreamResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService{//mètode virtual, .h...

    @Headers("Client-ID: ywvglt0gib8rqdly0ejobehqfi071m")
    @GET("streams") // de https://api.twitch.tv/helix/streams
    fun getStreams(): Call<TWStreamResponse>

    companion object {//kotlin. tot el que vagi dins, és static
        private var retrofit = Retrofit.Builder()//fetch
            .baseUrl("https://api.twitch.tv/helix/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create<ApiService>(ApiService::class.java)
    }

}