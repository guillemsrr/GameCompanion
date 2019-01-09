
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
import bernatriu.gotcompanion.models.GotCharacterResponse
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
        ApiService.service.getStreams().enqueue(object : Callback<GotCharacterResponse> {


            override fun onResponse(call: Call<GotCharacterResponse>, response: Response<GotCharacterResponse>) {
                Log.w("MainActivity","characters fetched")

                response.body()?.data?.let {streams ->

                    // Iterate Streams
                    for(stream in streams){
                        Log.e("MainActivity","Character with name ${stream.characterName} and actor ${stream.actor}")
                    }

                } ?: kotlin.run{
                    //ERROR
                    Log.w("MainActivity","characters error on fetching")

                }
            }

            override fun onFailure(call: Call<GotCharacterResponse>, t: Throwable) {

                Log.e("MainActivity", "Error getting streams")
            }

        })
    }

}
