package com.app.lorempiscum.utils

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener

@BindingAdapter("imageUrl", "progressBar")
fun AppCompatImageView.setImageUrl(url: String?, progressBar: ProgressBar) {
    Glide.with(context).load(url)
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: com.bumptech.glide.request.target.Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                progressBar.visibility = View.GONE
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: com.bumptech.glide.request.target.Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                progressBar.visibility = View.GONE
                return false
            }
        })
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .centerCrop()
        .into(this)
}