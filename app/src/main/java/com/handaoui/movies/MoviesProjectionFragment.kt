package com.handaoui.movies

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.ArrayList


class MoviesProjectionFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater!!.inflate(R.layout.fragment_movies_projection, container, false)

        var recyclerView: RecyclerView = rootView.findViewById(R.id.moviesPreview)
        recyclerView.layoutManager = LinearLayoutManager(rootView.context, LinearLayoutManager.HORIZONTAL, false)
        var moviesList = ArrayList<MoviePreview>()
        moviesList.add(MoviePreview("Harry Potter", R.drawable.harry))
        moviesList.add(MoviePreview("The Shawshank", R.drawable.shawshank))
        moviesList.add(MoviePreview("The beast", R.drawable.beast))

        var moviesPreviewAdapter = MoviesPreviewAdapter(rootView.context, moviesList)
        recyclerView.adapter = moviesPreviewAdapter

        return rootView
    }
}
