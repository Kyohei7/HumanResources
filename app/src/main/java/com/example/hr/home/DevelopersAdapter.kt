package com.example.hr.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hr.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_card_developer.view.*

class DevelopersAdapter(val dataDev: ArrayList<DevelopersModel>, val listener: OnAdapterListener): RecyclerView.Adapter<DevelopersAdapter.DeveloperHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DevelopersAdapter.DeveloperHolder {
        return DeveloperHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_card_developer, parent, false))
    }

    override fun getItemCount(): Int = dataDev.size

    override fun onBindViewHolder(holder: DeveloperHolder, position: Int) {
        val developer = dataDev[position]
        Picasso.get().load("http://18.234.106.45:8080/uploads/" + developer.photo).into(holder.view.img_image_developer)
        holder.view.tv_name_developer.text = developer.name
        holder.view.tv_job_developer.text = developer.job
        holder.view.img_image_developer.setOnClickListener {
            listener.OnClick(developer)
        }
    }

    class DeveloperHolder(val view: View): RecyclerView.ViewHolder(view)

    fun addList(newList: List<DevelopersModel>) {
        dataDev.clear()
        dataDev.addAll(newList)
        notifyDataSetChanged()
    }


    interface OnAdapterListener {
        fun OnClick(developer: DevelopersModel)
    }

}