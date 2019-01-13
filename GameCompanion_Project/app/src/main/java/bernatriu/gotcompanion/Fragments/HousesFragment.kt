package bernatriu.gotcompanion.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import bernatriu.gotcompanion.R
import bernatriu.gotcompanion.adapters.HouseAdapter
import bernatriu.gotcompanion.models.GOTHouse
import bernatriu.gotcompanion.network.ApiService
import kotlinx.android.synthetic.main.fragment_characters.*
import kotlinx.android.synthetic.main.fragment_houses.*
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
class HousesFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getApiData()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_houses, container, false)
    }

    fun getApiData(){
        ApiService.service.getHouses().enqueue(object : Callback<ArrayList<GOTHouse>> {
            override fun onResponse(call: Call<ArrayList<GOTHouse>>, response: Response<ArrayList<GOTHouse>>) {
                Log.w("MainActivity","characters fetched")
                val list = ArrayList<GOTHouse>()
                response.body()?.let {houses ->

                    // Iterate Streams
                    for(house in houses){
                        Log.e("MainActivity","House with name ${house.houseName}, Lord ${house.lord}, words ${house.words}")
                        if(house.image != null)
                            list.add(house)
                    }

                } ?: kotlin.run{
                    //ERROR
                    Log.w("MainActivity","houses error on fetching - fetched nothing?")
                }
                activity?.let{
                    houses_list.adapter = HouseAdapter(list,this@HousesFragment)
                    houses_list.layoutManager = LinearLayoutManager(activity)
                }
            }

            override fun onFailure(call: Call<ArrayList<GOTHouse>>, t: Throwable) {

                Log.e("MainActivity", "Error getting houses")
                Log.e("MainActivity",t.message)
            }

        })
    }

}