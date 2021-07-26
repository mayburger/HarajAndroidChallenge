package com.example.harajtask.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.harajtask.data.room.AppDatabase
import com.example.harajtask.model.Product

class ProductPagingSource(val apiService: ApiService, val appDatabase:AppDatabase, val limit: Int = 10) : PagingSource<Int, Product>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        // Unused without API
        val lastId = params.key
        return try {
            val products = apiService.getProducts()
            val productResponse = ArrayList<Product>()

            if (products.isEmpty()){
                productResponse.addAll(appDatabase.productsDao().getProducts())
            } else{
                productResponse.addAll(products)
                appDatabase.productsDao().dropProducts()
                appDatabase.productsDao().putProducts(products)
            }

            LoadResult.Page(
                data = productResponse,
                prevKey = null,
                nextKey = null
            )
        } catch (e: Exception) {
            if (appDatabase.productsDao().getProducts().isNotEmpty()){
                LoadResult.Page(
                    data = appDatabase.productsDao().getProducts(),
                    prevKey = null,
                    nextKey = null
                )
            } else{
                LoadResult.Error(e)
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return 0
    }
}
