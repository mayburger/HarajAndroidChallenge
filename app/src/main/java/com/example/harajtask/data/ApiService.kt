package com.example.harajtask.data

import com.example.harajtask.base.BaseApplication
import com.example.harajtask.model.Product
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import java.util.concurrent.TimeUnit

interface ApiService {
    companion object Factory {
        fun create(): ApiService {

            val httpClient = OkHttpClient.Builder().apply {
                addInterceptor(
                    ChuckInterceptor(BaseApplication.instance)
                        .showNotification(true)
                )
                connectTimeout(60, TimeUnit.SECONDS)
                readTimeout(60, TimeUnit.SECONDS)
                writeTimeout(60, TimeUnit.SECONDS)
            }

            return Retrofit.Builder()
                .baseUrl("https://run.mocky.io/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
                .create(ApiService::class.java)
        }
    }


    @GET("131f8565-87b1-467b-a756-18fa0375124d")
    suspend fun getProducts(): ArrayList<Product>

}