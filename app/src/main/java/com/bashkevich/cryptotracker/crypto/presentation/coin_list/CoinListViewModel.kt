package com.bashkevich.cryptotracker.crypto.presentation.coin_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bashkevich.cryptotracker.core.domain.util.onError
import com.bashkevich.cryptotracker.core.domain.util.onSuccess
import com.bashkevich.cryptotracker.crypto.domain.CoinDataSource
import com.bashkevich.cryptotracker.crypto.presentation.CoinListState
import com.bashkevich.cryptotracker.crypto.presentation.models.toCoinUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CoinListViewModel(
    private val coinDataSource: CoinDataSource
) : ViewModel() {

    private val _state = MutableStateFlow(CoinListState())
    val state = _state.onStart {
        loadCoins()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), CoinListState())

    fun onAction(action: CoinListAction){
        when(action){
            is CoinListAction.OnCoinClick -> {

            }
        }
    }


    private fun loadCoins() {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }

            coinDataSource.getCoins().onSuccess { coins ->
                _state.update {
                    it.copy(coins = coins.map { coin -> coin.toCoinUi() })
                }
            }
                .onError { error ->
                    _state.update {
                        it.copy(isLoading = false)
                    }
                }
        }
    }
}