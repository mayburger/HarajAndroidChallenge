package com.example.harajtask.data

import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AppDataManager @Inject constructor(
    val mContext: Context,
    val mApiService: ApiService
){

}