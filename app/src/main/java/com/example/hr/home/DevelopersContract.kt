package com.example.hr.home

interface DevelopersContract {

    interface View {

        fun addListDeveloper(list: List<DevelopersModel>)
        fun showProgressBar()
        fun hideProgressBar()


    }

    interface Presenter {

        fun bindToView(view : View)
        fun unbind()
        fun CallDeveloperApi()

    }

}