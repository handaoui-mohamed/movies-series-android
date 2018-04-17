package com.handaoui.movies.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.handaoui.movies.R


class HomeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater!!.inflate(R.layout.fragment_home, container, false)

        val fragmentTransaction = childFragmentManager.beginTransaction()
        loadMoviesFragment(fragmentTransaction)
        loadSeriesFragment(fragmentTransaction)
        fragmentTransaction.commit()
        return rootView
    }

    private fun loadMoviesFragment(fragmentTransaction: FragmentTransaction){
        val moviesArgs = Bundle()
        val projectionMoviesFragment = PreviewFragment()
        projectionMoviesFragment.arguments = moviesArgs

        fragmentTransaction.replace(R.id.moviesInProjectionLayout, projectionMoviesFragment, projectionMoviesFragment.tag)
    }

    private fun loadSeriesFragment(fragmentTransaction: FragmentTransaction){}
}
