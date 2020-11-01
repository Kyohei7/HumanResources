package com.example.hr.home
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hr.R
import com.example.hr.databinding.FragmentHomeBinding
import com.example.hr.helper.Constant
import com.example.hr.helper.PreferencesHelper
import com.example.hr.home.details.DetailsDeveloperActivity
import com.example.hr.remote.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel


class HomeFragment : Fragment(){

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var sharePref: PreferencesHelper
    private lateinit var recycleHome : RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        sharePref = PreferencesHelper(requireContext())
        val service = ApiClient.getApiClient(requireContext())?.create(DevelopersApiService::class.java)

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModel.setSharePref(sharePref)
        if (service != null) {
            viewModel.setDeveloperService(service)
        }

        recycleHome = binding.recyclerHome
        recycleHome.adapter = DevelopersAdapter(arrayListOf(), object : DevelopersAdapter.OnAdapterListener {
            override fun OnClick(developer: DevelopersModel) {
                val a = sharePref.putString(Constant.PREFERENCE_IS_ID_DEV, developer.id.orEmpty())
                Log.d("iDDev", "${a}")
                startActivity(Intent(requireContext(), DetailsDeveloperActivity::class.java))
            }
        })
        recycleHome.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        viewModel.callApiDev()
        subscribeLiveData()

        return binding.root

    }

    private fun subscribeLiveData() {
        viewModel.isResponseAdapter.observe(viewLifecycleOwner, Observer {
            (binding.recyclerHome.adapter as DevelopersAdapter).addList(it)
        })
    }
}






















