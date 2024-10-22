package com.bashkevich.cryptotracker.crypto.domain

import com.bashkevich.cryptotracker.crypto.data.networking.dto.CoinPriceDto
import java.time.ZonedDateTime

data class CoinPrice(
    val priceUsd: Double,
    val dateTime: ZonedDateTime
)