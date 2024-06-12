package com.sbusraoner.recipeapp.di

import android.content.Context
import androidx.room.Room
import com.sbusraoner.recipeapp.data.source.network.ApiService
import com.sbusraoner.recipeapp.data.source.network.NetworkDataSource
import com.sbusraoner.recipeapp.data.source.network.SpoonacularNetworkDataSource
import com.sbusraoner.recipeapp.data.SpoonacularRepository
import com.sbusraoner.recipeapp.data.SpoonacularRepositoryImpl
import com.sbusraoner.recipeapp.data.source.local.RecipesDao
import com.sbusraoner.recipeapp.data.source.local.RecipesDataBase
import com.sbusraoner.recipeapp.utils.Constants
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    @Singleton
    fun provideRecipesDao(database: RecipesDataBase): RecipesDao {
        return database.recipesDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): RecipesDataBase {
        return Room.databaseBuilder(
            context.applicationContext,
            RecipesDataBase::class.java,
            "recipes_database.db"
        ).build()
    }
}

