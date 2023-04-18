package com.example.coinstatenewapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import java.util.ArrayList

@Entity(tableName = "_coins")
data class Coin(
    @ColumnInfo(name = "id")
    @PrimaryKey
    val id: String,
//    @ColumnInfo(name = "availableSupply")
//    val availableSupply: Long,
    @ColumnInfo(name = "contractAddress")
    val contractAddress: String?,
    @ColumnInfo(name = "decimals")
    val decimals: Int,
//    @ColumnInfo(name = "exp")
//    val exp: ArrayList<String>?,
    @ColumnInfo(name = "icon")
    val icon: String?,
    @ColumnInfo(name = "marketCap")
    val marketCap: Double,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "price")
    val price: Double,
    @ColumnInfo(name = "priceBtc")
    val priceBtc: Double,
    @ColumnInfo(name = "priceChange1d")
    val priceChange1d: Double,
    @ColumnInfo(name = "priceChange1h")
    val priceChange1h: Double,
    @ColumnInfo(name = "priceChange1w")
    val priceChange1w: Double,
    @ColumnInfo(name = "rank")
    val rank: Int,
    @ColumnInfo(name = "symbol")
    val symbol: String?,
//    @ColumnInfo(name = "totalSupply")
//    val totalSupply: Long,
    @ColumnInfo(name = "twitterUrl")
    val twitterUrl: String?,
    @ColumnInfo(name = "volume")
    val volume: Double,
    @ColumnInfo(name = "websiteUrl")
    val websiteUrl: String?
) : java.io.Serializable {
    @Expose
    @Ignore
    @Transient
    var isFavorite: Boolean = false
}