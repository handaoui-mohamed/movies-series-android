package com.handaoui.movies.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.handaoui.movies.Adapters.MoviesPreviewAdapter
import com.handaoui.movies.Data.Movie
import com.handaoui.movies.R
import java.util.ArrayList


class PreviewFragment : Fragment() {
    private var type: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            type = arguments.getString("TYPE")
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_preview, container, false)
        when (this.type){
            "MOVIES" -> this.createMoviesPreview(rootView)
            "SERIES" -> this.createSeriesPreview(rootView)
        }
        return rootView
    }

    private fun createMoviesPreview(rootView: View){
        val recyclerView: RecyclerView = rootView.findViewById(R.id.moviesPreview)
        recyclerView.layoutManager = LinearLayoutManager(rootView.context, LinearLayoutManager.HORIZONTAL, false)
        val moviesList = ArrayList<Movie>()
        moviesList.add(Movie("Harry Potter", R.drawable.harry))
        moviesList.add(Movie("The Shawshank", R.drawable.shawshank))
        moviesList.add(Movie("The beast", R.drawable.beast))
        rootView.findViewById<TextView>(R.id.sectionTitleTxt).text = getString(R.string.movies_in_projection)
        val moviesPreviewAdapter = MoviesPreviewAdapter(rootView.context, moviesList)
        recyclerView.adapter = moviesPreviewAdapter
    }

    private fun createSeriesPreview(rootView: View){
        val recyclerView: RecyclerView = rootView.findViewById(R.id.moviesPreview)
        recyclerView.layoutManager = LinearLayoutManager(rootView.context, LinearLayoutManager.HORIZONTAL, false)
        val moviesList = ArrayList<Movie>()
        moviesList.add(Movie("Harry Potter", R.drawable.harry))
        moviesList.add(Movie("The Shawshank", R.drawable.shawshank))
        moviesList.add(Movie("The beast", R.drawable.beast))
        rootView.findViewById<TextView>(R.id.sectionTitleTxt).text = getString(R.string.series_in_progress)
        val moviesPreviewAdapter = MoviesPreviewAdapter(rootView.context, moviesList)
        recyclerView.adapter = moviesPreviewAdapter
    }
}
