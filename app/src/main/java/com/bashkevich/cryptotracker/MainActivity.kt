package com.bashkevich.cryptotracker

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bashkevich.cryptotracker.core.navigation.AdaptiveCoinListDetailPane
import com.bashkevich.cryptotracker.core.presentation.util.ObserveAsOneTimeActions
import com.bashkevich.cryptotracker.core.presentation.util.toString
import com.bashkevich.cryptotracker.crypto.presentation.coin_detail.CoinDetailScreen
import com.bashkevich.cryptotracker.crypto.presentation.coin_list.CoinListOneTimeAction
import com.bashkevich.cryptotracker.crypto.presentation.coin_list.CoinListScreen
import com.bashkevich.cryptotracker.crypto.presentation.coin_list.CoinListViewModel
import com.bashkevich.cryptotracker.ui.theme.CryptoTrackerTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptoTrackerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val viewModel = koinViewModel<CoinListViewModel>()

                    AdaptiveCoinListDetailPane(
                        modifier = Modifier.padding(innerPadding),
                        viewModel = viewModel
                        )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CryptoTrackerTheme {
        Greeting("Android")
    }
}