package com.example.hr.projects

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProjectsPresenter(private val coroutineScope: CoroutineScope,
                        private val service: ProjectsApiService? ) : ProjectsContract.Presenter {


    private var view : ProjectsContract.View? = null

    override fun bindToView(view: ProjectsContract.View) {
       this.view = view
    }

    override fun unBind() {
        this.view = view
    }

    override fun callProjectApi() {

        coroutineScope.launch {
            view?.hideProgressBar()

            Log.d("Project", "start : ${Thread.currentThread().name}")

            val response = withContext(Dispatchers.IO) {
                Log.d("Project", "callApi : ${Thread.currentThread().name}")

                try {
                    service?.getAllProjectData()
                } catch (e: Throwable) {
                    e.printStackTrace()
                }

            }

            if (response is ProjectResponse) {

                val list = response.data?.map {
                    ProjectsModel(
                        it.id.orEmpty(),
                        it.name.orEmpty(),
                        it.description.orEmpty(),
                        it.deadline.orEmpty(),
                        it.id_company.orEmpty(),
                        it.photo.orEmpty(),
                        it.createAt.orEmpty(),
                        it.updateAt.orEmpty()
                    )
                }

                view?.addListProject(list)

            } else if (response is Throwable) {
                Log.e("Developer", response.message ?: "Error")
            }

            view?.hideProgressBar()
            Log.d("Developer", "finish : ${Thread.currentThread().name}")

        }
    }
}





