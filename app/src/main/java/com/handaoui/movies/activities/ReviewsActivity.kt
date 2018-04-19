package com.handaoui.movies.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import com.handaoui.movies.R
import com.handaoui.movies.adapters.ReviewsAdapter
import com.handaoui.movies.fakers.Series
import com.stepstone.apprating.listener.RatingDialogListener
import java.util.Arrays.asList
import com.stepstone.apprating.AppRatingDialog
import java.util.*


class ReviewsActivity : RatingDialogListener,AppCompatActivity() {
    override fun onNegativeButtonClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onNeutralButtonClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPositiveButtonClicked(rate: Int, comment: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reviews)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)

        val cartList = Series.list[0].comments
        val mAdapter = ReviewsAdapter(this, cartList)

        val mLayoutManager = LinearLayoutManager(this.applicationContext)
        recyclerView.setLayoutManager(mLayoutManager)
        recyclerView.setItemAnimator(DefaultItemAnimator())
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.setAdapter(mAdapter)

        val button: FloatingActionButton = findViewById(R.id.add_review)
        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                showDialog()
            }
        })
    }

    private fun showDialog() {
        AppRatingDialog.Builder()
                .setPositiveButtonText("Submit")
                .setNegativeButtonText("Cancel")
                .setNeutralButtonText("Later")
                .setNoteDescriptions(Arrays.asList("Very Bad", "Not good", "Quite ok", "Very Good", "Excellent !!!"))
                .setDefaultRating(3)
                .setTitle("Rate this application")
                .setDescription("Please select some stars and give your feedback")
                .setDefaultComment("This app is pretty cool !")
                .setStarColor(R.color.starColor)
                .setNoteDescriptionTextColor(R.color.noteDescriptionTextColor)
                .setTitleTextColor(R.color.titleTextColor)
                .setDescriptionTextColor(R.color.contentTextColor)
                .setHint("Please write your comment here ...")
                .setHintTextColor(R.color.hintTextColor)
                .setCommentTextColor(R.color.commentTextColor)
                .setCommentBackgroundColor(R.color.description)
                .setWindowAnimation(R.style.MyDialogFadeAnimation)
                .create(this)
                .show()
    }
}
