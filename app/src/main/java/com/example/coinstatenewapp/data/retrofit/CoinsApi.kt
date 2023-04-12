package com.example.coinstatenewapp.data.retrofit

import androidx.lifecycle.LiveData
import com.example.coinstatenewapp.model.CoinsModel
import retrofit2.Response
import retrofit2.http.GET

interface CoinsApi {

    @GET("/public/v1/coins?skip=0&limit=35&currency=EUR")
    suspend fun getCoins(): Response<CoinsModel>
}