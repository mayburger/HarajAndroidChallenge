package com.example.harajtask.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BasePagingViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun onBind(position: Int,data:T)
}