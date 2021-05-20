package com.ahmedtikiwa.felinelove.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.ahmedtikiwa.felinelove.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import timber.log.Timber

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    val requestOptions = RequestOptions()
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)

    try {
        Glide.with(imageView.context)
            .load(url)
            .placeholder(R.drawable.loading_list_placeholder)
            .error(R.drawable.loading_list_placeholder)
            .fallback(R.drawable.loading_list_placeholder)
            .apply(requestOptions)
            .into(imageView)
    } catch (e: Exception) {
        Timber.d(e)
    }
}