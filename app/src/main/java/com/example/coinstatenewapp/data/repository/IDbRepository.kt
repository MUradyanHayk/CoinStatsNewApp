package com.example.coinstatenewapp.data.repository

import androidx.lifecycle.LiveData
import com.example.coinstatenewapp.model.Coin

interface IDbRepository {
    val allFavoriteCoins: LiveData<List<Coin>>

    suspend fun updateCoin(coin: Coin, onSuccess: () -> Unit = {})
    suspend fun insertCoin(coin: Coin, onSuccess: () -> Unit = {})
    suspend fun deleteCoin(coin: Coin, onSuccess: () -> Unit = {})
}