package bw.co.ultimateinformatics.mpepuapp.activities

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.TextView
import bw.co.ultimateinformatics.mpepuapp.R
import bw.co.ultimateinformatics.mpepuapp.fragments.H4AFragment
import bw.co.ultimateinformatics.mpepuapp.fragments.W4AFragment
import bw.co.ultimateinformatics.mpepuapp.fragments.W4HFragment

class AllGraphsActivity : AppCompatActivity() {

    //private TextView mTextMessage;

    private var toolbar: ActionBar? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val frag: Fragment

        when (item.itemId) {
            R.id.navigation_weight_for_age -> {
                toolbar!!.title = "Weight for Age"
                //frag = new W4AFragment();
                //.setArguments();
                loadFragment(W4AFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_weight_for_height -> {
                toolbar!!.title = "Weight for Height"
                //frag = new W4HFragment();
                loadFragment(W4HFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_height_for_age -> {
                toolbar!!.title = "Height for Age"
                loadFragment(H4AFragment())

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_graphs)

        toolbar = supportActionBar

        // mTextMessage = findViewById(R.id.message);
        val navigation = findViewById<BottomNavigationView>(R.id.navigation)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        toolbar!!.title = "Weight for Age"
        //Load the Weight for Age Fragment by Default
        loadFragment(W4AFragment())
    }


    private fun loadFragment(fragment: Fragment) {
        // load fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}
