package com.handaoui.movies.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.handaoui.movies.R
import com.handaoui.movies.fakers.Movies
import android.support.design.widget.CollapsingToolbarLayout
import kotlinx.android.synthetic.main.activity_movie_details.*


class MovieDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        val movieId = intent.getIntExtra("id", 0)
        val movie = Movies.getMovieById(movieId)

        if(movie !== null){
            setSupportActionBar(toolbar)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            val collapsingToolbar = findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar)
            collapsingToolbar.title = getString(R.string.movieDetails)
            header.setImageResource(movie.cover)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return true
    }
}
