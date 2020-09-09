package com.example.hr.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.hr.R

class ListProjectAdapter(val listProject: ArrayList<Project>): RecyclerView.Adapter<ListProjectAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListProjectAdapter.ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_card_projects, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listProject.size

    override fun onBindViewHolder(holder: ListProjectAdapter.ListViewHolder, position: Int) {

        val(name, picture) = listProject[position]
        Glide.with(holder.itemView.context)
            .load(picture)
            .apply(RequestOptions())
            .into(holder.imgPic)

        holder.tvName.text = name
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var imgPic: ImageView = itemView.findViewById(R.id.img_item_image)

    }
}

