package com.example.hr.projects

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
import com.example.hr.projects.edit.EditProjectActivity
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
                  editIntent(ProjectConstant.READ)
                Toast.makeText(requireContext(), "${ProjectConstant.READ}", Toast.LENGTH_SHORT).show()
            }

            override fun onUpdate(project: ProjectsModel) {
                sharePref.putString(Constant.PREFERENCE_IS_PROJECT, project.id)
                editIntent(ProjectConstant.UPDATE)
                Toast.makeText(requireContext(), "${ProjectConstant.UPDATE}", Toast.LENGTH_SHORT).show()
            }

            override fun onDelete(project: ProjectsModel) {
                deleteAlert(project)
            }
        })
        recycleProject.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        viewModels.getProjectByidCompany()
        subscribeLiveData()

        binding.editProject.setOnClickListener {
            editIntent(ProjectConstant.CREATE)
            Toast.makeText(requireContext(), "${ProjectConstant.CREATE}", Toast.LENGTH_SHORT).show()
        }

        return binding.root

    }

    private fun deleteAlert(project: ProjectsModel) {
        val dialog = AlertDialog.Builder(requireContext())
        dialog.apply {
            setTitle("Delete Confirm")
            setMessage("Are You Sure to Delete ${project.name_project}?")
            setNegativeButton("Cancel") { dialog: DialogInterface?, which: Int ->
                dialog?.dismiss()
            }
            setPositiveButton("Delete") { dialog: DialogInterface?, which: Int ->
                viewModels.delete(project.id)
                dialog?.dismiss()
            }
        }
        dialog.show()
    }

    private fun editIntent(type_intent: Int) {
        startActivity(Intent(requireContext(), EditProjectActivity::class.java)
            .putExtra("type_intent", type_intent)
        )
    }

    override fun onResume() {
        super.onResume()
        viewModels.getProjectByidCompany()
    }

    private fun subscribeLiveData() {
        viewModels.isProjectResponse.observe(viewLifecycleOwner, Observer {
            (binding.recyclerProject.adapter as ProjectsAdapter).addList(it)
        })
        viewModels.isDeleteProject.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), "Delete Success", Toast.LENGTH_SHORT).show()
            viewModels.getProjectByidCompany()
        })
    }
}