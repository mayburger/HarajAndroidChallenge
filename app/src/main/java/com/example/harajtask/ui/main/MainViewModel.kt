package com.example.harajtask.ui.main

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.harajtask.base.BaseNavigator
import com.example.harajtask.base.BaseViewModel
import com.example.harajtask.data.AppDataManager
import com.example.harajtask.data.ProductPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(dataManager: AppDataManager):BaseViewModel<BaseNavigator>(dataManager) {

    val products = Pager(PagingConfig(5)){
        val limit = 5
        ProductPagingSource(dataManager.mApiService, limit)
    }.flow

}