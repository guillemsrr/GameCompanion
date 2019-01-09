package bernatriu.gotcompanion.activities

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import bernatriu.gotcompanion.Fragments.CharactersFragment
import bernatriu.gotcompanion.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity : AppCompatActivity() {

   /* private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        lateinit var fragment: Fragment
        when (item.itemId) {
            R.id.navigation_home -> {
                fragment = CharactersFragment()
                Log.w("suau","suau")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                //message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                //message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }

        val fragmentManager = supportFragmentManager

        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer,fragment)
        fragmentTransaction.commit()

        false
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.navigation_home -> {
                    fragment = CharactersFragment()
                    Log.w("suau","suau")
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_dashboard -> {
                    //message.setText(R.string.title_dashboard)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_notifications -> {
                    //message.setText(R.string.title_notifications)
                    return@OnNavigationItemSelectedListener true
                }
            }

            val fragmentManager = supportFragmentManager

            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragmentContainer,fragment)
            fragmentTransaction.commit()

            false
        })




    }
}
