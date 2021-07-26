package com.example.harajtask.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.harajtask.model.Product

class ProductPagingSource(val apiService: ApiService, val limit: Int = 10) : PagingSource<Int, Product>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        // Unused without API
        val lastId = params.key
        return try {
            val feedResponse: List<Product> = apiService.getProducts()

            LoadResult.Page(
                data = feedResponse,
                prevKey = null,
                nextKey = null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return 0
    }
}
