package com.example.hr.projects

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hr.R
import com.example.hr.databinding.ItemCardDeveloperBinding
import com.example.hr.databinding.ItemCardProjectBinding
import com.example.hr.helper.PreferencesHelper
import com.squareup.picasso.Picasso

class ProjectsAdapter(val items: ArrayList<ProjectsModel>, val listener: OnClickViewListener): RecyclerView.Adapter<ProjectsAdapter.ProjectHolder>() {

    private lateinit var sharePref: PreferencesHelper
    private fun getPhotoImage(file: String) : String = "http://18.234.106.45:8080/uploads/$file"

    fun addList(list: List<ProjectsModel>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProjectsAdapter.ProjectHolder {
        return ProjectHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_card_project, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ProjectsAdapter.ProjectHolder, position: Int) {
        val item = items[position]
        holder.binding.tvNameProject.text = item.name_project
        holder.binding.tvJobDeadline.text = item.deadline
        Picasso.get().load(getPhotoImage(item.photo.toString())).
        into(holder.binding.imgImageProject)
        holder.binding.rvProjects.setOnClickListener {
            listener.OnClick(item.id)
        }
    }

    class ProjectHolder(val binding: ItemCardProjectBinding): RecyclerView.ViewHolder(binding.root)

    interface OnClickViewListener {
        fun OnClick(id: String)
    }
}