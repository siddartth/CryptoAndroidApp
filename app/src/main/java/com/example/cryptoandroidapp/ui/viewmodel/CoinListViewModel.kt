package com.example.cryptoandroidapp.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoandroidapp.common.Resource
import com.example.cryptoandroidapp.domain.use_case.GetCoinUseCase
import com.example.cryptoandroidapp.ui.viewstate.CoinViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase
): ViewModel() {

    private val _state = mutableStateOf(CoinViewState())
    val state: State<CoinViewState> = _state


    init {
        Log.d("CoinListViewModel", "view Model invoked")
        getCoins()
    }

    private fun getCoins() {
            getCoinUseCase().onEach { result ->
                when(result) {
                    is Resource.Error<*> -> {
                        _state.value = CoinViewState(error = result.message?: "An unexpected Error occurred.")
                    }
                    is Resource.Loading<*> -> {
                        Log.d("CoinListViewModel", "Loading...")
                        _state.value = CoinViewState(isLoading = true)
                    }
                    is Resource.Success<*> -> {
                        Log.d("CoinListViewModel", "Success")
                        _state.value = CoinViewState(coins = result.data?: emptyList())
                    }
                }
            }.launchIn(viewModelScope)
    }

}