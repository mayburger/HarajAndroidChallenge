package com.example.harajtask.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.harajtask.data.room.dao.ProductsDao
import com.example.harajtask.model.Product

@Database(entities = [Product::class],
    version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productsDao(): ProductsDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "haraj")
                    // No Migrations needed at the moment
                .fallbackToDestructiveMigration()
                .build()
    }
}