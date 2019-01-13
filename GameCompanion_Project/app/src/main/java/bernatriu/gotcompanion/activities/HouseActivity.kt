package bernatriu.gotcompanion.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import bernatriu.gotcompanion.R
import bernatriu.gotcompanion.models.GOTHouse
import bernatriu.gotcompanion.models.GOTHouseSearch
import bernatriu.gotcompanion.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_house.*


class HouseActivity : AppCompatActivity() {

    var House: GOTHouse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_house)

        getHouse(intent.getStringExtra("house"))
    }


    fun getHouse(name : String){

        ApiService.service.getHouseByName(name).enqueue(object : Callback<GOTHouseSearch> {


            override fun onResponse(call: Call<GOTHouseSearch>, response: Response<GOTHouseSearch>) {
                Log.w("MainActivity","house by name fetched")


                response.body()?.data?.let {house ->


                    Log.e("MainActivity","House with name ${house.houseName}, Lord ${house.lord}, words ${house.words}")
                    House = house
                    drawCharacteristics()


                } ?: kotlin.run{
                    //ERROR
                    Log.w("MainActivity","house error on fetching by name- fetched nothing?")

                }
            }

            override fun onFailure(call: Call<GOTHouseSearch>, t: Throwable) {

                Log.e("MainActivity", "Error getting house by name")
                Log.e("MainActivity",t.message)
            }
        })
    }

    fun drawCharacteristics(){

        houseName.text = House?.houseName
        if(House?.houseName == null)
            houseName.text = "unknown"
        lord.text = House?.lord
        if(House?.lord == null)
            lord.text = "unknown"
        words.text = House?.words
        if(House?.words == null)
            words.text = "unknown"
        region.text = House?.region
        if( House?.region == null)
            region.text = "unknown"
        overlord.text = House?.overlord
        if(House?.overlord == null)
            overlord.text = "unknown"
        coat_arms.text = House?.coatOfArms
        if(House?.coatOfArms == null)
            coat_arms.text = "unknown"

        if(House?.image != null){
            Glide
                .with(house_image.context)
                //.load(character.image)
                .load("https://api.got.show/" + House?.image)
                .apply(
                    RequestOptions()
                        .transforms(CenterCrop())
                        .placeholder(R.drawable.default_character_image)
                )
                .into(house_image)
            Log.w("MainActivity","House image link: ${House?.image}")
        }
    }
}
