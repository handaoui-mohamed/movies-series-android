package com.handaoui.movies.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import com.handaoui.movies.R
import com.handaoui.movies.activities.EpisodePreviewActivity
import com.handaoui.movies.activities.SeasonPreviewActivity
import com.handaoui.movies.activities.SerieDetailsActivity
import com.handaoui.movies.data.SeasonEpisode
import com.handaoui.movies.data.SeriesSeason

class EpisodesListAdapter(private val context: Context, private val episodes: ArrayList<SeasonEpisode>, private val season_number: Int,private val serieId: Int) :
        RecyclerView.Adapter<EpisodesListAdapter.ViewHolder>() {

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        var name: TextView = view.findViewById(R.id.name)
        var frame : FrameLayout = view.findViewById(R.id.episode_item_frame)
        var id : Int = 0
        init{
            frame.setOnClickListener {
                val episode = episodes.find{s -> s.id == id}
                val serieDetailsIntent = Intent(context, EpisodePreviewActivity::class.java).apply {
                    putExtra("serieId", serieId)
                    putExtra("air_date", episode!!.air_date)
                    putExtra("season_number", episode.season_number)
                    putExtra("episode_number", episode.episode_number)
//                    putExtra("crew", episode.crew)
                    putExtra("name", episode.name)
                    putExtra("overview", episode.overview)

//                    putExtra("name", season.name)
//                    putExtra("episodes", season.episodes)
//                    putExtra("overview", season.overview)
                    putExtra("still_path", episode.still_path)
                    putExtra("vote_average", episode.vote_average)
                }
                context.startActivity(serieDetailsIntent)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): EpisodesListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.episodeslist_item, parent, false) as View
        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = """Episode ${position + 1} : """ + episodes[position].name
        holder.id = episodes[position].id
    }

    override fun getItemCount() = episodes.size
}