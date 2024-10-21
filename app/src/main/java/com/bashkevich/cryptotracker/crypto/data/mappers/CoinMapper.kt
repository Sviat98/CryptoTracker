package com.bashkevich.cryptotracker.crypto.data.mappers

import com.bashkevich.cryptotracker.crypto.data.networking.dto.CoinDto
import com.bashkevich.cryptotracker.crypto.domain.Coin

fun CoinDto.toCoin() = Coin(
    id = id,
    rank = rank,
    name = name,
    symbol = symbol,
    marketCapUsd = marketCapUsd,
    priceUsd = priceUsd,
    changePercent24Hr = changePercent24Hr
)