package com.honnalmanja.kotlinlisttask.utils

import android.content.Context
import androidx.appcompat.widget.AppCompatImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.honnalmanja.kotlinlisttask.R

fun getProgressDrawable(context: Context) : CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}

fun AppCompatImageView.loadImage(url: String?, progressDrawable: CircularProgressDrawable ){
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.drawable.ic_baseline_broken_image_24)
        Glide.with(this.context)
            .setDefaultRequestOptions(options)
            .load(url).into(this)
}