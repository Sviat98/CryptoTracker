package com.bashkevich.cryptotracker.crypto.presentation.coin_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bashkevich.cryptotracker.R
import com.bashkevich.cryptotracker.crypto.presentation.CoinListState
import com.bashkevich.cryptotracker.crypto.presentation.coin_detail.components.InfoCard
import com.bashkevich.cryptotracker.crypto.presentation.coin_list.components.CoinListItem
import com.bashkevich.cryptotracker.crypto.presentation.coin_list.components.previewCoin
import com.bashkevich.cryptotracker.crypto.presentation.models.toDisplayableNumber
import com.bashkevich.cryptotracker.ui.theme.CryptoTrackerTheme
import com.bashkevich.cryptotracker.ui.theme.greenBackground

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CoinDetailScreen(
    modifier: Modifier = Modifier,
    state: CoinListState
) {
    val contentColor = if (isSystemInDarkTheme()) Color.White else Color.Black

    if (state.isLoading) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else if (state.selectedCoin != null) {
        val coin = state.selectedCoin

        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = coin.iconRes),
                contentDescription = coin.name,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(100.dp)
            )
            Text(
                text = coin.name,
                fontSize = 40.sp,
                fontWeight = FontWeight.Black,
                textAlign = TextAlign.Center,
                color = contentColor
            )
            Text(
                text = coin.symbol,
                fontSize = 20.sp,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Center,
                color = contentColor
            )
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                InfoCard(
                    title = stringResource(R.string.market_cap),
                    formattedText = "$ ${coin.marketCapUsd.formatted}",
                    icon = ImageVector.vectorResource(R.drawable.stock)
                )
                InfoCard(
                    title = stringResource(R.string.price),
                    formattedText = "$ ${coin.priceUsd.formatted}",
                    icon = ImageVector.vectorResource(R.drawable.dollar)
                )
                val absoluteChangeFormatted =
                    (coin.priceUsd.value * (coin.changePercent24Hr.value / 100)).toDisplayableNumber()

                val isPositive = coin.changePercent24Hr.value > 0.0

                val trendingColor = if (isPositive){
                    if (isSystemInDarkTheme()) Color.Green else greenBackground
                }else{
                    MaterialTheme.colorScheme.error
                }
                InfoCard(
                    title = stringResource(R.string.change_last_24h),
                    formattedText = absoluteChangeFormatted.formatted,
                    icon = if (isPositive){
                        ImageVector.vectorResource(R.drawable.trending)
                    }else{
                        ImageVector.vectorResource(R.drawable.trending_down)
                    },
                    contentColor = trendingColor
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun CoinDetailScreenPreview() {
    CryptoTrackerTheme {
        CoinDetailScreen(
            state = CoinListState(
                isLoading = false,
                selectedCoin = previewCoin,
            ),
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        )
    }
}