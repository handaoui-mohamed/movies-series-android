package com.handaoui.movies.adapters

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.handaoui.movies.data.Series
import com.handaoui.movies.R
import java.util.ArrayList


class SeriePreviewAdapter(var context: Context,
                           private var seriesList: ArrayList<Series>) :
        RecyclerView.Adapter<SeriePreviewAdapter.CustomViewHolder>() {

    override fun getItemCount() = seriesList.size

    override fun onBindViewHolder(holder: CustomViewHolder?, position: Int) {
        val serie: Series = seriesList[position]
        holder?.imageView?.setImageResource(serie.cover)
        holder?.titleView?.text = serie.title
        holder?.id = serie.id
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CustomViewHolder {
        val view: View = LayoutInflater.from(parent?.context).inflate(R.layout.serie_preview_card, parent, false)
        return CustomViewHolder(view)
    }

    inner class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var id: Int = 0
        var titleView: TextView = view.findViewById(R.id.serieTitleTxt)
        var imageView: ImageView = view.findViewById(R.id.coverImg)
        private var cardView: CardView = view.findViewById(R.id.card_view)

        init {
            cardView.setOnClickListener {
//                val serieDetailsIntent = Intent(context, SerieDetailsActivity::class.java).apply {
//                    putExtra("id", id)
//                }
//                context.startActivity(serieDetailsIntent)
            }
        }
    }
}