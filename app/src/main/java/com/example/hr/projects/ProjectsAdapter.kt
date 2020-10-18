package com.example.hr.projects

import android.text.Editable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hr.R
import com.example.hr.databinding.ItemCardDeveloperBinding
import com.example.hr.databinding.ItemCardProjectBinding
import com.squareup.picasso.Picasso

class ProjectsAdapter : RecyclerView.Adapter<ProjectsAdapter.ProjectHolder>() {


    private val items = mutableListOf<ProjectsModel>()

    private fun getPhotoImage(file: String) : String = "http://54.160.226.42:5000/uploads/$file"

    fun addList(list: List<ProjectsModel>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    class ProjectHolder(val binding: ItemCardProjectBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectHolder {

        return ProjectHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_card_project, parent, false))

    }

    override fun onBindViewHolder(holder: ProjectHolder, position: Int) {
        val item = items[position]
        holder.binding.tvNameProject.text = item.name_project
        holder.binding.tvJobDeadline.text = item.deadline
        Picasso.get().load(getPhotoImage(item.photo.toString())).
        into(holder.binding.imgImageProject)
    }

    override fun getItemCount(): Int = items.size

}