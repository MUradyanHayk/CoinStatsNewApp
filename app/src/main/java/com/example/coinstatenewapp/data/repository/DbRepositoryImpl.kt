package com.example.coinstatenewapp.data.repository

import androidx.lifecycle.LiveData
import com.example.coinstatenewapp.data.database.CoinsDao
import com.example.coinstatenewapp.model.Coin

class DbRepositoryImpl(val coinDao: CoinsDao) : IDbRepository {
    override val allFavoriteCoins: LiveData<List<Coin>>
        get() = coinDao.getAllFavoriteCoins()

    override suspend fun updateCoin(coin: Coin, onSuccess: () -> Unit) {
        coinDao.update(coin)
        onSuccess()
    }

    override suspend fun insertCoin(coin: Coin, onSuccess: () -> Unit) {
        coinDao.insert(coin)
        onSuccess()
    }

    override suspend fun deleteCoin(coin: Coin, onSuccess: () -> Unit) {
        coinDao.delete(coin)
        onSuccess()
    }
}