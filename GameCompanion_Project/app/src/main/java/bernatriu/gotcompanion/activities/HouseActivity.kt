package bernatriu.gotcompanion.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import bernatriu.gotcompanion.R
import bernatriu.gotcompanion.models.GOTHouseSearch
import bernatriu.gotcompanion.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HouseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_house)


        var house_name = intent.getStringExtra("house")

        Log.w("MainActivity","preparing to open up specific house: ${house_name}")

        getHouse(house_name)
    }


    fun getHouse(name : String){

        ApiService.service.getHouseByName(name).enqueue(object : Callback<GOTHouseSearch> {


            override fun onResponse(call: Call<GOTHouseSearch>, response: Response<GOTHouseSearch>) {
                Log.w("MainActivity","house by name fetched")


                response.body()?.data?.let {house ->


                    Log.e("MainActivity","House with name ${house.houseName}, Lord ${house.lord}, words ${house.words}")


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




}
