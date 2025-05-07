package com.example.cryptoandroidapp.domain.use_case

import com.example.cryptoandroidapp.common.Resource
import com.example.cryptoandroidapp.data.dto.toCoinDetails
import com.example.cryptoandroidapp.domain.model.CoinDetails
import com.example.cryptoandroidapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetCoinDetailsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetails>> = flow {
        try {
            emit(Resource.Loading())
            val coinDetails = repository.getCoinById(coinId = coinId).toCoinDetails()
            emit(Resource.Success(coinDetails))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.message()?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.message.toString()?: "Server Error"))
        }
    }
}