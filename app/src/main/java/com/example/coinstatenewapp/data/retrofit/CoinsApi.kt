package com.example.coinstatenewapp.data.retrofit

import com.example.coinstatenewapp.model.CoinsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinsApi {
    companion object {
        const val MAX_PAGE_SIZE = 200
    }

    @GET("/public/v1/coins?skip=0&limit=35&currency=EUR")
    suspend fun getCoins(): Response<CoinsModel>

    @GET("/public/v1/coins")
    suspend fun getCoins(@Query("skip") skip: Int = 0, @Query("limit") limit: Int, @Query("currency") currency: String = "EUR"): Response<CoinsModel>
}