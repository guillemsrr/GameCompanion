package bernatriu.gotcompanion.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bernatriu.gotcompanion.Models.GOTEpisode

import bernatriu.gotcompanion.R
import bernatriu.gotcompanion.adapters.EpisodeAdapter
import bernatriu.gotcompanion.network.ApiService
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
 * [placesFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [placesFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class EpisodesFragment : Fragment() {


    var list: ArrayList<GOTEpisode> = arrayListOf()
    var viewList: ArrayList<GOTEpisode> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getApiData()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_episodes, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        findButton.setOnClickListener{

            if(findText.text.toString().isEmpty()){
                Log.w("MainActivity","input empty")
                viewList = list
            }
            else{
                viewList = selectEpisodesbyName(list,findText.text.toString())


                if(viewList.isEmpty()){
                    emptyWarn.visibility = View.VISIBLE
                }
                else{
                    emptyWarn.visibility = View.GONE
                }
            }
            drawRecyclerView()
        }


    }

    fun getApiData(){
        ApiService.service.getEpisodes().enqueue(object : Callback<ArrayList<GOTEpisode>> {


            override fun onResponse(call: Call<ArrayList<GOTEpisode>>, response: Response<ArrayList<GOTEpisode>>) {
                Log.w("MainActivity","characters fetched")

                response.body()?.let {episodes ->

                    // Iterate Streams
                    for(episode in episodes){
                        Log.e("MainActivity","Episode with name ${episode.episodeName}, Number ${episode.season}-${episode.number}, characters ${episode.characters}")
                        list.add(episode)
                    }
                    viewList = list
                    drawRecyclerView()

                } ?: kotlin.run{
                    //ERROR
                    Log.w("MainActivity","houses error on fetching - fetched nothing?")

                }
            }

            override fun onFailure(call: Call<ArrayList<GOTEpisode>>, t: Throwable) {

                Log.e("MainActivity", "Error getting houses")
                Log.e("MainActivity",t.message)
            }

        })
    }
    fun selectEpisodesbyName(originalList : ArrayList<GOTEpisode>, search: String) : ArrayList<GOTEpisode>{

        var selectedItems : ArrayList<GOTEpisode> = arrayListOf()

        for (episode in originalList){
            episode.episodeName?.let { nameS ->
                if(nameS.contains(search)){
                    selectedItems.add(episode)
                }

            }

        }
        return selectedItems
    }
    fun drawRecyclerView(){

        activity?.let{

            Log.w("MainActivity","Loaded RecyclerView")
            episodes_list.adapter = EpisodeAdapter(viewList)
            characters_list.layoutManager = LinearLayoutManager(activity)

        }
    }




}
