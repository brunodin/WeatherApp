package com.weather.weatherapplication.presentation.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<Binding : ViewDataBinding>: AppCompatActivity() {

    protected lateinit var binding: Binding

    @get:LayoutRes
    abstract var layoutRes: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.lifecycleOwner = this
    }

    protected fun dismissKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (null != currentFocus) {
            imm.hideSoftInputFromWindow(currentFocus!!.applicationWindowToken, 0)
        }
    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun openActivity(
        activity: Class<*>,
        options: Bundle? = null,
        finishWhenOpen: Boolean = false,
        finishStack: Boolean = false
    ) {
        val intent = Intent(this, activity)
        if (options != null) intent.putExtras(options)

        if (finishStack) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        startActivity(intent, options)

        if (finishWhenOpen) (this as Activity).finish()

    }
}