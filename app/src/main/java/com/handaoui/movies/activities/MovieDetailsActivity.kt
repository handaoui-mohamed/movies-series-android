package com.handaoui.movies.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.handaoui.movies.R
import com.handaoui.movies.fakers.Comments
import com.handaoui.movies.fakers.Movies
import com.handaoui.movies.fakers.User
import com.handaoui.movies.fragments.PersonsFragment
import com.handaoui.movies.fragments.PreviewFragment
import kotlinx.android.synthetic.main.activity_movie_details.*
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.latest_comment.*
import kotlinx.android.synthetic.main.movie_projection_room.*
import kotlinx.android.synthetic.main.summary.*


class MovieDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val movieId = intent.getIntExtra("id", 0)
        val movie = Movies.getMovieById(movieId)

        if (movie !== null) {
//            header.setImageResource(movie.poster_path)

            // Movie details
            movieTitleTxt.text = movie.title
            movieRating.rating = movie.vote_average / 2
            movieDescriptionTxt.text = movie.overview
            movieReleaseTxt.text = "${resources.getString(R.string.releaseDate)}:  ${movie.release_date}"

            seeMoreBtn.setOnClickListener {
                movieDescriptionTxt.maxLines = 200
                seeMoreBtn.visibility = View.GONE
            }

            // Projection Room
            val projectRoom = movie.projectRoom
            if (projectRoom !== null) {
                projectRoomNameTxt.text = projectRoom.name
                projectRoomImg.setImageResource(projectRoom.image)
                projectRoomDescriptionTxt.text = projectRoom.address

                projectRoomBtn.setOnClickListener {
                    val map = "http://maps.google.co.in/maps?q=" + projectRoom.address
                    val i = Intent(Intent.ACTION_VIEW, Uri.parse(map))
                    startActivity(i)
                }
            } else {
                // remove projectRoom
                projectRoomContainer.visibility = View.GONE
            }

            // favorite button
            var isFavorite = User.profile.isMovieFavored(movieId)
            toggleFavorite(isFavorite)

            favoriteBtn.setOnClickListener {
                isFavorite = !isFavorite
                toggleFavorite(isFavorite, true, movieId)
            }

            // persons fragment
            val personsFragment = PersonsFragment().apply {
                arguments = Bundle().apply {
                    putInt("id", movieId)
                    putInt("origin", 0)
                }
            }
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.personsContainer, personsFragment, personsFragment.tag)
                    .commit()

            // latest comment
            val comments = Comments.getMovieComments(/*movieId*/0) // for testing purposes
            val latestComment = comments[comments.size - 1]
            commentatorNameTxt.text = latestComment.commentator
            latestRating.rating = latestComment.rating / 2
            Log.i("vote_average", latestRating.rating.toString())
            commentContent.text = latestComment.content

            seeCommentsBtn.setOnClickListener {
                val intent = Intent(this, ReviewsActivity::class.java).apply {
                    putExtra("type", "Movie")
                    putExtra("id", movieId)
                }
                startActivity(intent)
                overridePendingTransition(R.anim.abc_slide_in_bottom, R.anim.abc_slide_out_bottom)
            }

            // related movies
            val moviesPreviewFragment = PreviewFragment().apply {
                arguments = Bundle().apply {
                    putString("type", "related")
                    putInt("id", movieId)
                }
            }

            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.relatedMoviesContainer, moviesPreviewFragment, moviesPreviewFragment.tag)
                    .commit()
        }
    }

    private fun toggleFavorite(isFavorite: Boolean, updateProfile: Boolean = false, movieId: Int = -1) {
        if (isFavorite) {
            favoriteBtn.setImageResource(R.drawable.zzz_heart)
            if (updateProfile) User.profile.addMovie(movieId)
        } else {
            favoriteBtn.setImageResource(R.drawable.ic_favorite_border_black_24dp)
            if (updateProfile) User.profile.removeMovie(movieId)
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
