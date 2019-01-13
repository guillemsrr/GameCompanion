package bernatriu.gotcompanion.network

import bernatriu.gotcompanion.Models.GOTEpisode
import bernatriu.gotcompanion.models.GOTCharacter
import bernatriu.gotcompanion.models.GOTCharacterSearch
import bernatriu.gotcompanion.models.GOTHouse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService{

    //GET ALL CHARACTERS
    @GET("characters")
    fun getCharacters() : Call<ArrayList<GOTCharacter>>

    //GET ALL HOUSES
    @GET("houses")
    fun getHouses() : Call<ArrayList<GOTHouse>>

    //GET ALL EPISODES
    @GET("episodes")
    fun getEpisodes(): Call<ArrayList<GOTEpisode>>

    // GET CHARACTER BY NAME
    @GET ("characters/{name}")
    fun getCharacterByName(@Path("name") name: String ) : Call<GOTCharacterSearch>

    companion object {
        private var retrofit = Retrofit.Builder()
            .baseUrl("https://api.got.show/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create<ApiService>(ApiService::class.java)

    }


}