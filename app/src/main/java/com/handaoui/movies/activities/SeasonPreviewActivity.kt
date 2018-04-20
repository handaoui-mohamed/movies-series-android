package com.handaoui.movies.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Button
import com.handaoui.movies.R
import com.handaoui.movies.adapters.EpisodesListAdapter
import com.handaoui.movies.adapters.SeasonsListAdapter
import com.handaoui.movies.fakers.Comments
import com.handaoui.movies.fakers.Series
import com.handaoui.movies.fragments.PersonsFragment
import kotlinx.android.synthetic.main.activity_season_preview.*
import kotlinx.android.synthetic.main.latest_comment.*
import kotlinx.android.synthetic.main.summary.*

class SeasonPreviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_season_preview)
        val seasonId = intent.getIntExtra("id", 0)
        val seriesId = intent.getIntExtra("seriesId", 0)
        val season = Series.getSeriesById(seriesId)!!.seasons[seasonId]

        if(season !== null){
            seasonHeader.setImageResource(season.cover)

            seasonReleaseTxt.text = "${resources.getString(R.string.releaseDate)}:  ${season.date}"

            seasonTitleTxt.text = "Season ${seasonId+1}"
            seasonRating.numStars = 5
            seasonRating.rating = season.rating / 2

            movieDescriptionTxt.text = season.description

            seeMoreBtn.setOnClickListener {
                movieDescriptionTxt.maxLines = 200
                seasonDetailsContainer.removeView(seeMoreBtn)
            }



            val personsFragment = PersonsFragment().apply {
                arguments = Bundle().apply {
                    putInt("id", seasonId)
                    putInt("origin", 1)
                }
            }

            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.personsContainer, personsFragment, personsFragment.tag)
                    .commit()


        }



        val mLayoutManager = LinearLayoutManager(this.applicationContext)
        val mAdapter = EpisodesListAdapter(this, Series.list[seriesId].seasons[seasonId].episodes)

        var recyclerView = findViewById<RecyclerView>(R.id.seasons_list_recycler).apply {
            layoutManager= mLayoutManager
            itemAnimator = DefaultItemAnimator()
            adapter = mAdapter
        }
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        val comments = Comments.getMovieComments(/*movieId*/0) // for testing purposes
        val latestComment = comments[comments.size - 1]
        commentatorNameTxt.text = latestComment.commentator
        latestRating.rating = latestComment.rating / 2
        Log.i("rating", latestRating.rating.toString())
        commentContent.text = latestComment.content

        seeCommentsBtn.setOnClickListener {
            val intent = Intent(this, ReviewsActivity::class.java).apply {
                putExtra("type", "Movie")
                putExtra("id", seasonId)
            }
            startActivity(intent)
            overridePendingTransition(R.anim.abc_slide_in_bottom, R.anim.abc_slide_out_bottom)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return true
    }

    fun seeReviews(view: View) {
        val intent = Intent(this, ReviewsActivity::class.java).apply {
            putExtra("source", "Serie")
        }
        startActivity(intent)
    }

}
