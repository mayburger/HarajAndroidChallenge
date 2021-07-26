package com.example.harajtask.utils.ext

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.vertical(){
    layoutManager = LinearLayoutManager(context)
}

fun RecyclerView.horizontal(){
    layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
}

fun RecyclerView.grid2(){
    layoutManager = GridLayoutManager(context, 2)
}

fun RecyclerView.grid3(){
    layoutManager = GridLayoutManager(context, 3)
}