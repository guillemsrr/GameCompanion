package bernatriu.gotcompanion.network

import bernatriu.gotcompanion.models.GOTCharacter
import bernatriu.gotcompanion.models.GotCharacterResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService{

    @Headers("Client-ID: ry9mcpqp1kgx1yxi9z2zm42bedsso3")
    @GET("characters")
    fun getCharacters() : Call<GotCharacterResponse>

    companion object {
        private var retrofit = Retrofit.Builder()
            .baseUrl("https://api.got.show/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create<ApiService>(ApiService::class.java)
    }


}