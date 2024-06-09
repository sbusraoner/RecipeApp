package com.sbusraoner.recipeapp.di

import com.sbusraoner.recipeapp.data.network.ApiService
import com.sbusraoner.recipeapp.data.network.NetworkDataSource
import com.sbusraoner.recipeapp.data.network.SpoonacularNetworkDataSource
import com.sbusraoner.recipeapp.data.network.SpoonacularRepository
import com.sbusraoner.recipeapp.data.network.SpoonacularRepositoryImpl
import com.sbusraoner.recipeapp.utils.Constants
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    //okhttp
    @Provides
    fun provideOkHttpClient() : OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
    }
    //retrofit
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //api service
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideEdamamNetworkDataSource(apiService: ApiService) : NetworkDataSource {
        return SpoonacularNetworkDataSource(apiService)
    }

}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindEdamamRepository(
        spoonacularRepositoryImpl: SpoonacularRepositoryImpl
    ): SpoonacularRepository
}

