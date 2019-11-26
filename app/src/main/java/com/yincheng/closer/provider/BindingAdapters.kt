package com.yincheng.closer.provider

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.yincheng.provider.cache.GlideCacheUtil.getCachedFile

/**
 * 不能用class包起来
 */
@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: AppCompatImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        // TODO() DrawableTransitionOptions.withCrossFade()
        Glide.with(view.context)
            .load(getCachedFile(view.context, imageUrl) ?: imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}

// todo Boolean也能为空？
@BindingAdapter("isFabGone")
fun bindIsFabGone(view: FloatingActionButton, isGone: Boolean?) {
    if (isGone == null || isGone) view.hide()
    else view.show()
}