package com.example.coinstatenewapp.data.repository

import androidx.lifecycle.LiveData
import com.example.coinstatenewapp.data.retrofit.RetrofitInstance
import com.example.coinstatenewapp.model.CoinsModel
import retrofit2.Response

class ServerRepositoryImpl : ICoinServerRepository {
    override suspend fun getAllCoinsFromServer(): Response<CoinsModel> {
        return RetrofitInstance.api.getCoins()
    }
}