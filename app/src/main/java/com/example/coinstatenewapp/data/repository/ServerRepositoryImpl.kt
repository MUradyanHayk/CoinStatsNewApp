package com.example.coinstatenewapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.coinstatenewapp.data.retrofit.RetrofitInstance
import com.example.coinstatenewapp.model.CoinsModel
import com.example.coinstatenewapp.paging.CoinsPageSource
import retrofit2.Response

class ServerRepositoryImpl : ICoinServerRepository {
    override suspend fun getAllCoinsFromServer(): Response<CoinsModel> {
        return RetrofitInstance.api.getCoins(limit = 6)
    }

    fun getCoins() = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100), pagingSourceFactory = {
            CoinsPageSource(coinsApi = RetrofitInstance.api)
        }
    ).liveData
}