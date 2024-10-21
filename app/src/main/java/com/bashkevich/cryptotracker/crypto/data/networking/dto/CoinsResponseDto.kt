package com.bashkevich.cryptotracker.crypto.data.networking.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinsResponseDto(
    @SerialName("data")
    val data: List<CoinDto>
)
