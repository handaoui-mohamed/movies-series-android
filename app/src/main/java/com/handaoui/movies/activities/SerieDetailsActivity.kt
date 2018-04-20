package com.handaoui.movies.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import com.handaoui.movies.R
import com.handaoui.movies.adapters.ReviewsAdapter
import com.handaoui.movies.fakers.Series
import kotlinx.android.synthetic.main.activity_serie_details.*
import android.content.Intent
import com.handaoui.movies.fakers.User
import com.handaoui.movies.fragments.PersonsFragment
import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.android.synthetic.main.summary.*


class SerieDetailsActivity : AppCompatActivity() {
    val source = "source"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_serie_details)
        val serieId = intent.getIntExtra("id", 0)
        val serie = Series.getSeriesById(serieId)

        if(serie !== null){
            Serieheader.setImageResource(serie.cover)

            serieReleaseTxt.text = "${resources.getString(R.string.releaseDate)}:  ${serie.date}"

            serieTitleTxt.text = serie.title
            serieRating.numStars = 5
            serieRating.rating = serie.rating / 2

            movieDescriptionTxt.text = serie.description

            seeMoreBtn.setOnClickListener {
                movieDescriptionTxt.maxLines = 200
                serieDetailsContainer.removeView(seeMoreBtn)
            }

            var isFavorite = User.profile.isSeriesFavored(serieId)
            toggleFavorite(isFavorite)

            serfavoriteBtn.setOnClickListener {
                isFavorite = !isFavorite
                toggleFavorite(isFavorite, true, serieId)
            }

            val personsFragment = PersonsFragment().apply {
                arguments = Bundle().apply {
                    putInt("id", serieId)
                    putInt("origin", 1)
                }
            }

            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.personsContainer, personsFragment, personsFragment.tag)
                    .commit()


        }

        val button:Button = findViewById(R.id.see_reviews_buttons)
        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                seeReviews(v)
            }
        })

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

    fun seeReviews(view: View) {
        val intent = Intent(this, ReviewsActivity::class.java).apply {
            putExtra(source, "Serie")
        }
        startActivity(intent)
    }

    private fun toggleFavorite(isFavorite: Boolean, updateProfile: Boolean = false, serieId: Int = -1) {
        if (isFavorite) {
            serfavoriteBtn.setImageResource(R.drawable.zzz_heart)
            if (updateProfile) User.profile.addSeries(serieId)
        } else {
            serfavoriteBtn.setImageResource(R.drawable.ic_favorite_border_black_24dp)
            if (updateProfile) User.profile.removeSeries(serieId)
        }
    }
}
