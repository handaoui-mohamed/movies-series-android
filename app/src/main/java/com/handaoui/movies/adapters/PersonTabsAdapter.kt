package com.handaoui.movies.adapters

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.handaoui.movies.fragments.PersonPreviewFragment

class PersonTabsAdapter(fm: FragmentManager, private var tabCount: Int, private var dataId: Int) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0, 1 -> createMoviePersonFragment(position)
            else -> null
        }
    }

    private fun createMoviePersonFragment(index: Int) = PersonPreviewFragment().apply {
        arguments = Bundle().apply {
            putString("type", "movie")
            putString("personType", if (index == 0) "actors" else "directors")
            putInt("id", dataId)
        }
    }


    override fun getCount() = tabCount
}