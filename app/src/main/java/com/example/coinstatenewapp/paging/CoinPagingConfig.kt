package com.example.coinstatenewapp.paging

import androidx.paging.PagingConfig.Companion.MAX_SIZE_UNBOUNDED
import androidx.paging.PagingSource.LoadResult.Page.Companion.COUNT_UNDEFINED

class CoinPagingConfig(
    val pageSize: Int = 0,
    val prefetchDistance: Int = pageSize,
    val enablePlaceholders: Boolean = true,
    val initialLoadSize: Int = pageSize * 3,
    val maxSize: Int = MAX_SIZE_UNBOUNDED,
    val jumpThreshold: Int = COUNT_UNDEFINED,
)