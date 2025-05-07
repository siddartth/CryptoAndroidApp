package com.example.cryptoandroidapp.ui.viewstate

import com.example.cryptoandroidapp.domain.model.CoinDetails

data class CoinDetailsViewState(
    val coin: CoinDetails? = null,
    val isLoading: Boolean = false,
    val error: String = ""
)