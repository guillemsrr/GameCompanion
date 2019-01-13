package bernatriu.gotcompanion.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import bernatriu.gotcompanion.R

class CharacterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)

            var character_name = intent.getStringExtra("character")

            Log.w("CharacterFragment","preparing to get specific characters: ${character_name}")

            //Log.e("CharacterFragment", "there is no bundle!")




    }


}
