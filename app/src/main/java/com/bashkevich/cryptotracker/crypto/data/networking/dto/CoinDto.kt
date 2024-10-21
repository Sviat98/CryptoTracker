package com.bashkevich.cryptotracker.crypto.data.networking.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinDto(
    @SerialName("id")
    val id: String,
    @SerialName("rank")
    val rank: Int,
    @SerialName("name")
    val name: String,
    @SerialName("symbol")
    val symbol: String,
    @SerialName("marketCapUsd")
    val marketCapUsd: Double,
    @SerialName("priceUsd")
    val priceUsd: Double,
    @SerialName("changePercent24Hr")
    val changePercent24Hr: Double
)
