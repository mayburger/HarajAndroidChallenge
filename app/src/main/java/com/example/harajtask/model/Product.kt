package com.example.harajtask.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "products")
class Product(
    @PrimaryKey(autoGenerate = true)
    val id:Int?=null,
    @ColumnInfo(name = "title")
    var title:String?=null,
    @ColumnInfo(name = "username")
    var username:String?=null,
    @ColumnInfo(name = "thumbURL")
    var thumbURL:String?=null,
    @ColumnInfo(name = "commentCount")
    var commentCount:Int?=null,
    @ColumnInfo(name = "city")
    var city:String?=null,
    @ColumnInfo(name = "date")
    var date:Long?=null,
    @ColumnInfo(name = "body")
    var body:String?=null,
    @ColumnInfo(name = "profile_picture")
    var profile_picture:String?=null
): Parcelable