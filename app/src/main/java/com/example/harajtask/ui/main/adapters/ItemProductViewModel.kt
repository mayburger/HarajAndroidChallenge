package com.example.harajtask.ui.main.adapters

import com.example.harajtask.model.Product
import com.google.gson.internal.bind.util.ISO8601Utils
import org.ocpsoft.prettytime.PrettyTime
import java.util.*

class ItemProductViewModel(val data:Product){

    fun getTime():String{
        val pretty = PrettyTime(Locale.getDefault())
        val cal = Calendar.getInstance(Locale.ENGLISH)
        cal.timeInMillis = data.date?.times(1000) ?: 0
        return pretty.format(cal)
    }

    fun getComments():String{
        return "(${data.commentCount})"
    }

}