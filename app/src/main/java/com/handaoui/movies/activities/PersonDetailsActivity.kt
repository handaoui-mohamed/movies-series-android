package com.handaoui.movies.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.handaoui.movies.Config
import com.handaoui.movies.R
import com.handaoui.movies.data.Person
import com.handaoui.movies.fakers.User
import com.handaoui.movies.fragments.PersonsFragment
import com.handaoui.movies.fragments.PreviewFragment
import com.handaoui.movies.services.Api
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.android.synthetic.main.latest_comment.*
import kotlinx.android.synthetic.main.movie_projection_room.*
import kotlinx.android.synthetic.main.summary.*
import retrofit2.Call
import retrofit2.Callback


class PersonDetailsActivity : AppCompatActivity() {
    private var personId: Int = 0
    private var loading = true
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_details)
        context = this

        personId = intent.getIntExtra("id", 0)
        movieTitleTxt.text = intent.getStringExtra("name")
        movieDescriptionTxt.text = intent.getStringExtra("biography")
        Picasso.with(context)
                .load(Config.imagePreviewUrl + intent.getStringExtra("profile_path"))
                .into(header)
        getPersonDetails()
    }

    private fun getPersonDetails() {
        Api.personService.getPerson(personId).enqueue(object : Callback<Person> {
            override fun onResponse(call: Call<Person>, response: retrofit2.Response<Person>) {
                loading = false
                val person = response.body()
                if (person != null) {
                    movieDescriptionTxt.text = person.biography
                    movieReleaseTxt.text = "${resources.getString(R.string.birthday)}:  ${person.birthday}"

                    Picasso.with(context)
                            .load(Config.imageUrl + person.profile_path)
                            .into(header)

                    seeMoreBtn.setOnClickListener {
                        movieDescriptionTxt.maxLines = 200
                        seeMoreBtn.visibility = View.GONE
                    }

                    // favorite button
                    var isFavorite = false

                    favoriteBtn.setOnClickListener {
                        isFavorite = !isFavorite
                        toggleFavorite(isFavorite, true, personId)
                    }

//                    // related movies
//                    val moviesPreviewFragment = PreviewFragment().apply {
//                        arguments = Bundle().apply {
//                            putString("type", "credits")
//                            putInt("id", personId)
//                        }
//                    }
//
//                    supportFragmentManager
//                            .beginTransaction()
//                            .replace(R.id.relatedMoviesContainer, moviesPreviewFragment, moviesPreviewFragment.tag)
//                            .commit()
                }
            }

            override fun onFailure(call: Call<Person>, t: Throwable) {
                Log.i("PersonDetails", t.toString())
                loading = false
            }
        })
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
