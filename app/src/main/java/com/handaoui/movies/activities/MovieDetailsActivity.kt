package com.handaoui.movies.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.handaoui.movies.R
import com.handaoui.movies.fakers.Movies
import kotlinx.android.synthetic.main.activity_movie_details.*


class MovieDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        val movieId = intent.getIntExtra("id", 0)
        val movie = Movies.getMovieById(movieId)

        if(movie !== null){
            collapsing_toolbar.title = resources.getString(R.string.movieDetails)
            header.setImageResource(movie.cover)

            movieTitleTxt.text = movie.title
            movieRating.numStars = 5
            movieRating.rating = (movie.rating / 4)
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
