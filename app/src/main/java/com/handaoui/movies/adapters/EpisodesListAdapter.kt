package com.handaoui.movies.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import com.handaoui.movies.R
import com.handaoui.movies.activities.SeasonPreviewActivity
import com.handaoui.movies.activities.SerieDetailsActivity
import com.handaoui.movies.data.SeriesSeason

class SeasonsListAdapter(private val context: Context, private val seasons: ArrayList<SeriesSeason>) :
        RecyclerView.Adapter<SeasonsListAdapter.ViewHolder>() {

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        var name: TextView = view.findViewById(R.id.name)
        var frame : FrameLayout = view.findViewById(R.id.season_item_frame)
        var id : Int = 0
        init{
            frame.setOnClickListener {
                val serieDetailsIntent = Intent(context, SeasonPreviewActivity::class.java).apply {
                    putExtra("id", id)
                    putExtra("seasonId", 0)
                }
                context.startActivity(serieDetailsIntent)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): SeasonsListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.seasonlist_item, parent, false) as View
        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = """Season ${position + 1}"""
        holder.id = seasons[position].id
    }

    override fun getItemCount() = seasons.size
}