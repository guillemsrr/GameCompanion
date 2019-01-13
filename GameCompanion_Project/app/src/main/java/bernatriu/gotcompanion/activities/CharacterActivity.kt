package bernatriu.gotcompanion.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import bernatriu.gotcompanion.R
import bernatriu.gotcompanion.models.GOTCharacterSearch
import bernatriu.gotcompanion.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)

            var character_name = intent.getStringExtra("character")

            Log.w("MainActivity","preparing to get specific characters: ${character_name}")

            getCharacter(character_name)




    }

    fun getCharacter(name : String){

        ApiService.service.getCharacterByName(name).enqueue(object : Callback<GOTCharacterSearch> {


            override fun onResponse(call: Call<GOTCharacterSearch>, response: Response<GOTCharacterSearch>) {
                Log.w("MainActivity","character by name fetched")


                response.body()?.data?.let {character ->


                    Log.e("MainActivity","Character with name ${character.name}, father ${character.father}, house ${character.house}")




                } ?: kotlin.run{
                    //ERROR
                    Log.w("MainActivity","characters error on fetching by name- fetched nothing?")

                }
            }

            override fun onFailure(call: Call<GOTCharacterSearch>, t: Throwable) {

                Log.e("MainActivity", "Error getting character by name")
                Log.e("MainActivity",t.message)

            }

        })

    }


}
