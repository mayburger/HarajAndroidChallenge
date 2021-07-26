package com.example.harajtask.utils.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object ImageViewBinding {

    @BindingAdapter(value=["imageUrl","imageUrlCircle"], requireAll = false)
    @JvmStatic
    fun bindImageUrl(view: ImageView, url:String?, imageUrlCircle:String?){
        url?.let{
            Glide.with(view.context).load(it).centerCrop().into(view)
        }
        imageUrlCircle?.let {
            Glide.with(view.context).load(it).centerCrop().circleCrop().into(view)
        }
    }

}