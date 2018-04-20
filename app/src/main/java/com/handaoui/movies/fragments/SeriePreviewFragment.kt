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


class SeriePreviewFragment : Fragment() {

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

        when(type){
            "all" ->{
                recyclerView.layoutManager = GridLayoutManager(rootView.context, calculateNoOfColumns(rootView.context))
                rootView.findViewById<TextView>(R.id.sectionTitleTxt).visibility = View.GONE
                series = Series.list
            }
            "related" ->{
                recyclerView.layoutManager = GridLayoutManager(rootView.context, 1, GridLayoutManager.HORIZONTAL, false)
                val movieId = args.getInt("id")
                rootView.findViewById<View>(R.id.sectionTitleTxt).visibility = View.GONE
                series = Series.getRelatedSeriess(movieId)
            }
        }

        val seriesPreviewAdapter = SeriePreviewAdapter(rootView.context, series)
        recyclerView.adapter = seriesPreviewAdapter
    }
}
