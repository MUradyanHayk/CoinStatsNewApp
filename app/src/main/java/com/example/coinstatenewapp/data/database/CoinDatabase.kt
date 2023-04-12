package com.example.coinstatenewapp.data.database

import androidx.room.RoomDatabase

abstract class CoinDatabase : RoomDatabase() {
    abstract fun getCoinDao(): CoinsDao
}