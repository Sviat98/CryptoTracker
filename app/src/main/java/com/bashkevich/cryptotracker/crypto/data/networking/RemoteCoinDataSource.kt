package com.bashkevich.cryptotracker.crypto.data.networking

import com.bashkevich.cryptotracker.core.data.networking.constructUrl
import com.bashkevich.cryptotracker.core.data.networking.safeCall
import com.bashkevich.cryptotracker.core.domain.util.NetworkError
import com.bashkevich.cryptotracker.core.domain.util.Result
import com.bashkevich.cryptotracker.core.domain.util.map
import com.bashkevich.cryptotracker.crypto.data.mappers.toCoin
import com.bashkevich.cryptotracker.crypto.data.mappers.toCoinPrice
import com.bashkevich.cryptotracker.crypto.data.networking.dto.CoinHistoryDto
import com.bashkevich.cryptotracker.crypto.data.networking.dto.CoinsResponseDto
import com.bashkevich.cryptotracker.crypto.domain.Coin
import com.bashkevich.cryptotracker.crypto.domain.CoinDataSource
import com.bashkevich.cryptotracker.crypto.domain.CoinPrice
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import java.time.ZoneOffset

import java.time.ZonedDateTime

class RemoteCoinDataSource(
    private val httpClient: HttpClient
): CoinDataSource {
    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {
        return safeCall<CoinsResponseDto> {
            httpClient.get(urlString =constructUrl("/assets"))
        }.map {response->  response.data.map { it.toCoin() } }
    }

    override suspend fun getCoinHistory(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime
    ): Result<List<CoinPrice>, NetworkError> {
        val startMillis = start.withZoneSameInstant(ZoneOffset.UTC).toInstant().toEpochMilli()

        val endMillis = end.withZoneSameInstant(ZoneOffset.UTC).toInstant().toEpochMilli()

        return safeCall<CoinHistoryDto> {
            httpClient.get(urlString =constructUrl("/assets/$coinId/history")){
                parameter("interval","h6")
                parameter("start",startMillis)
                parameter("end",endMillis)
            }
        }.map {response->  response.data.map { it.toCoinPrice() } }
    }

}