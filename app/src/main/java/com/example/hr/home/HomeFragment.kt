package com.example.hr.home
import android.content.Context
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
import com.example.hr.databinding.FragmentHomeBinding
import com.example.hr.remote.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel


class HomeFragment : Fragment(), DevelopersContract.View {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var coroutineScope: CoroutineScope
    private var presenter: DevelopersPresenter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        coroutineScope = CoroutineScope(Job() + Dispatchers.Main)
        val service = ApiClient.getApiClient(this.requireContext())?.create(DevelopersApiService::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)


        binding.recyclerHome.adapter        = DevelopersAdapter()
        binding.recyclerHome.layoutManager  = LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)


        presenter = DevelopersPresenter(coroutineScope, service)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        presenter?.bindToView(this)
        presenter?.CallDeveloperApi()
        Log.d("android1", "call developer api on start")
    }

    override fun onStop() {
        presenter?.unbind()
        super.onStop()
    }

    override fun onDestroy() {
        coroutineScope.cancel()
        presenter = null
        super.onDestroy()
    }

    override fun addListDeveloper(list: List<DevelopersModel>) {

        (binding.recyclerHome.adapter as DevelopersAdapter).addList(list)

    }

    override fun showProgressBar() {
        binding.progressbar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        binding.progressbar.visibility = View.GONE
    }

}






















