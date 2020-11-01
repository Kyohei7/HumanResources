package com.example.hr.projects

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hr.R
import com.example.hr.databinding.FragmentHomeBinding
import com.example.hr.databinding.FragmentProjectsBinding
import com.example.hr.helper.Constant
import com.example.hr.helper.PreferencesHelper
import com.example.hr.projects.details.DetailsProjectActivity
import com.example.hr.remote.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel


class ProjectFragment : Fragment() {

    private lateinit var binding: FragmentProjectsBinding
    private lateinit var viewModels: ProjectViewModels
    private lateinit var sharePref: PreferencesHelper
    private lateinit var recycleProject: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_projects, container, false)
        sharePref = PreferencesHelper(requireContext())

        val service = ApiClient.getApiClient(activity as AppCompatActivity)?.create(ProjectsApiService::class.java)

        viewModels = ViewModelProvider(this).get(ProjectViewModels::class.java)
        viewModels.setSharePref(sharePref)

        if (service != null) {
            viewModels.setProjectService(service)
        }

        recycleProject = binding.recyclerProject
        recycleProject.adapter = ProjectsAdapter(arrayListOf(), object : ProjectsAdapter.onAdapterListener {
            override fun OnClick(project: ProjectsModel) {
                sharePref.putString(Constant.PREFERENCE_IS_PROJECT, project.id)
                startActivity(Intent(requireContext(), DetailsProjectActivity::class.java))
            }
        })
        recycleProject.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        viewModels.getProjectByidCompany()
        subscribeLiveData()
        return binding.root

    }

    override fun onResume() {
        super.onResume()
        viewModels.getProjectByidCompany()
    }

    private fun subscribeLiveData() {
        viewModels.isProjectResponse.observe(viewLifecycleOwner, Observer {
            (binding.recyclerProject.adapter as ProjectsAdapter).addList(it)
        })
    }
}