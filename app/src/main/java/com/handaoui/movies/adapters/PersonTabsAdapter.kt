package com.handaoui.movies.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.handaoui.movies.fragments.PersonPreviewFragment

class PersonTabsAdapter(fm: FragmentManager, private var tabCount: Int, private val dataId: Int) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0, 1 -> createMoviePersonFragment(position)
            else -> null
        }
    }

    private fun createMoviePersonFragment(index: Int) = PersonPreviewFragment().apply {
        arguments.putString("type", "movie")
        arguments.putString("personType", if (index == 0) "actors" else "directors")
        arguments.putInt("id", dataId)
    }


    override fun getCount() = tabCount
}