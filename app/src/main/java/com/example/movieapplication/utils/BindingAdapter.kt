package com.example.movieapplication.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.movieapplication.R

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("text")
    fun TextView.text(text: Int) {
        this.text = text.toString()
    }

    @JvmStatic
    @BindingAdapter("bind:imageUrl")
    fun loadImage(imageView: ImageView, imageUrl: String?) {
        imageUrl?.run {
            Glide.with(imageView.context)
                .load(imageUrl)
                .error(R.drawable.ic_launcher_foreground)// will be displayed if the image cannot be loaded
                .fallback(R.drawable.ic_launcher_foreground)// will be displayed if the image url is null
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .override(imageView.width, imageView.height)
                .into(imageView)
        }
    }
}