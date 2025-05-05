package com.example.cryptoandroidapp.domain.use_case

import com.example.cryptoandroidapp.common.Resource
import com.example.cryptoandroidapp.data.dto.toCoin
import com.example.cryptoandroidapp.domain.model.Coin
import com.example.cryptoandroidapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())
            val coins = repository.getCoins().map { it.toCoin() }
            emit(Resource.Success(coins))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.message()?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.message.toString()?: "Server Error"))
        }
    }
}