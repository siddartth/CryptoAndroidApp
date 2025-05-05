package com.example.cryptoandroidapp.data.repository

import com.example.cryptoandroidapp.data.api.CoinApi
import com.example.cryptoandroidapp.data.dto.CoinDetailsDto
import com.example.cryptoandroidapp.data.dto.CoinDto
import com.example.cryptoandroidapp.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinApi
): CoinRepository {
    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailsDto {
        return api.getCoinById(coinId)
    }
}