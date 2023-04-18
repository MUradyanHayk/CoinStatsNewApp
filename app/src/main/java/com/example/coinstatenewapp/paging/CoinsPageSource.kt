package com.example.coinstatenewapp.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.coinstatenewapp.data.retrofit.CoinsApi
import com.example.coinstatenewapp.model.Coin
import com.example.coinstatenewapp.model.CoinsModel
import retrofit2.HttpException

class CoinsPageSource(val coinsApi: CoinsApi) : PagingSource<Int, Coin>() {
    override fun getRefreshKey(state: PagingState<Int, Coin>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Coin> {
//        return try {
//            val position = params.key ?: 1
//            val response = coinsApi.getCoins(limit=position)
//            return LoadResult.Page(
//                data = response.body()!!.coins,
//                prevKey = if (position == 1) null else position - 1,
//                nextKey = if (position == 35) null else position + 1
////                nextKey = if (position==response.body().tot)
//            )
//
//        } catch (e: Exception) {
//            LoadResult.Error(e)
//        }

//        if (query.isEmpty()) {
//            return LoadResult.Page(emptyList(), prevKey = null, nextKey = null)
//        }

        val page: Int = params.key ?: 1
        val pageSize: Int = params.loadSize.coerceAtMost(CoinsApi.MAX_PAGE_SIZE)

        val response = coinsApi.getCoins(page, pageSize)

        if (response.isSuccessful) {
            val coins = checkNotNull(response.body()).coins.map { it as Coin }
            val nextKey = if (coins.size < pageSize) null else page + 1
            val prevKey = if (page == 1) null else page + 1

            return LoadResult.Page(coins, prevKey = prevKey, nextKey = nextKey)
        } else {
            return LoadResult.Error(HttpException(response))
        }

    }
}