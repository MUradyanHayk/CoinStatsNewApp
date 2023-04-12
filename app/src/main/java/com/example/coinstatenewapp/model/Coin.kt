package com.example.coinstatenewapp.model

import java.util.ArrayList

data class Coin(
    val availableSupply: Long,
    val contractAddress: String?,
    val decimals: Int,
    val exp: ArrayList<String>?,
    val icon: String?,
    val id: String?,
    val marketCap: Double,
    val name: String?,
    val price: Double,
    val priceBtc: Double,
    val priceChange1d: Double,
    val priceChange1h: Double,
    val priceChange1w: Double,
    val rank: Int,
    val symbol: String?,
    val totalSupply: Long,
    val twitterUrl: String?,
    val volume: Double,
    val websiteUrl: String?
):java.io.Serializable