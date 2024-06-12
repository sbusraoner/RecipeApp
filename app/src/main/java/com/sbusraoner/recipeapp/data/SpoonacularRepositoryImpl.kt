package com.sbusraoner.recipeapp.data

import com.sbusraoner.recipeapp.data.SpoonacularRepository
import com.sbusraoner.recipeapp.data.source.local.RecipesDao
import com.sbusraoner.recipeapp.data.source.network.DetailRootResponse
import com.sbusraoner.recipeapp.data.source.network.NetworkDataSource
import com.sbusraoner.recipeapp.data.source.network.RootResponse
import com.sbusraoner.recipeapp.models.Result
import com.sbusraoner.recipeapp.utils.ApiResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SpoonacularRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val localDataSource: RecipesDao
) : SpoonacularRepository {
    override suspend fun getSearchRecipe(
        query: String
    ): Flow<ApiResult<RootResponse>> {
        val searchRecipeResponse = networkDataSource.getSearchRecipe(query)
        searchRecipeResponse.collect{ value ->
            when(value){
                is ApiResult.Success -> {
                    //localDataSource.insertRecipes(value.data?.toLocal().orEmpty())
                  //  localDataSource.insertCharacters(value.data?.toLocal().orEmpty())
                }
                else -> {
                    //ignored
                }
            }
        }
        return searchRecipeResponse

    }

    override suspend fun getRecipesWithMealType(
        type:String,
    ): Flow<ApiResult<RootResponse>> {
        val getRecipesWithMealTypeResponse = networkDataSource.getRecipesWithMealType(type)
        getRecipesWithMealTypeResponse.collect{ value ->
            when(value) {
                is ApiResult.Success -> {
                    //Local'e kayıt işlemleri
                }

                else -> {
                }
            }
        }

        return getRecipesWithMealTypeResponse
    }

    override suspend fun getRecipeWihtId(
        id: Int) : Flow<ApiResult<DetailRootResponse>>{
        val getRecipeWithIdResponse = networkDataSource.getRecipesWithId(id)
        getRecipeWithIdResponse.collect {value ->
            when (value) {
                is ApiResult.Success -> {
                    //Local'e kayıt işlemleri

                }
                else -> {}
            }
        }
        return getRecipeWithIdResponse
    }

    override suspend fun insertFavoriteRecipe(recipe: DetailRootResponse) {
        localDataSource.insertRecipes(recipe)
    }

    override fun deleteFavoriteRecipe(id: Int) {
        localDataSource.deleteDetailModelById(id = id)
    }

    override suspend fun getDetailWithIdFromLocal(id: Int) : Flow<DetailRootResponse> {
        return localDataSource.getDetailModelById(id = id)
    }

    override fun getAllFavoriteRecipes(): Flow<List<DetailRootResponse>> {
        return localDataSource.getAllFavoriteRecipes()
    }

    //

}
