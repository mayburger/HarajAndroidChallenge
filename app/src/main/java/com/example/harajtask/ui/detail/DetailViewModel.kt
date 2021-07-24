package com.example.harajtask.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.harajtask.model.Product
import java.text.SimpleDateFormat
import java.util.*

class DetailViewModel: ViewModel() {

    val product = MutableLiveData<Product>()
    val timestamp = MutableLiveData<String>()
    var navigator:DetailNavigator? = null

    fun setProduct(product:Product){
        this.product.value = product
        val cal = Calendar.getInstance(Locale.ENGLISH)
        cal.timeInMillis = product.date?.times(1000) ?: 0
        this.timestamp.value = SimpleDateFormat("yyyy-MM-dd hh:mma").format(cal.time)
    }

    fun onBack(){
        navigator?.onBack()
    }

}