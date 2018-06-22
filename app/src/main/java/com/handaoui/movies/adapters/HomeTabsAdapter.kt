package com.handaoui.movies.adapters

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.handaoui.movies.fragments.PreviewFragment
import com.handaoui.movies.fragments.SeriePreviewFragment

class HomeTabsAdapter(fm: FragmentManager, private var tabCount: Int) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> {
                val moviesArgs = Bundle()
                val projectionMoviesFragment = PreviewFragment()
                projectionMoviesFragment.arguments = moviesArgs
                projectionMoviesFragment
            }
            1 -> {
                val moviesArgs = Bundle()
                val airingSeriePreviewFragment = SeriePreviewFragment()
                airingSeriePreviewFragment.arguments = moviesArgs
                airingSeriePreviewFragment
            }
            else -> null
        }
    }

    override fun getCount() = tabCount
}