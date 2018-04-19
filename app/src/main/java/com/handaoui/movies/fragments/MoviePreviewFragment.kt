package com.handaoui.movies.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.handaoui.movies.adapters.MoviesPreviewAdapter
import com.handaoui.movies.R
import com.handaoui.movies.fakers.Movies


class PreviewFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_preview, container, false)
        this.createMoviesPreview(rootView)
        return rootView
    }

    private fun calculateNoOfColumns(context: Context): Int {
        val displayMetrics = context.resources.displayMetrics
        val dpWidth = displayMetrics.widthPixels / displayMetrics.density
        return (dpWidth / 180).toInt()
    }

    private fun createMoviesPreview(rootView: View) {
        val recyclerView: RecyclerView = rootView.findViewById(R.id.moviesPreview)
        recyclerView.layoutManager = GridLayoutManager(rootView.context, calculateNoOfColumns(rootView.context))
        rootView.findViewById<TextView>(R.id.sectionTitleTxt).text = getString(R.string.movies_in_projection)
        val moviesPreviewAdapter = MoviesPreviewAdapter(rootView.context, Movies.getProjectedMovies())
        recyclerView.adapter = moviesPreviewAdapter
    }
}
