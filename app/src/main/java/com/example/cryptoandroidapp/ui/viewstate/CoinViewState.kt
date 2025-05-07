package com.example.cryptoandroidapp.ui.viewstate

import com.example.cryptoandroidapp.domain.model.Coin

data class CoinViewState(
    val coins: List<Coin> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)
