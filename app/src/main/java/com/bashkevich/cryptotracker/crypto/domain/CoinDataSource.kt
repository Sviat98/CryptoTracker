package com.bashkevich.cryptotracker.crypto.domain

import com.bashkevich.cryptotracker.core.domain.util.NetworkError
import com.bashkevich.cryptotracker.core.domain.util.Result

interface CoinDataSource {
    suspend fun getCoins(): Result<List<Coin>, NetworkError>
}