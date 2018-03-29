package com.handaoui.movies.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.handaoui.movies.R

class HomeFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater!!.inflate(R.layout.fragment_home, container, false)
        val moviesArgs = Bundle()
        moviesArgs.putString("TYPE", "MOVIES")
        val projectionMoviesFragment = PreviewFragment()
        projectionMoviesFragment.arguments = moviesArgs

        val seriesArgs = Bundle()
        seriesArgs.putString("TYPE", "SERIES")
        val currentSeriesFragment = PreviewFragment()
        currentSeriesFragment.arguments = seriesArgs

        val fragmentManager = childFragmentManager
        fragmentManager
                .beginTransaction()
                .replace(R.id.moviesInProjectionLayout, projectionMoviesFragment, projectionMoviesFragment.tag)
                .commit()

        fragmentManager
                .beginTransaction()
                .replace(R.id.currentSeriesLayout, currentSeriesFragment, currentSeriesFragment.tag)
                .commit()

        return rootView
    }

}
