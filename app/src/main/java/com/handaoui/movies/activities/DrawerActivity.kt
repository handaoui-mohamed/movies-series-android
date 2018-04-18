package com.handaoui.movies.activities

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.handaoui.movies.R
import com.handaoui.movies.fragments.CinemaFragment
import com.handaoui.movies.fragments.HomeFragment
import com.handaoui.movies.fragments.SeriePreviewFragment
import kotlinx.android.synthetic.main.activity_drawer.*
import kotlinx.android.synthetic.main.app_bar_drawer.*


class DrawerActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        nav_view.menu.getItem(0).isChecked = true
        loadFragment(HomeFragment())
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.drawer, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                ViewCompat.setElevation(appBarLayout, 4f)
                setTitle(R.string.home)
                loadFragment(HomeFragment())
            }
            R.id.nav_cinema-> {
                ViewCompat.setElevation(appBarLayout, 0f)
                setTitle(R.string.cinema)
                loadFragment(CinemaFragment())
            }
            R.id.nav_series -> {
                ViewCompat.setElevation(appBarLayout, 0f)
                setTitle(R.string.tvShows)
                loadFragment(SeriePreviewFragment())
            }
            R.id.nav_persons -> {
                setTitle(R.string.persons)
            }
            R.id.nav_comments -> {
                setTitle(R.string.comments_ratings)
            }
            R.id.nav_bookmark -> {
                setTitle(R.string.bookmark)
            }
            R.id.nav_settings -> {
                setTitle(R.string.settings)
            }
            R.id.nav_contact -> {
                setTitle(R.string.contact_us)
            }
            R.id.nav_share -> {
                setTitle(R.string.share)
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun loadFragment(fragment: Fragment){
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.drawer_fragment_container, fragment, fragment.tag)
                .commit()
    }
}