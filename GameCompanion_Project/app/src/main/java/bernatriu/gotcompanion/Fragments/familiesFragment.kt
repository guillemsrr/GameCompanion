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
import bernatriu.gotcompanion.models.GOTHouse
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
 * [familiesFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [familiesFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class familiesFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getApiData()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_families, container, false)
    }

    fun getApiData(){
        ApiService.service.getHouses().enqueue(object : Callback<ArrayList<GOTHouse>> {


            override fun onResponse(call: Call<ArrayList<GOTHouse>>, response: Response<ArrayList<GOTHouse>>) {
                Log.w("MainActivity","characters fetched")

                response.body()?.let {houses ->

                    // Iterate Streams
                    for(house in houses){
                        Log.e("MainActivity","House with name ${house.houseName}, Lord ${house.lord}, words ${house.words}")
                    }

                } ?: kotlin.run{
                    //ERROR
                    Log.w("MainActivity","houses error on fetching - fetched nothing?")

                }
            }

            override fun onFailure(call: Call<ArrayList<GOTHouse>>, t: Throwable) {

                Log.e("MainActivity", "Error getting houses")
                Log.e("MainActivity",t.message)
            }

        })
    }

}