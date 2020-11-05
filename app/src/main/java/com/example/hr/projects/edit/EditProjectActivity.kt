package com.example.hr.projects.edit

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.hr.R
import com.example.hr.databinding.ActivityEditProjectBinding
import com.example.hr.helper.Constant
import com.example.hr.helper.PreferencesHelper
import com.example.hr.projects.ProjectConstant
import com.example.hr.projects.ProjectsApiService
import com.example.hr.remote.ApiClient
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_edit_project.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class EditProjectActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProjectBinding
    private lateinit var sharePref: PreferencesHelper
    private lateinit var viewModel: EditProjectViewModel
//    private lateinit var recyclerView: RecyclerView

    private var photo: MultipartBody.Part? = null

    companion object {
        private val IMAGE_PICK_CODE = 1000
        val PERMISSION_CODE = 1001
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_project)
        val service = ApiClient.getApiClient(this)?.create(ProjectsApiService::class.java)
        viewModel = ViewModelProvider(this).get(EditProjectViewModel::class.java)
        sharePref = PreferencesHelper(applicationContext)

        if (service != null) {
            viewModel.setEditProjectService(service)
        }

        viewModel.setSharePref(sharePref)


        binding.btnEdit.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    val permissions = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permissions, PERMISSION_CODE)
                } else {
                    pickImgGallery()
                }
            } else {
                pickImgGallery()
            }
        }

        viewSetup()
        setupListener()
        subscribeLiveData()

    }

    private fun viewSetup() {
        when(typeIntent()) {
            ProjectConstant.CREATE -> {
//                supportActionBar!!.title = "Create New Project"
                binding.btnPost.visibility = View.VISIBLE
                binding.btnUpdate.visibility = View.GONE
                binding.btnEdit.visibility = View.VISIBLE
            }
            ProjectConstant.READ -> {
//                supportActionBar!!.title = "Read Only"
                binding.btnPost.visibility = View.GONE
                binding.btnUpdate.visibility = View.GONE
                binding.btnEdit.visibility = View.GONE
                viewModel.getDataProject()
            }
            ProjectConstant.UPDATE -> {
//                supportActionBar!!.title = "Edit"
                binding.btnPost.visibility = View.GONE
                binding.btnUpdate.visibility = View.VISIBLE
                viewModel.getDataProject()
            }
        }
    }

    private fun typeIntent(): Int {
        return intent.getIntExtra("type_intent", 0)
    }

    private fun setupListener() {
        btn_post.setOnClickListener {
            val idCompany = sharePref.getString(Constant.PREFERENCE_IS_ID_COMPANY)
            viewModel.postDataProject(
                createPartFromString("$idCompany"),
                createPartFromString(binding.nameProject.text.toString()),
                createPartFromString(binding.description.text.toString()),
                createPartFromString(binding.deadline.text.toString()),
                photo
            )
        }
        btn_update.setOnClickListener {
            val idCompany = sharePref.getString(Constant.PREFERENCE_IS_ID_COMPANY)
            Log.d("IDCOMPANY", "$idCompany")
            viewModel.putDataProject(
                createPartFromString("$idCompany"),
                createPartFromString(binding.nameProject.text.toString()),
                createPartFromString(binding.description.text.toString()),
                createPartFromString(binding.deadline.text.toString()),
                photo
            )
        }
    }

    fun subscribeLiveData() {
        viewModel.isResponseGetProject.observe(this, Observer {
            binding.nameProject.setText(it.data?.nameProject)
            binding.description.setText(it.data?.description)
            binding.deadline.setText(it.data?.deadline)
            Picasso.get().load("http://18.234.106.45:8080/uploads/" + it.data?.photo)
            .into(binding.circleImageView)
        })
        viewModel.isResponsePostProject.observe(this, Observer {
            Toast.makeText(this@EditProjectActivity, "Add Project Success!", Toast.LENGTH_SHORT).show()
            finish()
        })
        viewModel.isResponsePutProject.observe(this, Observer {
            if (it) {
                Toast.makeText(this@EditProjectActivity, "Update Project Success!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this@EditProjectActivity, "Update Project Failed!", Toast.LENGTH_SHORT).show()
            }
        })
    }


    private fun pickImgGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImgGallery()
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    fun getPath(context: Context, uri: Uri?): String {
        var result: String? = null
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val cursor: Cursor? =
            uri?.let { context.contentResolver.query(it, proj, null, null, null) }
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                val column_index = cursor.getColumnIndexOrThrow(proj[0])
                result = cursor.getString(column_index)
            }
            cursor.close()
        }
        if (result == null) {
            result = "Not found"
        }
        return result
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            binding.circleImageView.setImageURI(data?.data)
            val filePath = getPath(this, data?.data)
            val file = File(filePath)

            val mediaTypeImg = "image/jpeg".toMediaType()
            val inputStream = contentResolver.openInputStream(data?.data!!)
            val reqFile: RequestBody? = inputStream?.readBytes()?.toRequestBody(mediaTypeImg)
            photo = reqFile?.let { it1 ->
                MultipartBody.Part.createFormData(
                    "photo", file.name,
                    it1
                )
            }
        }
    }


    @NonNull
    private fun createPartFromString(json: String): RequestBody {
        val mediaType = "multipart/form-data".toMediaType()
        return json
            .toRequestBody(mediaType)
    }
}





