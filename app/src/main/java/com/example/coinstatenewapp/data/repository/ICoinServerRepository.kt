package com.example.coinstatenewapp.data.repository

import androidx.lifecycle.LiveData
import com.example.coinstatenewapp.model.Coin
import com.example.coinstatenewapp.model.CoinsModel
import retrofit2.Response

interface ICoinServerRepository {
    suspend fun getAllCoinsFromServer(): Response<CoinsModel>
}