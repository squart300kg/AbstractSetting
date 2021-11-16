package com.example.starter.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.starter.util.GlideUtil

@BindingAdapter("theWarsOfStar:setImage")
fun ImageView.setImage(thumbnailURL: String) {

    GlideUtil.loadImage(this, thumbnailURL)

}