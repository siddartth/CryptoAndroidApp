package com.example.cryptoandroidapp.di

import com.example.cryptoandroidapp.common.Constants
import com.example.cryptoandroidapp.data.api.CoinApi
import com.example.cryptoandroidapp.data.repository.CoinRepositoryImpl
import com.example.cryptoandroidapp.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesCoinsApi(): CoinApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinApi::class.java)
    }

    @Provides
    @Singleton
    fun providesCoinsRepository(api: CoinApi): CoinRepository {
        return CoinRepositoryImpl(api)
    }

}