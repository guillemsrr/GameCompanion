
package bernatriu.gotcompanion.Fragments


import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import bernatriu.gotcompanion.R
import bernatriu.gotcompanion.adapters.CharacterAdapter
import bernatriu.gotcompanion.models.GOTCharacter
import bernatriu.gotcompanion.models.GOTCharacterSearch
import bernatriu.gotcompanion.network.ApiService
import kotlinx.android.synthetic.main.fragment_characters.*
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

    var list: ArrayList<GOTCharacter> = arrayListOf()
    var viewList: ArrayList<GOTCharacter> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.w("MainActivity","preparing to get characters")
        //getApiData()
        getApiData() // get and draw





    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_characters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        findButton.setOnClickListener{

            if(findText.text.toString().isEmpty()){
                Log.w("MainActivity","input empty")
                viewList = list
            }
            else{
                viewList = selectCharactersbyName(list,findText.text.toString())


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

    private fun getApiData(){
        ApiService.service.getCharacters().enqueue(object : Callback<ArrayList<GOTCharacter>> {

            override fun onResponse(call: Call<ArrayList<GOTCharacter>>, response: Response<ArrayList<GOTCharacter>>) {
                Log.w("MainActivity","characters fetched")
                response.body()?.let {characters ->
                    // Iterate Streams
                    for(character in characters){
                        if(character.image != null){
                            Log.e("MainActivity","Character with name ${character.name}, father ${character.father}, house ${character.house}")
                            list.add(character)
                        }
                    }
                    viewList = list
                    drawRecyclerView()


                } ?: kotlin.run{
                    //ERROR
                    Log.w("MainActivity","characters error on fetching - fetched nothing?")
                }

            }

            override fun onFailure(call: Call<ArrayList<GOTCharacter>>, t: Throwable) {

                Log.e("MainActivity", "Error getting characters")
                Log.e("MainActivity",t.message)
            }

        })
    }

    fun selectCharactersbyName(originalList : ArrayList<GOTCharacter>, search: String) : ArrayList<GOTCharacter>{

        var selectedItems : ArrayList<GOTCharacter> = arrayListOf()

        for (character in originalList){
            character.name?.let { nameS ->
                if(nameS.contains(search)){
                    selectedItems.add(character)
                }

            }

        }
        return selectedItems
    }

    fun drawRecyclerView(){

        activity?.let{

            Log.w("MainActivity","Loaded RecyclerView")
            characters_list.adapter = CharacterAdapter(viewList)
            characters_list.layoutManager = LinearLayoutManager(activity)

        }
    }



}
