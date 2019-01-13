package bernatriu.gotcompanion.activities

import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import bernatriu.gotcompanion.Fragments.CharactersFragment
import bernatriu.gotcompanion.Fragments.EpisodesFragment
import bernatriu.gotcompanion.Fragments.HousesFragment
import bernatriu.gotcompanion.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener{ item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.navigation_home -> {
                    fragment = CharactersFragment()
                    //message.setText(R.string.title_home)
                    Log.w("pere","home")


                }
                R.id.navigation_dashboard -> {
                    Log.w("pere","dash")

                    fragment = HousesFragment()
                    //message.setText(R.string.title_dashboard)

                }
                R.id.navigation_notifications -> {
                    Log.w("pere","fita")
                    fragment = EpisodesFragment()
                    //message.setText(R.string.title_notifications)

                }
            }
            val fragmentManager = supportFragmentManager

            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragmentContainer,fragment)
            fragmentTransaction.commit()
            true
        }

        navigation.selectedItemId = R.id.navigation_home
    }
}
