package com.example.hr.home

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DevelopersPresenter( private val coroutineScope: CoroutineScope,
                           private val service: DevelopersApiService?) : DevelopersContract.Presenter {


    private var view: DevelopersContract.View? = null


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
}