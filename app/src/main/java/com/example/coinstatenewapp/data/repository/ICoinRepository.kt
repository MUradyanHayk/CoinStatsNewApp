package com.example.coinstatenewapp.data.repository

import androidx.lifecycle.LiveData
import com.example.coinstatenewapp.model.CoinsModel
import retrofit2.Response

interface ICoinRepository {
    suspend fun getAllCoinsFromServer(): Response<CoinsModel>
}