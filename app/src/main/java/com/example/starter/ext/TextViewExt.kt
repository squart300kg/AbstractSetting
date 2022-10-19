package com.example.starter.ext

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("setIntTextToString")
fun TextView.setIntTextToString(intText: Int?) {
    intText?.let {
        this.text = intText.toString()
    }
}

