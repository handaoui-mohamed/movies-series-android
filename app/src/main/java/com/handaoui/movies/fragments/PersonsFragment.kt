package com.handaoui.movies.fragments


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.handaoui.movies.R
import com.handaoui.movies.adapters.PersonTabsAdapter

class PersonsFragment : Fragment() {
    private var dataId = 0
    private lateinit var personTabsAdapter: PersonTabsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_persons, container, false)
        configureTabLayout(rootView)
        return rootView
    }

    private fun configureTabLayout(rootView: View) {
        val tabLayout: TabLayout = rootView.findViewById(R.id.tab_layout)
        val pager: ViewPager = rootView.findViewById(R.id.pager)

        tabLayout.addTab(tabLayout.newTab().setText(resources.getString(R.string.actors)))
        tabLayout.addTab(tabLayout.newTab().setText(resources.getString(R.string.directors)))

        dataId = arguments!!.getInt("id")
        val origin = arguments!!.getInt("origin")

        personTabsAdapter = PersonTabsAdapter(childFragmentManager, tabLayout.tabCount, dataId, origin)
        pager.adapter = personTabsAdapter
        pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                pager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }
}
