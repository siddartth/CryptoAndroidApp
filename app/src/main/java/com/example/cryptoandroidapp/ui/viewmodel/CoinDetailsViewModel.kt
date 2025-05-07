package com.example.cryptoandroidapp.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoandroidapp.common.Constants
import com.example.cryptoandroidapp.common.Resource
import com.example.cryptoandroidapp.domain.use_case.GetCoinDetailsUseCase
import com.example.cryptoandroidapp.ui.viewstate.CoinDetailsViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailsViewModel @Inject constructor(
    private val getCoinDetailsUseCase: GetCoinDetailsUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _state = mutableStateOf(CoinDetailsViewState())
    val state: State<CoinDetailsViewState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
            getCoin(coinId)
        }
    }

    private fun getCoin(coinId: String) {
            getCoinDetailsUseCase(coinId).onEach { result ->
                when(result) {
                    is Resource.Error<*> -> {
                        _state.value = CoinDetailsViewState(
                            isLoading = false,
                            error = result.message?:"An unexpected error occurred."
                        )
                    }
                    is Resource.Loading<*> -> {
                        _state.value = CoinDetailsViewState(
                            isLoading = true
                        )
                    }
                    is Resource.Success<*> -> {
                        _state.value = CoinDetailsViewState(
                            isLoading = false,
                            coin = result.data
                        )
                    }
                }
            }.launchIn(viewModelScope)
    }
}