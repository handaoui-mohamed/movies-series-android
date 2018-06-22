package com.handaoui.movies.activities

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.View
import com.handaoui.movies.Config
import com.handaoui.movies.R
import com.handaoui.movies.data.SeriesSeason
import com.handaoui.movies.fakers.Comments
import com.handaoui.movies.fakers.User
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_season_preview.*
import kotlinx.android.synthetic.main.activity_serie_details.*
import kotlinx.android.synthetic.main.latest_comment.*
import com.handaoui.movies.services.Api
import kotlinx.android.synthetic.main.summary.*
import retrofit2.Call
import retrofit2.Callback

class SeasonPreviewActivity : AppCompatActivity() {

    private var serieId: Int = 0
    private var seasonId: Int = 0
    private var loading = true
    private lateinit var context: Context


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_season_preview)
        seasonId = intent.getIntExtra("seasonId", 0)
        serieId = intent.getIntExtra("serieId", 0)



        context = this
        seasonTitleTxt.text = "Season ${intent.getStringExtra("season_number")}"
        Picasso.with(this)
                .load(Config.imagePreviewUrl + intent.getStringExtra("poster_path"))
                .into(seasonHeader)
        seasonReleaseTxt.text = "${resources.getString(R.string.releaseDate)}:  ${intent.getStringExtra("air_date")}"


        getSeasonDetails()

//            seasonRating.numStars = 5
//            seasonRating.rating = season.rating / 2
//
//            movieDescriptionTxt.text = season.description
//
//            seeMoreBtn.setOnClickListener {
//                movieDescriptionTxt.maxLines = 200
//                seasonDetailsContainer.removeView(seeMoreBtn)
//            }
//
//
//
//            val personsFragment = PersonsFragment().apply {
//                arguments = Bundle().apply {
//                    putInt("id", seasonId)
//                    putInt("origin", 1)
//                }
//            }
//
//            supportFragmentManager
//                    .beginTransaction()
//                    .replace(R.id.personsContainer, personsFragment, personsFragment.tag)
//                    .commit()
//
//
//        }



//        val mLayoutManager = LinearLayoutManager(this.applicationContext)
//        val mAdapter = EpisodesListAdapter(this, ArrayList())
//
//        var recyclerView = findViewById<RecyclerView>(R.id.seasons_list_recycler).apply {
//            layoutManager= mLayoutManager
//            itemAnimator = DefaultItemAnimator()
//            adapter = mAdapter
//        }
//        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
//
//        val comments = Comments.getMovieComments(/*movieId*/0) // for testing purposes
//        val latestComment = comments[comments.size - 1]
//        commentatorNameTxt.text = latestComment.author
//        latestRating.rating = latestComment.rating / 2
//        Log.i("vote_average", latestRating.rating.toString())
//        commentContent.text = latestComment.content

//        seeCommentsBtn.setOnClickListener {
//            val intent = Intent(this, ReviewsActivity::class.java).apply {
//                putExtra("type", "Movie")
//                putExtra("id", seasonId)
//            }
//            startActivity(intent)
//            overridePendingTransition(R.anim.abc_slide_in_bottom, R.anim.abc_slide_out_bottom)
//        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return true
    }

//    fun seeReviews(view: View) {
//        val intent = Intent(this, ReviewsActivity::class.java).apply {
//            putExtra("source", "Serie")
//        }
//        startActivity(intent)
//    }


    private fun getSeasonDetails() {
        Api.serieService.getSeason(serieId,intent.getIntExtra("episode_count", 0)).enqueue(object : Callback<SeriesSeason> {
            override fun onResponse(call: Call<SeriesSeason>, response: retrofit2.Response<SeriesSeason>) {
                loading = false
                val season = response.body()
                if (season != null) {

                    seasonTitleTxt.text = "Season ${intent.getStringExtra("season_number")} : " + intent.getStringExtra("name")
                    movieDescriptionTxt.text = season.overview

                    Picasso.with(context)
                            .load(Config.imageUrl + season.poster_path)
                            .into(seasonHeader)

                    seeMoreBtn.setOnClickListener {
                        movieDescriptionTxt.maxLines = 200
                        seeMoreBtn.visibility = View.GONE
                    }

//                    // favorite button
//                    var isFavorite = User.profile.isSeriesFavored(serieId)
//                    toggleFavorite(isFavorite)
//
//                    serfavoriteBtn.setOnClickListener {
//                        isFavorite = !isFavorite
//                        toggleFavorite(isFavorite, true, serieId)
//                    }

//                    val mLayoutManager = LinearLayoutManager(context.applicationContext)
//                    val mAdapter = EpisodesListAdapter(context, season.episodes, season.id, serieId)
//
//                    var recyclerView = findViewById<RecyclerView>(R.id.seasons_list_recycler).apply {
//                        layoutManager= mLayoutManager
//                        itemAnimator = DefaultItemAnimator()
//                        adapter = mAdapter
//                    }
//                    recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))


//                    getMovieReviews()

                    // related movies
//                    val seriesPreviewFragment = SeriePreviewFragment().apply {
//                        arguments = Bundle().apply {
//                            putString("type", "related")
//                            putInt("id", serieId)
//                        }
//                    }
//
//                    supportFragmentManager
//                            .beginTransaction()
//                            .replace(R.id.relatedSeriesContainer, seriesPreviewFragment, seriesPreviewFragment.tag)
//                            .commit()
                }
            }

            override fun onFailure(call: Call<SeriesSeason>?, t: Throwable?) {
                Log.i("SeasonDetails", t.toString())
                loading = false
            }
        })
    }

}
