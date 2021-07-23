package com.example.harajtask.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Product(
    var title:String?=null,
    var username:String?=null,
    var thumbURL:String?=null,
    var commentCount:Int?=null,
    var city:String?=null,
    var date:Long?=null,
    var body:String?=null,
): Parcelable