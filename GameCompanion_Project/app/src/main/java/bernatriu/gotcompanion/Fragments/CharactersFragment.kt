
package bernatriu.gotcompanion.Fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import bernatriu.gotcompanion.R
import bernatriu.gotcompanion.models.GOTCharacter
import bernatriu.gotcompanion.models.GOTCharacterSearch
import bernatriu.gotcompanion.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [CharactersFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [CharactersFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class CharactersFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.w("MainActivity","preparing to get characters")
        //getApiData()
        getCharacter("Hodor")
        getCharacter("Jon Snow")
        getCharacter("petrucas")


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

                response.body()?.let {characters ->

                    // Iterate Streams
                    for(character in characters){
                        Log.e("MainActivity","Character with name ${character.characterName}, father ${character.father}, house ${character.house}")
                    }

                } ?: kotlin.run{
                    //ERROR
                    Log.w("MainActivity","characters error on fetching - fetched nothing?")

                }
            }

            override fun onFailure(call: Call<ArrayList<GOTCharacter>>, t: Throwable) {

                Log.e("MainActivity", "Error getting chaaracters")
                Log.e("MainActivity",t.message)
            }

        })
    }

    fun getCharacter(name : String){

        ApiService.service.getCharacterByName(name).enqueue(object : Callback<GOTCharacterSearch> {


            override fun onResponse(call: Call<GOTCharacterSearch>, response: Response<GOTCharacterSearch>) {
                Log.w("MainActivity","character by name fetched")


                response.body()?.data?.let {character ->


                    Log.e("MainActivity","Character with name ${character.characterName}, father ${character.father}, house ${character.house}")




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
