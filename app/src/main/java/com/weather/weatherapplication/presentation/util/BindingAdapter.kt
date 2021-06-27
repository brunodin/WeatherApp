package com.weather.weatherapplication.presentation.util

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.weather.weatherapplication.R

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("android:activeButton")
    fun activeButton(button: MaterialButton, active: ObservableField<Boolean>) {
        val (buttonColor, textColor) = if (active.get() == true) {
            Pair(R.color.active_button, R.color.white)
        } else {
            Pair(R.color.inactive_button, R.color.black)
        }
        button.setBackgroundColor(
            ContextCompat.getColor(
                button.context,
                buttonColor
            )
        )
        button.setTextColor(
            ContextCompat.getColor(
                button.context,
                textColor
            )
        )
    }

    @JvmStatic
    @BindingAdapter("android:arrowBackClick")
    fun arrowBackClick(toolbar: MaterialToolbar, func: ()->Unit) {
        toolbar.setNavigationOnClickListener {
            func()
        }
    }

    @JvmStatic
    @BindingAdapter("android:imageIcon")
    fun imageIcon(image: ImageView, icon: String?) {
        icon?.let {
            val iconResource = getIconFromCode(it)
            image.setImageResource(iconResource)
        }
    }

    private fun getIconList(): ArrayList<Pair<Int, ArrayList<String>>> {
        return arrayListOf(
            Pair(
                R.drawable.t01d,
                arrayListOf(
                    "t01d",
                    "t02d",
                    "t03d"
                )
            ),
            Pair(
                R.drawable.t01n,
                arrayListOf(
                    "t01n",
                    "t02n",
                    "t03n"
                )
            ),
            Pair(
                R.drawable.t04d,
                arrayListOf(
                    "t04d",
                    "t05d"
                )
            ),
            Pair(
                R.drawable.t04n,
                arrayListOf(
                    "t04n",
                    "t05n"
                )
            ),
            Pair(
                R.drawable.d01d,
                arrayListOf(
                    "d01d",
                    "d01n",
                    "d02d",
                    "d02n",
                    "d03d",
                    "s02d",
                    "s02n",
                    "s03d",
                    "s03n",
                    "s06d",
                    "s06n",
                    "d03n"
                )
            ),
            Pair(
                R.drawable.r01d,
                arrayListOf(
                    "r01d",
                    "r01n",
                    "r02d",
                    "r02n",
                    "r03d",
                    "r03n",
                    "f01d",
                    "f01n",
                    "r04d",
                    "u00d",
                    "r04n",
                    "r06d"
                )
            ),
            Pair(
                R.drawable.r04n,
                arrayListOf(
                    "r04n",
                    "r05n",
                    "r06n"
                )
            ),
            Pair(
                R.drawable.r05d,
                arrayListOf(
                    "r05d"
                )
            ),
            Pair(
                R.drawable.s01d,
                arrayListOf(
                    "s01d",
                    "s04d",
                    "s01d"
                )
            ),
            Pair(
                R.drawable.s01n,
                arrayListOf(
                    "s01n",
                    "s04n"
                )
            ),
            Pair(
                R.drawable.s05d,
                arrayListOf(
                    "s05d",
                    "s05n"
                )
            ),
            Pair(
                R.drawable.a01d,
                arrayListOf(
                    "a01d",
                    "a03d",
                    "a04d",
                    "a05d",
                    "a06d",
                    "a02d"
                )
            ),
            Pair(
                R.drawable.a01n,
                arrayListOf(
                    "a01n",
                    "a03n",
                    "a04n",
                    "a05n",
                    "a06n",
                    "a02n"
                )
            ),
            Pair(
                R.drawable.c01d,
                arrayListOf(
                    "c01d"
                )
            ),
            Pair(
                R.drawable.c01n,
                arrayListOf(
                    "c01n"
                )
            ),
            Pair(
                R.drawable.c02d,
                arrayListOf(
                    "c02d"
                )
            ),
            Pair(
                R.drawable.c02n,
                arrayListOf(
                    "c02n"
                )
            ),
            Pair(
                R.drawable.c03d,
                arrayListOf(
                    "c03d"
                )
            ),
            Pair(
                R.drawable.c03n,
                arrayListOf(
                    "c03n"
                )
            ),
            Pair(
                R.drawable.c04d,
                arrayListOf(
                    "c04d",
                    "c04n"
                )
            ),
        )
    }

    fun getIconFromCode(code: String?): Int {
        val list = getIconList()
        list.forEach {
            val valid = validateItemCode(it, code)
            if (!valid.isNullOrEmpty()) {
                return it.first
            }
        }
        return 0
    }

    private fun validateItemCode(
        it: Pair<Int, ArrayList<String>>,
        code: String?
    ): List<String> {
        return it.second.filter { imageCode -> imageCode == code.toString() }
    }
}