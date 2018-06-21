package com.handaoui.movies.fragments


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.handaoui.movies.R
import com.handaoui.movies.adapters.PersonTabsAdapter
import com.handaoui.movies.dtos.CreditsDto
import com.handaoui.movies.services.Api
import retrofit2.Call
import retrofit2.Callback

class PersonsFragment : Fragment() {
    private var dataId = 0
    private var loading = false

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

        if (origin == 0) getMovieCredits() else getSeriesCredits()

        pager.adapter = PersonTabsAdapter(childFragmentManager, tabLayout.tabCount, dataId, origin)
        pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                pager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    private fun getSeriesCredits() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun getMovieCredits() {
        loading = true
        Api.movieService.getMovieCredits(dataId).enqueue(object : Callback<CreditsDto> {
            override fun onResponse(call: Call<CreditsDto>, response: retrofit2.Response<CreditsDto>) {
                loading = false
                val res = response.body()
                Log.i("credits", "id = " + dataId + ", " + res.toString())
                if (res?.crew != null && res.crew.size > 0) {

                }
            }

            override fun onFailure(call: Call<CreditsDto>, t: Throwable) {
                Log.i("Moviescredits", t.toString())
                loading = false
            }
        })
    }
}
