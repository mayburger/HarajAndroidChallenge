package com.example.harajtask.di

import android.app.Activity
import com.example.harajtask.ui.main.adapters.ProductAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object RecyclerModule {

    @Provides
    internal fun provideProductAdapter(activity: Activity): ProductAdapter {
        return ProductAdapter(activity)
    }

}