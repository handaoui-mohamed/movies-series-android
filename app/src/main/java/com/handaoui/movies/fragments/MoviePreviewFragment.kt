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
import android.widget.Toast
import com.handaoui.movies.adapters.MoviesPreviewAdapter
import com.handaoui.movies.R
import com.handaoui.movies.data.Movie
import com.handaoui.movies.dtos.MoviesDto
import com.handaoui.movies.fakers.Movies
import com.handaoui.movies.fakers.User
import com.handaoui.movies.services.Api
import retrofit2.Call
import retrofit2.Callback


class PreviewFragment : Fragment() {
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
        var movies = ArrayList<Movie>()

        when(type){
            "all" ->{
                recyclerView.layoutManager = GridLayoutManager(rootView.context, calculateNoOfColumns(rootView.context))
                rootView.findViewById<TextView>(R.id.sectionTitleTxt).visibility = View.GONE
                movies = Movies.list
            }
            "projected" ->{
                Api.movieService.getPlayingMovies(1).enqueue(object : Callback<MoviesDto> {
                    override fun onResponse(call: Call<MoviesDto>, response: retrofit2.Response<MoviesDto>) {
                        val res = response.body()
                        if(res?.results != null){
                            recyclerView.layoutManager = GridLayoutManager(rootView.context, calculateNoOfColumns(rootView.context))
                            rootView.findViewById<TextView>(R.id.sectionTitleTxt).text = getString(R.string.movies_in_projection)
                            val moviesPreviewAdapter = MoviesPreviewAdapter(rootView.context, res.results)
                            recyclerView.adapter = moviesPreviewAdapter
                            Log.i("MoviePreview",""+res?.results.size)
                        }
                    }

                    override fun onFailure(call: Call<MoviesDto>, t: Throwable) {
                        Log.i("MoviePreview", t.toString())
                    }
                })
            }
            "bookmark" ->{
                recyclerView.layoutManager = GridLayoutManager(rootView.context, calculateNoOfColumns(rootView.context))
                rootView.findViewById<TextView>(R.id.sectionTitleTxt).visibility = View.GONE
                movies = Movies.getListFromIds(User.profile.favoriteMovies)
            }
            "related" ->{
                recyclerView.layoutManager = GridLayoutManager(rootView.context, 1, GridLayoutManager.HORIZONTAL, false)
                val movieId = args.getInt("id")
                rootView.findViewById<View>(R.id.sectionTitleTxt).visibility = View.GONE
                movies = Movies.getRelatedMovies(movieId)
            }
        }

//        val moviesPreviewAdapter = MoviesPreviewAdapter(rootView.context, movies)
//        recyclerView.adapter = moviesPreviewAdapter
    }
}
