package com.freelanxer.ktroomdb.activity

import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.freelanxer.ktroomdb.R
import com.google.android.material.snackbar.Snackbar

abstract class BaseActivity: AppCompatActivity(), View.OnClickListener {

    protected fun showToast(textResId: Int) = showToast(getString(textResId))

    protected fun showToast(text: String?) = Toast.makeText(this, text ?: "", Toast.LENGTH_SHORT).show()

    protected fun showSnackBar(
        contentView: View,
        text: Int,
        actionName: Int = R.string.done,
        action: View.OnClickListener? = null,
    ) {
        showSnackBar(contentView, getString(text), getString(actionName), action)
    }

    protected fun showSnackBar(
        contentView: View,
        text: String,
        actionName: String = getString(R.string.done),
        action: View.OnClickListener? = null,
    ) {
        Snackbar.make(contentView, text, Snackbar.ANIMATION_MODE_SLIDE).setAction(actionName, action).show()
    }

    override fun onClick(view: View?) {

    }

}