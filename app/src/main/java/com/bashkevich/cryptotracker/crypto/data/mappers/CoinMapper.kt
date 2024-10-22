package com.bashkevich.cryptotracker.crypto.data.mappers

import com.bashkevich.cryptotracker.crypto.data.networking.dto.CoinDto
import com.bashkevich.cryptotracker.crypto.data.networking.dto.CoinPriceDto
import com.bashkevich.cryptotracker.crypto.domain.Coin
import com.bashkevich.cryptotracker.crypto.domain.CoinPrice
import java.time.Instant
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime

fun CoinDto.toCoin() = Coin(
    id = id,
    rank = rank,
    name = name,
    symbol = symbol,
    marketCapUsd = marketCapUsd,
    priceUsd = priceUsd,
    changePercent24Hr = changePercent24Hr
)


fun CoinPriceDto.toCoinPrice(): CoinPrice {
        return CoinPrice(priceUsd,Instant.ofEpochMilli(dateTime).atZone(ZoneOffset.UTC))
}