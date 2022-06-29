package com.nithiann.thecircle.di

import com.nithiann.thecircle.common.Constants
import com.nithiann.thecircle.domain.repository.AboutRepository
import com.nithiann.thecircle.domain.repository.MessageListRepository
import com.nithiann.thecircle.domain.repository.MessageRepository
import com.nithiann.thecircle.domain.use_case.getMessagesUseCase
import com.nithiann.thecircle.infrastructure.remote.Api
import com.nithiann.thecircle.infrastructure.repository.AboutRepositoryImpl
import com.nithiann.thecircle.infrastructure.repository.MessageListRepositoryImpl
import com.nithiann.thecircle.infrastructure.repository.MessageRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.KeyStore
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)

object AppModule {
    val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    var client: OkHttpClient = OkHttpClient().newBuilder()
        .addInterceptor(loggingInterceptor)
        .build()

    @Provides
    @Singleton
    fun provideUserApi(): Api {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
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

    @Provides
    @Singleton
    fun provideMessageListRepository(api: Api): MessageListRepository {
        return MessageListRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideUseCaseGetMessages(repository: MessageListRepository): getMessagesUseCase {
        return getMessagesUseCase(repository)
    }


    @Provides
    @Singleton
    fun provideCAKeyStore(): KeyStore {
        return KeyStore.getInstance("AndroidCAStore").apply {
            load(null)
        }
    }

    @Provides
    @Singleton
    fun providePrivateKeyStore(): KeyStore {
        return KeyStore.getInstance("AndroidKeyStore").apply {
            load(null)
        }
    }
}