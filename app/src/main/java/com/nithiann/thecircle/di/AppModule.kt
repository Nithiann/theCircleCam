package com.nithiann.thecircle.di

import com.nithiann.thecircle.common.Constants
import com.nithiann.thecircle.domain.repository.AboutRepository
import com.nithiann.thecircle.domain.repository.MessageRepository
import com.nithiann.thecircle.infrastructure.remote.Api
import com.nithiann.thecircle.infrastructure.repository.AboutRepositoryImpl
import com.nithiann.thecircle.infrastructure.repository.MessageRepositoryImpl
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
    fun provideUserApi(): Api {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }

    @Provides
    @Singleton
    fun provideUserRepository(api: Api): AboutRepository {
        return AboutRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideMessagesRepository(api: Api): MessageRepository {
        return MessageRepositoryImpl(api)
    }
}