/*
package bernatriu.gotcompanion.activities

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bernatriu.gotcompanion.R
import bernatriu.gotcompanion.models.GOTCharacter
import bernatriu.gotcompanion.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SpecificCharacterActivity {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.w("CharacterFragment","preparing to get specific characters")
        getApiData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_characters, container, false)
    }

    fun getApiData(){
        ApiService.service.getCharacters().enqueue(object : Callback<ArrayList<GOTCharacter>> {

            override fun onResponse(call: Call<ArrayList<GOTCharacter>>, response: Response<ArrayList<GOTCharacter>>) {
                Log.w("MainActivity","characters fetched")
                val list = ArrayList<GOTCharacter>()
                response.body()?.let {characters ->
                    // Iterate Streams
                    for(character in characters){
                        Log.e("MainActivity","Character with name ${character.name}, father ${character.father}, house ${character.house}")
                        list.add(character)
                    }
                } ?: kotlin.run{
                    //ERROR
                    Log.w("MainActivity","characters error on fetching - fetched nothing?")
                }
//                activity?.let{
//                    characters_list.adapter = CharacterAdapter(list)
//                    characters_list.layoutManager = LinearLayoutManager(activity)
//                }
            }

            override fun onFailure(call: Call<ArrayList<GOTCharacter>>, t: Throwable) {

                Log.e("MainActivity", "Error getting characters")
                Log.e("MainActivity",t.message)
            }

        })
    }
}*/
