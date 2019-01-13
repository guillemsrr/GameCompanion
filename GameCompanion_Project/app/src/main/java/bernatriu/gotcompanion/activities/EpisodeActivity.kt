package bernatriu.gotcompanion.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import bernatriu.gotcompanion.Models.GOTEpisode
import bernatriu.gotcompanion.Models.GOTEpisodeSearch
import bernatriu.gotcompanion.R
import bernatriu.gotcompanion.network.ApiService
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_episode.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EpisodeActivity : AppCompatActivity() {

    var Episode: GOTEpisode? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_episode)

       getEpisode(intent.getStringExtra("episode"))

        nextEpisode.setOnClickListener {

            Episode?.nextEpisode?.let { nextE ->
                val characterIntent = Intent(this@EpisodeActivity, EpisodeActivity::class.java)
                characterIntent.putExtra("episode", nextE)

                startActivity(characterIntent)
            }
        }
    }

    fun getEpisode(name : String) {

        ApiService.service.getEpisodeByName(name).enqueue(object : Callback<GOTEpisodeSearch> {
            override fun onResponse(call: Call<GOTEpisodeSearch>, response: Response<GOTEpisodeSearch>) {
                Log.w("MainActivity", "episode by name fetched")

                response.body()?.data?.let { episode ->

                    Log.e(
                        "MainActivity",
                        "Episode with name ${episode.episodeName}, Number ${episode.season}-${episode.number}, characters ${episode.characters}"
                    )
                    Episode = episode
                    drawCharacteristics()

                } ?: kotlin.run {
                    //ERROR
                    Log.w("MainActivity", "episode error on fetching by name- fetched nothing?")
                }
            }

            override fun onFailure(call: Call<GOTEpisodeSearch>, t: Throwable) {

                Log.e("MainActivity", "Error getting episode by name")
                Log.e("MainActivity", t.message)
            }
        })
    }

    fun drawCharacteristics(){


        episode_name.text = Episode?.episodeName
        number.text = Episode?.number.toString()
        if(Episode?.number == null)
            number.text = "unknown"

        season.text = Episode?.season.toString()
        if(Episode?.season == null)
            season.text = "unknown"

        airDate.text = Episode?.airDate.toString()
        if(Episode?.airDate == null)
            airDate.text = "unknown"

        director.text = Episode?.director
        if(Episode?.director == null)
            director.text = "unknown"
        nextEpisode.text = Episode?.nextEpisode
        if(Episode?.nextEpisode == null)
            nextEpisode.text = "unknown"

        Log.w("MainActivity","DRawn characteristics")


    }
}
