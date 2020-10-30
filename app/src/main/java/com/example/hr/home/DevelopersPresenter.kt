package com.example.hr.home

import android.util.Log
import com.example.hr.helper.Constant
import com.example.hr.helper.PreferencesHelper
import com.example.hr.profile.CompanyByIdUserResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DevelopersPresenter( private val coroutineScope: CoroutineScope,
                           private val service: DevelopersApiService?,
                           private var sharePref: PreferencesHelper) : DevelopersContract.Presenter {


    private var view: DevelopersContract.View? = null
//    private lateinit var sharePref : PreferencesHelper

    override fun bindToView(view: DevelopersContract.View) {
        this.view = view
    }

    override fun unbind() {
        this.view = null
    }


    override fun CallDeveloperApi() {

        coroutineScope.launch {
            view?.hideProgressBar()

            Log.d("Developer", "start : ${Thread.currentThread().name}")

            val response = withContext(Dispatchers.IO) {
                Log.d("Developer", "callApi : ${Thread.currentThread().name}")

                try {
                    service?.getAllDevelopers()
                } catch (e: Throwable) {
                    e.printStackTrace()
                }

            }

            if (response is DevelopersResponse) {

                val list = response.data?.map {
                    DevelopersModel(
                        it.id.orEmpty(),
                        it.photo.orEmpty(),
                        it.name.orEmpty(),
                        it.job.orEmpty(),
                        it.location.orEmpty(),
                        it.status.orEmpty(),
                        it.description.orEmpty(),
                        it.skill.orEmpty(),
                        it.email.orEmpty(),
                        it.instagram.orEmpty(),
                        it.github.orEmpty(),
                        it.gitlab.orEmpty(),
                        it.portfolio.orEmpty(),
                        it.experience.orEmpty(),
                        it.createAt.orEmpty(),
                        it.updateAt.orEmpty()
                    )
                }

                view?.addListDeveloper(list)

            } else if (response is Throwable) {
                Log.e("Developer", response.message ?: "Error")
            }

            view?.hideProgressBar()
            Log.d("Developer", "finish : ${Thread.currentThread().name}")
        }
    }

    override fun setSharePref(sharePref: PreferencesHelper) {
        this.sharePref = sharePref
    }

    fun callCompanyId(id : String) {
        coroutineScope.launch {
            view?.hideProgressBar()
            val response = withContext(Dispatchers.IO) {
                try {
                    service?.getCompanyId(id)
                } catch (e: Throwable) {
                    e.printStackTrace()
                }
            }
            if (response is CompanyByIdUserResponse) {
                sharePref.putString(Constant.PREFERENCE_IS_ID_COMPANY, response.data.idCompany.toString())
                Log.d("android1", "$response")
            }
        }
    }
}