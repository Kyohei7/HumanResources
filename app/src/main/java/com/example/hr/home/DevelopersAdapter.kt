package com.example.hr.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hr.R
import com.example.hr.databinding.ItemCardDeveloperBinding
import com.squareup.picasso.Picasso

class DevelopersAdapter: RecyclerView.Adapter<DevelopersAdapter.DeveloperHolder>() {


    private val items = mutableListOf<DevelopersModel>()

    private fun getPhotoImage(file: String) : String = "http://18.234.106.45:8080/uploads/$file"

    fun addList(list: List<DevelopersModel>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DevelopersAdapter.DeveloperHolder {
        return DeveloperHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_card_developer, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: DeveloperHolder, position: Int) {
        val item = items[position]
        holder.binding.tvNameDeveloper.text = item.name
        holder.binding.tvJobDeveloper.text = item.job
        Picasso.get().load(getPhotoImage(item.photo.toString())).
            into(holder.binding.imgImageDeveloper)

    }

    class DeveloperHolder(val binding: ItemCardDeveloperBinding): RecyclerView.ViewHolder(binding.root)

}