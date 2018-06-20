package com.handaoui.movies.fragments

import android.content.Context
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.handaoui.movies.adapters.SeriePreviewAdapter
import com.handaoui.movies.adapters.ReviewsAdapter
import com.handaoui.movies.R
import com.handaoui.movies.fakers.Series
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.handaoui.movies.dtos.SeriesDto
import com.handaoui.movies.fakers.User
import com.handaoui.movies.services.Api
import retrofit2.Call
import retrofit2.Callback

class SeriePreviewFragment : Fragment() {
    private var loading = true
    private var page = 1
    private var serieId = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_serie_preview, container, false)
        this.createSeriesPreview(rootView)
        return rootView
    }

    private fun calculateNoOfColumns(context: Context): Int {
        val displayMetrics = context.resources.displayMetrics
        val dpWidth = displayMetrics.widthPixels / displayMetrics.density
        return (dpWidth / 180).toInt()
    }

    private fun createSeriesPreview(rootView: View) {
//        val recyclerView: RecyclerView = rootView.findViewById(R.id.seriesPreview)
//        recyclerView.layoutManager = GridLayoutManager(rootView.context, calculateNoOfColumns(rootView.context)) as RecyclerView.LayoutManager?
//        rootView.findViewById<TextView>(R.id.sectionTitleTxt).text = getString(R.string.trending_series)
//        val seriesPreviewAdapter = SeriePreviewAdapter(rootView.context, Series.list)
//        recyclerView.adapter = seriesPreviewAdapter

        val args = arguments
        val type = args!!.getString("type", "all")
        val recyclerView: RecyclerView = rootView.findViewById(R.id.seriesPreview)
        var series = ArrayList<com.handaoui.movies.data.Series>()
        var layoutManager = GridLayoutManager(rootView.context, calculateNoOfColumns(rootView.context))

        when(type){
            "all" ->{
                rootView.findViewById<TextView>(R.id.sectionTitleTxt).visibility = View.GONE
            }
            "bookmark" ->{
                rootView.findViewById<TextView>(R.id.sectionTitleTxt).visibility = View.GONE
            }
            "related" ->{
                layoutManager = GridLayoutManager(rootView.context, 1, GridLayoutManager.HORIZONTAL, false)
                serieId = args.getInt("id")
                rootView.findViewById<View>(R.id.sectionTitleTxt).visibility = View.GONE
            }
        }
        val seriesPreviewAdapter = SeriePreviewAdapter(rootView.context, series)
        loadData(seriesPreviewAdapter, type)
        recyclerView.adapter = seriesPreviewAdapter
        recyclerView.layoutManager = layoutManager

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
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
                    loadData(seriesPreviewAdapter, type)
            }
        })
    }

    private fun loadData(seriesPreviewAdapter: SeriePreviewAdapter, type: String) {
        loading = true
        val seriesCallback = object : Callback<SeriesDto> {
            override fun onResponse(call: Call<SeriesDto>, response: retrofit2.Response<SeriesDto>) {
                loading = false
                val res = response.body()
                if (res?.results != null) seriesPreviewAdapter.addToList(res.results)
            }

            override fun onFailure(call: Call<SeriesDto>, t: Throwable) {
                Log.i("jkhkjhkjhkjhkj", t.toString())
                loading = false
            }
        }

        when (type) {
            "all" -> {
                Api.serieService.getAllSeries(page++).enqueue(seriesCallback)
            }
            "projected" -> {
                Api.serieService.getPlayingSeries(page++).enqueue(seriesCallback)
            }
            "bookmark" -> {
            }
            "related" -> {
                Api.serieService.getSimilarSeries(serieId).enqueue(seriesCallback)
            }
        }
    }

}
