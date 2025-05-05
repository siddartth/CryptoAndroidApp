package com.example.cryptoandroidapp.data.api

import com.example.cryptoandroidapp.data.dto.CoinDetailsDto
import com.example.cryptoandroidapp.data.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinApi {

    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinDto>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoinById(
        @Path("coinId") coinId: String
    ): CoinDetailsDto
}