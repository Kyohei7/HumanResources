package com.example.hr.projects

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hr.R
import com.example.hr.databinding.ItemCardDeveloperBinding
import com.example.hr.databinding.ItemCardProjectBinding
import com.example.hr.helper.PreferencesHelper
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_card_project.view.*

class ProjectsAdapter(val dataProject: ArrayList<ProjectsModel>, val listener: onAdapterListener): RecyclerView.Adapter<ProjectsAdapter.ProjectHolder>() {

    fun addList(list: List<ProjectsModel>) {
        dataProject.clear()
        dataProject.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProjectsAdapter.ProjectHolder {
        return ProjectHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_card_project, parent, false
        ))
    }

    override fun getItemCount(): Int = dataProject.size

    override fun onBindViewHolder(holder: ProjectsAdapter.ProjectHolder, position: Int) {
        val project = dataProject[position]
        holder.view.tv_name_project.text = project.name_project
        holder.view.tv_job_deadline.text = project.deadline
        Picasso.get().load("http://18.234.106.45:8080/uploads" + project.photo)
            .into(holder.view.img_image_project)

        holder.itemView.setOnClickListener {
            listener.OnClick(project)
        }

        holder.view.edit.setOnClickListener {
            listener.onUpdate(project)
        }

        holder.view.delete.setOnClickListener {
            listener.onDelete(project)
        }

    }

    class ProjectHolder(val view: View): RecyclerView.ViewHolder(view)

    interface onAdapterListener {
        fun OnClick(project: ProjectsModel)
        fun onUpdate(project: ProjectsModel)
        fun onDelete(project: ProjectsModel)
    }
}