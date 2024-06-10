package com.sbusraoner.recipeapp.data.network

import com.sbusraoner.recipeapp.utils.ApiResult
import com.sbusraoner.recipeapp.utils.Constants
import com.sbusraoner.recipeapp.utils.apiFlow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class SpoonacularNetworkDataSource @Inject constructor(
    private val apiService: ApiService
) : NetworkDataSource {

    override suspend fun getSearchRecipe(
        query: String
    ): Flow<ApiResult<RootResponse>> = apiFlow {
        apiService.getSearchRecipe(query,Constants.API_KEY)
    }


    override suspend fun getRecipesWithMealType(
        type: String,
    ): Flow<ApiResult<RootResponse>> = apiFlow {
        apiService.getRecipesWithMealType(Constants.API_KEY, type)
    }

    override suspend fun getRecipesWithId(
        id:Int
    ): Flow<ApiResult<DetailRootResponse>> = apiFlow {
        apiService.getRecipesWithId(id,Constants.API_KEY)
    }

}