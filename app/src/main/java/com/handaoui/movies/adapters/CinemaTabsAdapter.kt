package com.handaoui.movies.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.handaoui.movies.fragments.PreviewFragment

class CinemaTabsAdapter(fm: FragmentManager, private var tabCount: Int) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return PreviewFragment()
            1 -> return PreviewFragment()
            else -> return null
        }
    }

    override fun getCount() = tabCount
}