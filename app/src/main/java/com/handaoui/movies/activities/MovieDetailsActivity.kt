package com.handaoui.movies.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.handaoui.movies.R
import com.handaoui.movies.fakers.Movies
import com.handaoui.movies.fakers.User
import com.handaoui.movies.fragments.PersonsFragment
import kotlinx.android.synthetic.main.activity_movie_details.*


class MovieDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val movieId = intent.getIntExtra("id", 0)
        val movie = Movies.getMovieById(movieId)

        if (movie !== null) {
            header.setImageResource(movie.cover)

            // Movie details
            movieTitleTxt.text = movie.title
            movieRating.numStars = 5
            movieRating.rating = movie.rating / 2
            movieDescriptionTxt.text = movie.description

            seeMoreBtn.setOnClickListener {
                movieDescriptionTxt.maxLines = 200
                movieDetailsContainer.removeView(seeMoreBtn)
            }

            // Projection Room
            val projectRoom = movie.projectRoom
            if (projectRoom !== null) {
                projectRoomNameTxt.text = projectRoom.name
                projectRoomImg.setImageResource(projectRoom.image)
                projectRoomDescriptionTxt.text = projectRoom.address
            } else {
                // remove projectRoom
                movieDetailsContainer.removeView(projectRoomContainer)
            }

            // favorite button
            var isFavorite = User.profile.isMovieFavored(movieId)
            toggleFavorite(isFavorite)

            favoriteBtn.setOnClickListener {
                isFavorite = !isFavorite
                toggleFavorite(isFavorite, true, movieId)
            }

            // persons fragment
            val personsFragment  = PersonsFragment().apply {
                arguments.putInt("id", movieId)
            }
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.personsContainer, personsFragment, personsFragment.tag)
                    .commit()
        }
    }

    private fun toggleFavorite(isFavorite: Boolean, updateProfile:Boolean = false, movieId:Int = -1){
        if (isFavorite) {
            favoriteBtn.setImageResource(R.drawable.zzz_heart)
            if(updateProfile) User.profile.addMovie(movieId)
        }
        else {
            favoriteBtn.setImageResource(R.drawable.ic_favorite_border_black_24dp)
            if(updateProfile) User.profile.removeMovie(movieId)
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
