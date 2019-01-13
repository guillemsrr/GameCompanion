package bernatriu.gotcompanion.activities

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import bernatriu.gotcompanion.R
import bernatriu.gotcompanion.models.GOTCharacter
import bernatriu.gotcompanion.models.GOTCharacterSearch
import bernatriu.gotcompanion.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_character.*
import kotlinx.android.synthetic.main.simple_character.view.*

class CharacterActivity : AppCompatActivity() {

    var Character: GOTCharacter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)

        getCharacter(intent.getStringExtra("character"))
            //Log.e("CharacterFragment", "there is no bundle!")

        father.setOnClickListener {

            Character?.father?.let {fath ->
                val characterIntent = Intent(this@CharacterActivity, CharacterActivity::class.java)
                characterIntent.putExtra("character", fath)

                startActivity(characterIntent)
            }
        }
        mother.setOnClickListener {

            Character?.mother?.let {mother ->
                val characterIntent = Intent(this@CharacterActivity, CharacterActivity::class.java)
                characterIntent.putExtra("character", mother)

                startActivity(characterIntent)
            }
        }
        house.setOnClickListener {


            Character?.house?.let {hou ->
                val houseIntent = Intent(this@CharacterActivity, HouseActivity::class.java)
                houseIntent.putExtra("house", hou)

                startActivity(houseIntent)
            }
        }
    }



    fun getCharacter(name : String){

        ApiService.service.getCharacterByName(name).enqueue(object : Callback<GOTCharacterSearch> {


            override fun onResponse(call: Call<GOTCharacterSearch>, response: Response<GOTCharacterSearch>) {
                Log.w("MainActivity","character by name fetched")


                response.body()?.data?.let {character ->


                    Log.e("MainActivity","Character with name ${character.name}, father ${character.father}, house ${character.house}")
                    Character = character
                    drawCharacteristics()


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

    fun drawCharacteristics(){


        characterName.text = Character?.name
        father.text = Character?.father
        if(Character?.father == null)
            father.text = "unknown"

        mother.text = Character?.mother
        if(Character?.mother == null)
            mother.text = "unknown"

        house.text = Character?.house
        if(Character?.house == null)
            house.text = "unknown"

        culture.text = Character?.culture
        if(Character?.culture == null)
            culture.text = "unknown"

        Log.w("MainActivity","DRawn characteristics")

        if(Character?.image != null){
            Glide
                .with(character_image.context)
                //.load(character.image)
                .load("https://api.got.show/" + Character?.image)
                .apply(
                    RequestOptions()
                        .transforms(CenterCrop())
                        .placeholder(R.drawable.default_character_image)
                )
                .into(character_image)
        }
    }



}
