package com.example.hr.util

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.hr.R

abstract class BaseActivity : AppCompatActivity() {

    lateinit var mActivity: AppCompatActivity

    override fun onCreate(saveInstanceState: Bundle?) {
        super.onCreate(saveInstanceState)
        mActivity = this
    }

    protected inline fun <reified ClassActivity>
            baseStartActivity(content: Context) {
            content.startActivity(Intent(content, ClassActivity::class.java))
    }

    abstract fun initView()

    abstract fun initListener()

    fun setShortToast(text: String, context: Context = this) = Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    fun setLongToast(text: String, context: Context = this) = Toast.makeText(context, text, Toast.LENGTH_LONG).show()

    fun close() {
        val close = Intent(Intent.ACTION_MAIN)
        close.addCategory(Intent.CATEGORY_HOME)
        close.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(close)
    }

    fun closeWithDialogConfirm() {
        val dialog = AlertDialog.Builder(this)
            .setTitle("Confirm exit!")
            .setMessage("Are you sure?")
            .setCancelable(false)
            .setPositiveButton("Yes") { dialog, id ->
                close()}
            .setNegativeButton("No") {  dialog, id -> }
        dialog.show()
    }

    fun setErrorDialog(title: String?, message: String?) {
        val dialog = AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setCancelable(true)
            .setPositiveButton("Ok") { dialog, id ->
                dialog.dismiss()}
        dialog.show()
    }

    fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)

    class KeyIntent {

        companion object {

            const val EXTRA_FIRST_NAME = "EXTRA_FIRST_NAME"
            const val EXTRA_LAST_NAME = "EXTRA_LAST_NAME"
            const val EXTRA_EMAIL = "EXTRA_EMAIL"
            const val EXTRA_PASSWORD = "EXTRA_PASSWORD"
        }
    }
}