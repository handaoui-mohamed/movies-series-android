package com.handaoui.movies.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.handaoui.movies.adapters.MoviesPreviewAdapter
import com.handaoui.movies.R
import com.handaoui.movies.data.Movie
import com.handaoui.movies.dtos.MoviesDto
import com.handaoui.movies.services.Api
import retrofit2.Call
import retrofit2.Callback


class PreviewFragment : Fragment() {
    private var loading = true
    private var page = 1
    private var movieId = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_preview, container, false)
        this.createMoviesPreview(rootView)
        return rootView
    }

    private fun calculateNoOfColumns(context: Context): Int {
        val displayMetrics = context.resources.displayMetrics
        val dpWidth = displayMetrics.widthPixels / displayMetrics.density
        return (dpWidth / 180).toInt()
    }

    private fun createMoviesPreview(rootView: View) {
        val args = arguments
        val type = args!!.getString("type", "projected")
        val recyclerView: RecyclerView = rootView.findViewById(R.id.moviesPreview)
        val moviesPreviewAdapter = MoviesPreviewAdapter(rootView.context, ArrayList())
        var layoutManager = GridLayoutManager(rootView.context, calculateNoOfColumns(rootView.context))
        recyclerView.adapter = moviesPreviewAdapter

        when (type) {
            "all" -> {
                rootView.findViewById<TextView>(R.id.sectionTitleTxt).visibility = View.GONE
                loadData(moviesPreviewAdapter, type)
            }
            "projected" -> {
                rootView.findViewById<TextView>(R.id.sectionTitleTxt).text = getString(R.string.movies_in_projection)
                loadData(moviesPreviewAdapter, type)
            }
            "bookmark" -> {
                rootView.findViewById<TextView>(R.id.sectionTitleTxt).visibility = View.GONE
                loadData(moviesPreviewAdapter, type)
            }
            "related" -> {
                layoutManager = GridLayoutManager(rootView.context, 1, GridLayoutManager.HORIZONTAL, false)
                movieId = args.getInt("id")
                loadData(moviesPreviewAdapter, type)
                rootView.findViewById<View>(R.id.sectionTitleTxt).visibility = View.GONE
            }
        }

        recyclerView.layoutManager = layoutManager

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            var pastVisiblesItems: Int = 0
            var visibleItemCount: Int = 0
            var totalItemCount: Int = 0

            override fun onScrolled(recyclerView: RecyclerView?,
                                    dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                visibleItemCount = layoutManager.childCount
                totalItemCount = layoutManager.itemCount
                pastVisiblesItems = layoutManager.findFirstVisibleItemPosition()

                if (visibleItemCount + pastVisiblesItems >= totalItemCount) loadData(moviesPreviewAdapter, type)
            }
        })
    }

    private fun loadData(moviesPreviewAdapter: MoviesPreviewAdapter, type:String){

        val moviesCallback = object : Callback<MoviesDto> {
            override fun onResponse(call: Call<MoviesDto>, response: retrofit2.Response<MoviesDto>) {
                val res = response.body()
                if (res?.results != null) moviesPreviewAdapter.addToList(res.results)
            }

            override fun onFailure(call: Call<MoviesDto>, t: Throwable) {
                Log.i("Movies Playing", t.toString())
            }
        }

        when (type) {
            "all" -> {
                Api.movieService.getAllMovies(page++).enqueue(moviesCallback)
            }
            "projected" -> {
                Api.movieService.getPlayingMovies(page++).enqueue(moviesCallback)
            }
            "bookmark" -> {}
            "related" -> {}
        }
    }

}
