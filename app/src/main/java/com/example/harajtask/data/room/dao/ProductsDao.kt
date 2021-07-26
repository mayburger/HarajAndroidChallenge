package com.example.harajtask.data.room.dao

import com.example.harajtask.model.Product
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductsDao {
    @Query("SELECT * FROM products")
    fun getProducts(): List<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun putProducts(products:List<Product>)

    @Query("DELETE FROM products")
    suspend fun dropProducts()
}