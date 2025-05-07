package com.example.cryptoandroidapp.domain.repository

import com.example.cryptoandroidapp.data.dto.CoinDetailsDto
import com.example.cryptoandroidapp.data.dto.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailsDto
}