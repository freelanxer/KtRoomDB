package com.freelanxer.ktroomdb.activity

import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity(), View.OnClickListener {

    protected fun showToast(textResId: Int) = showToast(getString(textResId))

    protected fun showToast(text: String?) = Toast.makeText(this, text ?: "", Toast.LENGTH_SHORT).show()

    override fun onClick(view: View?) {

    }

}