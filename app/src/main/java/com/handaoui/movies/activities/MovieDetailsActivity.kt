package com.handaoui.movies.activities

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.handaoui.movies.Config
import com.handaoui.movies.R
import com.handaoui.movies.daos.Db
import com.handaoui.movies.data.Movie
import com.handaoui.movies.data.PersonMovie
import com.handaoui.movies.dtos.CommentsDto
import com.handaoui.movies.fragments.PersonsFragment
import com.handaoui.movies.fragments.PreviewFragment
import com.handaoui.movies.holders.CreditsHolder
import com.handaoui.movies.services.Api
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.android.synthetic.main.latest_comment.*
import kotlinx.android.synthetic.main.movie_projection_room.*
import kotlinx.android.synthetic.main.summary.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import retrofit2.Call
import retrofit2.Callback


class MovieDetailsActivity : AppCompatActivity() {
    private var movieId: Int = 0
    private var loading = true
    private lateinit var context: Context
    private var movie: Movie? = null
    private lateinit var personsFragment: PersonsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        context = this

        movieId = intent.getIntExtra("id", 0)
        movieTitleTxt.text = intent.getStringExtra("title")
        movieRating.rating = intent.getFloatExtra("vote_average", 0f) / 2 + 1
        movieDescriptionTxt.text = intent.getStringExtra("overview")
        Picasso.with(context)
                .load(Config.imagePreviewUrl + intent.getStringExtra("poster_path"))
                .into(header)
        movieReleaseTxt.text = "${resources.getString(R.string.releaseDate)}:  ${intent.getStringExtra("release_date")}"
        projectRoomContainer.visibility = View.GONE

        // favorite button
        var isFavorite = false

        favoriteBtn.setOnClickListener {
            isFavorite = !isFavorite
            toggleFavorite(isFavorite, true)
        }

        doAsync {
            val db = Db.getInstance(context = context)
            movie = db!!.movieDao().getMovie(movieId)

            uiThread {
                if (movie != null) {
                    isFavorite = true
                    toggleFavorite(isFavorite)
                }
                if (isConnected()) getMovieDetails() else if (isFavorite) setMovieDetails()
            }
        }
    }

    private fun getMovieDetails() {
        Api.movieService.getMovie(movieId).enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: retrofit2.Response<Movie>) {
                loading = false
                movie = response.body()
                if (movie != null) setMovieDetails()
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.i("MovieDetails", t.toString())
                loading = false
            }
        })
    }

    fun setMovieDetails() {
        movieDescriptionTxt.text = movie!!.overview

        Picasso.with(context)
                .load(Config.imageUrl + movie!!.poster_path)
                .into(header)

        seeMoreBtn.setOnClickListener {
            movieDescriptionTxt.maxLines = 200
            seeMoreBtn.visibility = View.GONE
        }

        // persons fragment
        personsFragment = PersonsFragment().apply {
            arguments = Bundle().apply {
                putInt("id", movieId)
                putInt("origin", 0)
            }
        }
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.personsContainer, personsFragment, personsFragment.tag)
                .commit()

        getMovieReviews()

        seeCommentsBtn.setOnClickListener {
            val intent = Intent(context, ReviewsActivity::class.java).apply {
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

    private fun getMovieReviews() {
        // latest comment
        loading = true
        Api.movieService.getMovieReviews(movieId = movieId, page = 1).enqueue(object : Callback<CommentsDto> {
            override fun onResponse(call: Call<CommentsDto>, response: retrofit2.Response<CommentsDto>) {
                loading = false
                val res = response.body()
                Log.i("commentss", "id = " + movieId + ", " + res.toString())
                if (res?.results != null && res.results.size > 0) {
                    val latestComment = res.results.last()
                    commentatorNameTxt.text = latestComment.author
                    commentContent.text = latestComment.content
                    latestRating.rating = 0f
                }
            }

            override fun onFailure(call: Call<CommentsDto>, t: Throwable) {
                Log.i("MoviesComments", t.toString())
                loading = false
            }
        })
    }

    private fun toggleFavorite(isFavorite: Boolean, updateProfile: Boolean = false) {
        if (isFavorite) {
            favoriteBtn.setImageResource(R.drawable.zzz_heart)
            if (updateProfile) {
                // update isFavorite
                Observable.just(Db.getInstance(context))
                        .subscribeOn(Schedulers.io())
                        .subscribe { db ->
                            db.movieDao().insert(movie!!)

                            Log.i("crewss", "crew = " + CreditsHolder.crew.size + ", cast = " + CreditsHolder.cast.size)
                            CreditsHolder.crew.forEach { person ->
                                db.personDao().insert(person)
                                db.personMovieDao().insert(PersonMovie(movieId, person.id, "director"))
                            }

                            CreditsHolder.cast.forEach { person ->
                                db.personDao().insert(person)
                                db.personMovieDao().insert(PersonMovie(movieId, person.id,  "actor"))
                            }
                        }
            }
        } else {
            favoriteBtn.setImageResource(R.drawable.ic_favorite_border_black_24dp)
            if (updateProfile) {
                Observable.just(Db.getInstance(context))
                        .subscribeOn(Schedulers.io())
                        .subscribe { db ->
                            db.movieDao().delete(movie!!)
                            db.personMovieDao().delete(movieId)
                        }
            }
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

    private fun isConnected(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }
}
