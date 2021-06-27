package com.weather.weatherapplication.presentation.util

import android.app.Dialog
import android.content.Context
import android.view.View
import dagger.hilt.android.qualifiers.ActivityContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoadingHelper @Inject constructor(@ActivityContext val context: Context) {

        private var dialog: Dialog = AlertHelper.loadingDialog(context)


        fun stopLoading() {
            CoroutineScope(Dispatchers.Main).launch {
                dialog.dismiss()
            }
        }

        fun startLoading() {
            CoroutineScope(Dispatchers.Main).launch {
                if (!dialog.isShowing) {
                    dialog.show()
                }
            }
        }
    }