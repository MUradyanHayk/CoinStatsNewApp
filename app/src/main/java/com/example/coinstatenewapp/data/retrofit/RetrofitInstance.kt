package com.example.coinstatenewapp.data.retrofit

import com.example.coinstatenewapp.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitInstance {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api:CoinsApi by lazy {
        retrofit.create(CoinsApi::class.java)
    }
}