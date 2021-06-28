package com.weather.weatherapplication.presentation.util

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.text.Html
import android.view.Gravity
import android.view.View
import android.view.Window
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.weather.weatherapplication.R

class AlertHelper {
    companion object {
        fun alert(ctx: Context, title: String, message: String): AlertDialog {
            val alertDialogBuilder = AlertDialog.Builder(ctx)
            val alertDialog = alertDialogBuilder.create()
            alertDialog.setTitle(title)
            alertDialog.setMessage(Html.fromHtml(message, Html.FROM_HTML_MODE_COMPACT))
            alertDialog.setCancelable(false)
            return alertDialog
        }

        fun loadingDialog(ctx: Context): Dialog {
            val loading = Dialog(ctx)
            loading.requestWindowFeature(Window.FEATURE_NO_TITLE)
            loading.setContentView(R.layout.dialog_loading)
            loading.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            loading.setCanceledOnTouchOutside(false)
            loading.setCancelable(false)
            return loading
        }

        fun snackBar(parentLayout: View, message: String) {
            val snackBar = Snackbar.make(parentLayout, message, Snackbar.LENGTH_LONG)
            snackBar.setBackgroundTint(Color.RED)
            val snackBarLayout = snackBar.view
            val snackBarTextView =
                snackBarLayout.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
            snackBarTextView.typeface = Typeface.DEFAULT_BOLD
            snackBarTextView.setTextColor(Color.WHITE)
            snackBarLayout.textAlignment = View.TEXT_ALIGNMENT_GRAVITY
            snackBarTextView.gravity = Gravity.CENTER_HORIZONTAL
            snackBar.show()
        }
    }
}