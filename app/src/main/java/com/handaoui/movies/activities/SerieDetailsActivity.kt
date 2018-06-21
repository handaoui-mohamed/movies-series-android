package com.handaoui.movies.activities

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.handaoui.movies.R
import kotlinx.android.synthetic.main.activity_serie_details.*
import android.content.Intent
import android.util.Log
import com.handaoui.movies.Config
import com.handaoui.movies.adapters.SeasonsListAdapter
import com.handaoui.movies.data.Movie
import com.handaoui.movies.data.Series
import com.handaoui.movies.dtos.CommentsDto
import com.handaoui.movies.fakers.User
import com.handaoui.movies.fragments.SeriePreviewFragment
import com.handaoui.movies.services.Api
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.android.synthetic.main.latest_comment.*
import kotlinx.android.synthetic.main.movie_projection_room.*
import kotlinx.android.synthetic.main.summary.*
import retrofit2.Call
import retrofit2.Callback


class SerieDetailsActivity : AppCompatActivity() {
    val source = "source"
    private var serieId: Int = 0
    private var loading = true
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_serie_details)
        serieId = intent.getIntExtra("id", 0)
        context = this

        serieTitleTxt.text = intent.getStringExtra("name")
        Picasso.with(this)
                .load(Config.imagePreviewUrl + intent.getStringExtra("poster_path"))
                .into(Serieheader)
        serieReleaseTxt.text = "${resources.getString(R.string.releaseDate)}:  ${intent.getStringExtra("first_air_date")}"
        movieDescriptionTxt.text = intent.getStringExtra("overview")
        serieRating.numStars = 5
        serieRating.rating = (intent.getFloatExtra("vote_average", 0f)) / 2
        getSerieDetails()

//
//            seeMoreBtn.setOnClickListener {
//                movieDescriptionTxt.maxLines = 200
//                serieDetailsContainer.removeView(seeMoreBtn)
//            }
//
//            var isFavorite = User.profile.isSeriesFavored(serieId)
//            toggleFavorite(isFavorite)
//
//            serfavoriteBtn.setOnClickListener {
//                isFavorite = !isFavorite
//                toggleFavorite(isFavorite, true, serieId)
//            }
//
////            val personsFragment = PersonsFragment().apply {
////                arguments = Bundle().apply {
////                    putInt("id", serieId)
////                    putInt("origin", 1)
////                }
////            }
////
////            supportFragmentManager
////                    .beginTransaction()
////                    .replace(R.id.personsContainer, personsFragment, personsFragment.tag)
////                    .commit()
//
//
//        }


//        val mLayoutManager = LinearLayoutManager(this.applicationContext)
//        val mAdapter = SeasonsListAdapter(this, ArrayList())
//
//        var recyclerView = findViewById<RecyclerView>(R.id.seasons_list_recycler).apply {
//            layoutManager= mLayoutManager
//            itemAnimator = DefaultItemAnimator()
//            adapter = mAdapter
//        }
//        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

////        val comments = Comments.getMovieComments(/*movieId*/0) // for testing purposes
////        val latestComment = comments[comments.size - 1]
//        commentatorNameTxt.text = latestComment.commentator
//        latestRating.rating = latestComment.rating / 2
//        Log.i("vote_average", latestRating.rating.toString())
//        commentContent.text = latestComment.content

//        seeCommentsBtn.setOnClickListener {
//            val intent = Intent(this, ReviewsActivity::class.java).apply {
//                intent.getIntExtra("type", "Movie")
//                intent.getIntExtra("id", serieId)
//            }
//            startActivity(intent)
//            overridePendingTransition(R.anim.abc_slide_in_bottom, R.anim.abc_slide_out_bottom)
//        }

        // related movies
//        val seriesPreviewFragment = SeriePreviewFragment().apply {
//            arguments = Bundle().apply {
//                putString("type", "related")
//                putInt("id", serieId)
//            }
//        }
//
//        supportFragmentManager
//                .beginTransaction()
//                .replace(R.id.relatedSeriesContainer, seriesPreviewFragment, seriesPreviewFragment.tag)
//                .commit()
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

//    fun seeReviews(view: View) {
//        val intent = Intent(this, ReviewsActivity::class.java).apply {
//            intent.getIntExtra(source, "Serie")
//        }
//        startActivity(intent)
//    }

    private fun toggleFavorite(isFavorite: Boolean, updateProfile: Boolean = false, serieId: Int = -1) {
        if (isFavorite) {
            serfavoriteBtn.setImageResource(R.drawable.zzz_heart)
            if (updateProfile) User.profile.addSeries(serieId)
        } else {
            serfavoriteBtn.setImageResource(R.drawable.ic_favorite_border_black_24dp)
            if (updateProfile) User.profile.removeSeries(serieId)
        }
    }

    private fun getSerieDetails() {
        Api.serieService.getSerie(serieId).enqueue(object : Callback<Series> {
            override fun onResponse(call: Call<Series>, response: retrofit2.Response<Series>) {
                loading = false
                val serie = response.body()
                if (serie != null) {
                    movieDescriptionTxt.text = serie.overview

                    Picasso.with(context)
                            .load(Config.imageUrl + serie.poster_path)
                            .into(Serieheader)

                    seeMoreBtn.setOnClickListener {
                        movieDescriptionTxt.maxLines = 200
                        seeMoreBtn.visibility = View.GONE
                    }

                    // favorite button
                    var isFavorite = User.profile.isSeriesFavored(serieId)
                    toggleFavorite(isFavorite)

                    serfavoriteBtn.setOnClickListener {
                        isFavorite = !isFavorite
                        toggleFavorite(isFavorite, true, serieId)
                    }

//                    getMovieReviews()

//                    // persons fragment
//                    val personsFragment = PersonsFragment().apply {
//                        arguments = Bundle().apply {
//                            putInt("id", movieId)
//                            putInt("origin", 0)
//                        }
//                    }
//                    supportFragmentManager
//                            .beginTransaction()
//                            .replace(R.id.personsContainer, personsFragment, personsFragment.tag)
//                            .commit()
//

                    getMovieReviews()

                    // related movies
                    val seriesPreviewFragment = SeriePreviewFragment().apply {
                        arguments = Bundle().apply {
                            putString("type", "related")
                            putInt("id", serieId)
                        }
                    }

                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.relatedSeriesContainer, seriesPreviewFragment, seriesPreviewFragment.tag)
                            .commit()
                }
            }

            override fun onFailure(call: Call<Series>?, t: Throwable?) {
                Log.i("SerieDetails", t.toString())
                loading = false
            }
        })
    }

    private fun getMovieReviews() {
        // latest comment
        Api.serieService.getSerieReviews(serieId = serieId, page = 1).enqueue(object : Callback<CommentsDto> {
            override fun onResponse(call: Call<CommentsDto>, response: retrofit2.Response<CommentsDto>) {
                loading = false
                val res = response.body()
                Log.i("commentss", "id = " + serieId + ", " + res.toString())
                if (res?.results != null && res.results.size > 0) {
                    val latestComment = res.results.last()
                    commentatorNameTxt.text = latestComment.author
                    commentContent.text = latestComment.content
                    latestRating.rating = 0f
//
//                    seeCommentsBtn.setOnClickListener {
//                        val intent = Intent(context, ReviewsActivity::class.java).apply {
//                            putExtra("type", "Movie")
//                            putExtra("id", movieId)
//                        }
//
//                        startActivity(intent)
//                        overridePendingTransition(R.anim.abc_slide_in_bottom, R.anim.abc_slide_out_bottom)
//                    }
                }
            }

            override fun onFailure(call: Call<CommentsDto>, t: Throwable) {
                Log.i("MoviesComments", t.toString())
                loading = false
            }
        })
    }
}
