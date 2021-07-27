package com.example.harajtask.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.harajtask.data.room.AppDatabase
import com.example.harajtask.model.Product

class ProductPagingSource(
    val apiService: ApiService,
    val appDatabase: AppDatabase,
    val limit: Int = 10
) : PagingSource<Int, Product>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        // Unused without API
        var lastId:Int?= params.key ?: 0
        return try {
            val products = apiService.getProducts()
            val productResponse = ArrayList<Product>()

            lastId?.let{
                productResponse.add(products[it])
                if (lastId == 0) {
                    appDatabase.productsDao().dropProducts()
                }
                appDatabase.productsDao().putProduct(products[it])
                lastId = lastId?.plus(1)
                if (lastId == products.size){
                    lastId = null
                }
            }

            LoadResult.Page(
                data = productResponse,
                prevKey = null,
                nextKey = lastId
            )
        } catch (e: Exception) {
            if (appDatabase.productsDao().getProducts().isNotEmpty() && lastId == 0) {
                LoadResult.Page(
                    data = appDatabase.productsDao().getProducts(),
                    prevKey = null,
                    nextKey = null
                )
            } else {
                LoadResult.Error(e)
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return 0
    }
}
