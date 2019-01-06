package com.guillemserralorenz.twitchcompanion

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.guillemserralorenz.twitchcompanion.models.TWStreamResponse
import com.guillemserralorenz.twitchcompanion.network.ApiService
import com.guillemserralorenz.twittercompanion.R
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        getApiData()
    }

    fun getApiData(){
        val streams = ApiService.service.getStreams().enqueue(object: Callback<TWStreamResponse>{
            override fun onResponse(call: Call<TWStreamResponse>, response: Response<TWStreamResponse>) {
                response.body()?.data?.let{streams->
                    for(stream in streams){
                        Log.i("MainActivity", "Stream with title ${stream.title} and thumbnail ${stream.thumbnailUrl}")
                    }
                }?:kotlin.run{
                    //error
                }
            }

            override fun onFailure(call: Call<TWStreamResponse>, t: Throwable) {
                Log.e("MainActivity", "Error getting streams", t)
            }
        })
    }
}
