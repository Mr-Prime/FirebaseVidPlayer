package com.omega.firebasevidplayer.ui.VideosHome.adapter

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.omega.firebasevidplayer.R
import com.omega.firebasevidplayer.listener.RecyclerViewCallBack
import com.omega.firebasevidplayer.model.response.HomeResponse
import kotlinx.android.synthetic.main.item_video.view.*


class HomeAdapter( val context: Context) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    var itemList: List<HomeResponse> = arrayListOf()
    private lateinit var listener: RecyclerViewCallBack

    fun setOnDataRecyclerViewListener(listener: RecyclerViewCallBack) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_video, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        val width: Int = Resources.getSystem().displayMetrics.widthPixels

        (holder as ViewHolder).bind(item,context,width)


    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(
            video: HomeResponse?,
            context: Context,
            width: Int
        ){
            itemView.title.text = video?.title
            itemView.desc.text = video?.description

            Glide.with(itemView.context)
                .load(video?.thumb)
                .override(width/3,width/3)
                .into(itemView.thumb)

            itemView.setOnClickListener {
                listener.onClickItem(position)
            }
        }


    }
}