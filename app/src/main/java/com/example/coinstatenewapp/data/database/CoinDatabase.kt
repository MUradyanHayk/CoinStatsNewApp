package com.example.coinstatenewapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.coinstatenewapp.model.Coin

@Database(entities = [Coin::class], version = 1)

abstract class CoinDatabase : RoomDatabase() {
    abstract fun getCoinDao(): CoinsDao

    companion object {
        private var instance: CoinDatabase? = null

        @Synchronized
        fun getInstance(ctx: Context): CoinDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(ctx.applicationContext, CoinDatabase::class.java, "note_database").build()
            }
            return instance!!
        }
    }
}