package bernatriu.gotcompanion.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import bernatriu.gotcompanion.Models.GOTEpisodeSearch
import bernatriu.gotcompanion.R
import bernatriu.gotcompanion.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EpisodeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_episode)

        var episode_name = intent.getStringExtra("episode")

        Log.w("MainActivity","preparing to open up specific episode: ${episode_name}")

        getEpisode(episode_name)
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
}
