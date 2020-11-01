package com.example.hr.profile
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.hr.R
import com.example.hr.databinding.FragmentProfileBinding
import com.example.hr.helper.PreferencesHelper
import com.example.hr.remote.ApiClient

class ProfileFragment : Fragment() {


    private lateinit var binding: FragmentProfileBinding
    private lateinit var sharePref: PreferencesHelper
    private lateinit var viewModel: GetProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater)
        sharePref = PreferencesHelper(requireContext())
        val service = ApiClient.getApiClient(requireContext())?.create(ProfileApiService::class.java)
        viewModel = ViewModelProvider(this).get(GetProfileViewModel::class.java)
        viewModel.setSharePref(sharePref)

        if (service != null) {
            viewModel.setServiceProfile(service)
        }

        viewModel.getApiCompanyProfile()

        subscribeLiveData()

        return binding.root

    }

    private fun subscribeLiveData() {
        viewModel.isResponseGetProfile.observe(viewLifecycleOwner, Observer {
            binding.nameCompany.text = it.data.nameCompany
            binding.profileSector.text = it.data.sector
        })
    }

}