package com.example.coinstatenewapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.coinstatenewapp.model.Coin

@Dao
interface CoinsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(coin: Coin)

    @Delete
    suspend fun delete(coin: Coin)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(coin: Coin)
    @Query("SELECT * FROM _coins")
    fun getAllFavoriteCoins(): List<Coin>
}