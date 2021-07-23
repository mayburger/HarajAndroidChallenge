package com.example.harajtask.utils.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object ImageViewBinding {

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun bindImageUrl(view: ImageView, url:String?){
        url?.let{
            Glide.with(view.context).load(it).centerCrop().into(view)
        }
    }

}