package com.handaoui.movies

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var recyclerView:RecyclerView = moviesPreview as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        var moviesList = ArrayList<MoviePreview>()
        moviesList.add(MoviePreview("Harry Potter", R.drawable.harry))
        moviesList.add(MoviePreview("The Shawshank", R.drawable.shawshank))
        moviesList.add(MoviePreview("The beast", R.drawable.beast))

        var moviesPreviewAdapter = MoviesPreviewAdapter(applicationContext, moviesList)
        recyclerView.adapter = moviesPreviewAdapter
    }
}
