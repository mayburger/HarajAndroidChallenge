package com.example.harajtask.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.harajtask.data.ProductPagingSource
import com.example.harajtask.data.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(productRepository: ProductRepository):ViewModel() {

    val isLoading = MutableLiveData(true)

    val products = Pager(PagingConfig(5)){
        val limit = 5
        ProductPagingSource(productRepository, limit)
    }.flow

}