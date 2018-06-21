package com.handaoui.movies.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.handaoui.movies.R
import com.handaoui.movies.adapters.PersonsListAdapter
import com.handaoui.movies.dtos.PersonsDto
import com.handaoui.movies.services.Api
import retrofit2.Call
import retrofit2.Callback


class PersonsListFragment : Fragment() {
    private var loading = true
    private var page = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_preview, container, false)
        this.createPersonsPreview(rootView)
        return rootView
    }

    private fun calculateNoOfColumns(context: Context): Int {
        val displayMetrics = context.resources.displayMetrics
        val dpWidth = displayMetrics.widthPixels / displayMetrics.density
        return (dpWidth / 180).toInt()
    }

    private fun createPersonsPreview(rootView: View) {
        val recyclerView: RecyclerView = rootView.findViewById(R.id.moviesPreview)
        val personsPreviewAdapter = PersonsListAdapter(rootView.context, ArrayList())
        val layoutManager = GridLayoutManager(rootView.context, calculateNoOfColumns(rootView.context))
        recyclerView.adapter = personsPreviewAdapter

        loadData(personsPreviewAdapter)

        recyclerView.layoutManager = layoutManager
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            var pastVisibleItems: Int = 0
            var visibleItemCount: Int = 0
            var totalItemCount: Int = 0

            override fun onScrolled(recyclerView: RecyclerView?,
                                    dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                visibleItemCount = layoutManager.childCount
                totalItemCount = layoutManager.itemCount
                pastVisibleItems = layoutManager.findFirstVisibleItemPosition()

                if ((visibleItemCount + pastVisibleItems >= totalItemCount) && !loading)
                    loadData(personsPreviewAdapter)
            }
        })
    }

    private fun loadData(personsPreviewAdapter: PersonsListAdapter) {
        loading = true
        Api.personService.getAllPersons(page).enqueue(object : Callback<PersonsDto> {
            override fun onResponse(call: Call<PersonsDto>, response: retrofit2.Response<PersonsDto>) {
                loading = false
                val res = response.body()
                if (res?.results != null) {
                    personsPreviewAdapter.addToList(res.results)
                    page++
                }
            }

            override fun onFailure(call: Call<PersonsDto>, t: Throwable) {
                Log.i("Movies Playing", t.toString())
                loading = false
            }
        })
    }

}
