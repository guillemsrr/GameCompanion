package bernatriu.gotcompanion.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import bernatriu.gotcompanion.R
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)

            characterName.text = intent.getStringExtra("character")
            father.text = intent.getStringExtra("father")
            if(intent.getStringExtra("father") == null)
                father.text = "unknown"

            mother.text = intent.getStringExtra("mother")
            if(intent.getStringExtra("mother") == null)
                mother.text = "unknown"

            house.text = intent.getStringExtra("house")
            if(intent.getStringExtra("house") == null)
                house.text = "unknown"

            culture.text = intent.getStringExtra("culture")
            if(intent.getStringExtra("culture") == null)
                culture.text = "unknown"

            message.text = intent.getStringExtra("message")
            if(intent.getStringExtra("message") == null)
                message.text = "unknown"


        if(intent.getStringExtra("image")!=null){
            Glide
                .with(character_image.context)
                //.load(character.image)
                .load("https://api.got.show/" + intent.getStringExtra("image"))
                .apply(
                    RequestOptions()
                        .transforms(CenterCrop())
                        .placeholder(R.drawable.default_character_image)
                )
                .into(character_image)
        }
            //Log.e("CharacterFragment", "there is no bundle!")
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
