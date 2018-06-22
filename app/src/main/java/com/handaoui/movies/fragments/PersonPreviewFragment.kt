package com.handaoui.movies.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.handaoui.movies.R
import com.handaoui.movies.adapters.PersonPreviewAdapter
import com.handaoui.movies.data.Person
import com.handaoui.movies.dtos.CreditsDto
import com.handaoui.movies.fakers.Series
import com.handaoui.movies.services.Api
import retrofit2.Call
import retrofit2.Callback


class PersonPreviewFragment : Fragment() {
    private var dataId = 0
    private var loading = false
    private var credits: CreditsDto? = null
    private val crewTypes = arrayOf("Director", "Producer")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_person_preview, container, false)
        this.createPersonPreview(rootView)
        return rootView
    }

    private fun createPersonPreview(rootView: View) {
        val args = arguments
        val type = args!!.getString("type")
        val forActors = args.getString("personType") == "actors"
        dataId = args.getInt("id")


        val recyclerView: RecyclerView = rootView.findViewById(R.id.personsPreview)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(rootView.context, 2, GridLayoutManager.HORIZONTAL, false)
        val personPreviewAdapter = PersonPreviewAdapter(rootView.context, ArrayList())
        recyclerView.adapter = personPreviewAdapter

        when (type) {
            "movie" -> {
                if (credits != null) {
                    personPreviewAdapter.addToList(if (!forActors) filterCrew(credits!!.crew) else credits!!.cast)
                } else {
                    getMovieCredits(personPreviewAdapter, forActors)
                }
            }
            "serie" -> {
                val serie = Series.getSeriesById(0)
//                persons = if (forActors) serie!!.seasons[id].actors else serie!!.seasons[0].directors
            }
        }
    }

    private fun filterCrew(crewList: ArrayList<Person>): ArrayList<Person> {
        val crew = ArrayList<Person>()
        crew.addAll(crewList.filter { person -> crewTypes.contains(person.job) })
        return crew
    }

    private fun getMovieCredits(personPreviewAdapter: PersonPreviewAdapter, forActors: Boolean) {
        loading = true
        Api.movieService.getMovieCredits(dataId).enqueue(object : Callback<CreditsDto> {
            override fun onResponse(call: Call<CreditsDto>, response: retrofit2.Response<CreditsDto>) {
                loading = false
                credits = response.body()
                Log.i("credits", "id = $dataId")
                if (credits?.crew != null && credits!!.crew.size > 0 && !forActors) {
                    personPreviewAdapter.addToList(filterCrew(filterCrew(credits!!.crew) ))
                }

                if (credits?.cast != null && credits!!.cast.size > 0 && forActors) {
                    personPreviewAdapter.addToList(credits!!.cast)
                }
            }

            override fun onFailure(call: Call<CreditsDto>, t: Throwable) {
                Log.i("Moviescredits", t.toString())
                loading = false
            }
        })
    }
}
