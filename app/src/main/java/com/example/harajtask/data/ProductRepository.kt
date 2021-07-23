package com.example.harajtask.data

import android.content.Context
import com.example.harajtask.model.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ProductRepository(val mContext:Context){

    suspend fun getProducts():List<Product>{
        return suspendCoroutine { suspended->
            mContext.assets.open("data.json").bufferedReader().use {
                suspended.resume(Gson().fromJson(it.readLine(), object:TypeToken<List<Product>>(){}.type))
            }
        }
    }

}