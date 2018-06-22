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
import com.handaoui.movies.R
import com.handaoui.movies.adapters.MoviesPreviewAdapter
import com.handaoui.movies.daos.Db
import com.handaoui.movies.data.Movie
import com.handaoui.movies.dtos.MovieCreditDto
import com.handaoui.movies.dtos.MoviesDto
import com.handaoui.movies.services.Api
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback


class PreviewFragment : Fragment() {
    private var loading = true
    private var page = 1
    private var dataId = 0

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
            "related", "credits" -> {
                layoutManager = GridLayoutManager(rootView.context, 1, GridLayoutManager.HORIZONTAL, false)
                recyclerView.setHasFixedSize(true)
                dataId = args.getInt("id")
            }
        }

        loadData(moviesPreviewAdapter, type)

        recyclerView.layoutManager = layoutManager
        if (type != "bookmark") recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            var pastVisibleItems: Int = 0
            var visibleItemCount: Int = 0
            var totalItemCount: Int = 0

            override fun onScrolled(recyclerView: RecyclerView?,
                                    dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                visibleItemCount = layoutManager.childCount
                totalItemCount = layoutManager.itemCount
                pastVisibleItems = layoutManager.findFirstVisibleItemPosition()

                if ((visibleItemCount + pastVisibleItems >= totalItemCount) && !loading)
                    loadData(moviesPreviewAdapter, type)
            }
        })
    }

    private fun loadData(moviesPreviewAdapter: MoviesPreviewAdapter, type: String) {
        loading = true
        var moviesCallback: Callback<MoviesDto>? = null

        if (type != "credits") moviesCallback = object : Callback<MoviesDto> {
            override fun onResponse(call: Call<MoviesDto>, response: retrofit2.Response<MoviesDto>) {
                loading = false
                val res = response.body()
                if (res?.results != null) {
                    moviesPreviewAdapter.addToList(res.results)
                    page++
                }
            }

            override fun onFailure(call: Call<MoviesDto>, t: Throwable) {
                Log.i("Movies Playing", t.toString())
                loading = false
            }
        }

        when (type) {
            "all" -> {
                Api.movieService.getAllMovies(page).enqueue(moviesCallback)
            }
            "projected" -> {
                Api.movieService.getPlayingMovies(page).enqueue(moviesCallback)
            }
            "bookmark" -> {
                Observable.just(Db.getInstance(context!!))
                        .subscribeOn(Schedulers.io())
                        .subscribe { db ->
                            val movies = ArrayList<Movie>()
                            movies.addAll(db.movieDao().getAllMovies())
                            moviesPreviewAdapter.addToList(movies)
                        }
            }
            "related" -> {
                Api.movieService.getSimilarMovies(dataId, page).enqueue(moviesCallback)
            }
            "credits" -> {
                if (page == 1) Api.personService.getMovieCredits(dataId).enqueue(object : Callback<MovieCreditDto> {
                    override fun onResponse(call: Call<MovieCreditDto>, response: retrofit2.Response<MovieCreditDto>) {
                        loading = false
                        val res = response.body()
                        if (res?.cast != null) {
                            moviesPreviewAdapter.addToList(res.cast)
                            moviesPreviewAdapter.addToList(res.crew)
                            page++
                        }
                    }

                    override fun onFailure(call: Call<MovieCreditDto>, t: Throwable) {
                        Log.i("Movies credits", t.toString())
                        loading = false
                    }
                })
            }
        }
    }

}
