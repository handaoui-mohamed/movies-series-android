package com.handaoui.movies.adapters

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.handaoui.movies.fragments.PreviewFragment
import com.handaoui.movies.fragments.ProjectionRoomsFragment
import com.handaoui.movies.fragments.SeriePreviewFragment

class BookmarkTabsAdapter(fm: FragmentManager, private var tabCount: Int) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return PreviewFragment().apply {
                arguments = Bundle().apply {
                    putString("type", "bookmark")
                }
            }
            1 -> return SeriePreviewFragment().apply {
                arguments = Bundle().apply {
                    putString("type", "bookmark")
                }
            }
            2 -> return ProjectionRoomsFragment().apply {
                arguments = Bundle().apply {
                    putString("type", "bookmark")
                }
            }
            else -> return null
        }
    }

    override fun getCount() = tabCount
}