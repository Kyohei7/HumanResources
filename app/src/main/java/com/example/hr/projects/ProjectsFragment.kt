package com.example.hr.projects

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hr.R
import com.example.hr.recyclerview.ListProjectAdapter
import com.example.hr.recyclerview.Project
import com.example.hr.recyclerview.ProjectsData
import kotlinx.android.synthetic.main.fragment_projects.*

class ProjectsFragment : Fragment() {

    private lateinit var rvProjects: RecyclerView
    private var list: ArrayList<Project> = arrayListOf()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_projects, container, false)

        rvProjects = rootView.findViewById(R.id.rv_project) as RecyclerView
        rvProjects.setHasFixedSize(true)

        list.addAll(ProjectsData.listData)
        RecyclerViewList()
        return rootView
    }

    private fun RecyclerViewList() {
        rvProjects.layoutManager = LinearLayoutManager(activity)
        val listProjectAdapter = ListProjectAdapter(list)
        rvProjects.adapter = listProjectAdapter
    }

}
