package com.example.hr.recyclerview

object ProjectsData {

    private var data = arrayOf(
        arrayOf(
            "Name Project",
            "https://d17ivq9b7rppb3.cloudfront.net/small/avatar/202003301623439a1f412086d108f026fec185f4ab3e34.png"
        ),
        arrayOf(
            "Name Project",
            "https://images.pexels.com/photos/415829/pexels-photo-415829.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"
        ),arrayOf(
            "Name Project",
            "https://images.pexels.com/photos/1310522/pexels-photo-1310522.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940"
        ),arrayOf(
            "Name Project",
            ""
        )
    )

    val listData: ArrayList<Project>
        get() {
            val list = ArrayList<Project>()
            for (aData in data) {
                val project = Project()
                project.name = aData[0]
                project.picture = aData[1]


                list.add(project)
            }
            return list
        }

}