package com.bashkevich.cryptotracker.crypto.presentation.coin_list

import com.bashkevich.cryptotracker.core.domain.util.NetworkError

sealed interface CoinListOneTimeAction{
    data class Error(val error: NetworkError): CoinListOneTimeAction
}
