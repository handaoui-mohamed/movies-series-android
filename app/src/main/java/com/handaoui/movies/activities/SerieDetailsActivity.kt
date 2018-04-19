package com.handaoui.movies.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.handaoui.movies.R
import com.handaoui.movies.fakers.Series
import kotlinx.android.synthetic.main.activity_serie_details.*


class SerieDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_serie_details)
        val serieId = intent.getIntExtra("id", 0)
        val serie = Series.getSeriesById(serieId)

        if(serie !== null){
//            collapsing_toolbar.title = resources.getString(R.string.serieDetails)
            header.setImageResource(serie.cover)

            serieTitleTxt.text = serie.title
            serieRating.numStars = 5
            serieRating.rating = (serie.rating / 2f)
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
