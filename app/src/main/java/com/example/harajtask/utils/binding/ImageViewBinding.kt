package com.example.harajtask.utils.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object ImageViewBinding {

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun bindImageUrl(view: ImageView, url:String){
        Glide.with(view.context).load(url).centerCrop().into(view)
    }

}