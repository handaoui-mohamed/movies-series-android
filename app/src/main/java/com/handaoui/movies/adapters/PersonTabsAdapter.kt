package com.handaoui.movies.adapters

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.handaoui.movies.fragments.PersonPreviewFragment

class PersonTabsAdapter(fm: FragmentManager, private var tabCount: Int, private var dataId: Int,
                        private var origin : Int) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        return when(origin) {
            0 -> when (position) {
                0, 1 -> createMoviePersonFragment(position)
                else -> null
            }
            1 -> when (position) {
                0, 1 -> createSeriePersonFragment(position)
                else -> null
            }
            else -> null
        }

    }

    private fun createSeriePersonFragment(index: Int) = PersonPreviewFragment().apply {
        arguments = Bundle().apply {
            putString("type", "serie")
            putString("personType", if (index == 0) "actors" else "directors")
            putInt("id", dataId)
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