package com.example.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.get
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.example.Articles
import com.example.example.Model
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.DelicateCoroutinesApi
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


class MainActivity : AppCompatActivity() {


    lateinit var navigation_toggle : ActionBarDrawerToggle
    lateinit var drawer_layout: DrawerLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val nav_btn: ImageView = findViewById(R.id.drawer_btn)



        //TabLayout
        val tablayout: TabLayout = findViewById(R.id.tab_layout)
        val viewpager: ViewPager2 = findViewById(R.id.viewpager2)


        val viewpageradaper = fragadapter(supportFragmentManager, lifecycle, applicationContext)
        viewpager.adapter = viewpageradaper

        TabLayoutMediator(tablayout, viewpager) { tab, position ->
            run {
                when (position) {
                    0 -> tab.text = "India"
                    1 -> tab.text = "Nigeria"
                    2 -> tab.text = "Indonesia"
                    3 -> tab.text = "Ireland"
                    4 -> tab.text = "Israel"
                    else -> {
                        tab.text = "Japan"
                    }
                }
            }
        }.attach()






//        DrawerLayout
        drawer_layout = findViewById(R.id.drawer_layout)
        val nagivation_view: NavigationView = findViewById(R.id.navigation_view)

        navigation_toggle = ActionBarDrawerToggle(this, drawer_layout, R.string.open, R.string.close)
        drawer_layout.addDrawerListener(navigation_toggle)
        navigation_toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        nagivation_view.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {

                    Toast.makeText(applicationContext, "home clicked", Toast.LENGTH_LONG)
                        .show()
                       Log.d("navbar" , "Home selected" )
                }
                R.id.share_market -> Toast.makeText(
                    applicationContext,
                    "share-market clicked",
                    Toast.LENGTH_LONG
                ).show()
                R.id.politics -> Toast.makeText(
                    applicationContext,
                    "politics clicked",
                    Toast.LENGTH_LONG
                ).show()
                R.id.sports -> Toast.makeText(
                    applicationContext,
                    "sports clicked",
                    Toast.LENGTH_LONG
                ).show()
                R.id.education -> Toast.makeText(
                    applicationContext,
                    "education clicked",
                    Toast.LENGTH_LONG
                ).show()
                R.id.bollywood -> Toast.makeText(
                    applicationContext,
                    "bollywood clicked",
                    Toast.LENGTH_LONG
                ).show()

            }
            true

        }



        nav_btn.setOnClickListener(View.OnClickListener {
            drawer_layout.openDrawer(GravityCompat.START)


        })


    }


    override fun onBackPressed() {
        if(drawer_layout.isDrawerOpen(GravityCompat.START))
        {
            drawer_layout.closeDrawer(GravityCompat.START)
        }
        else {


            super.onBackPressed()

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(navigation_toggle.onOptionsItemSelected(item))
        {
            return  true
        }

        return super.onOptionsItemSelected(item)
    }



}