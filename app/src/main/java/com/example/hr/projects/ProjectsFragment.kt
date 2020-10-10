package com.example.hr.projects

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hr.R
import com.example.hr.databinding.FragmentProjectsBinding
import com.example.hr.remote.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel


class ProjectFragment : Fragment(), ProjectsContract.View {

    private lateinit var binding: FragmentProjectsBinding
    private lateinit var coroutineScope: CoroutineScope
    private var presenter: ProjectsPresenter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        coroutineScope = CoroutineScope(Job() + Dispatchers.Main)
        val service = ApiClient.getApiClient(this.requireContext())?.create(ProjectsApiService::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_projects, container, false)

        binding.recyclerProject.adapter = ProjectsAdapter()
        binding.recyclerProject.layoutManager = LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)

        presenter = ProjectsPresenter(coroutineScope, service)
        return binding.root

    }

    override fun onStart() {
        super.onStart()
        presenter?.bindToView(this)
        presenter?.callProjectApi()
        Log.d("android1", "call project api on start")
    }

    override fun onStop() {
        presenter?.unBind()
        super.onStop()
    }

    override fun onDestroy() {
        coroutineScope.cancel()
        presenter = null
        super.onDestroy()
    }


    override fun addListProject(list: List<ProjectsModel>) {

        (binding.recyclerProject.adapter as ProjectsAdapter).addList(list)

    }

    override fun showProgressBar() {
        binding.progressbar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        binding.progressbar.visibility = View.GONE
    }

}