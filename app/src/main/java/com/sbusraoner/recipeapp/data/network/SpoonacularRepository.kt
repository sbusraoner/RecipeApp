package com.sbusraoner.recipeapp.data.network


import com.sbusraoner.recipeapp.utils.ApiResult
import kotlinx.coroutines.flow.Flow

interface SpoonacularRepository {
    suspend fun getSearchRecipe(query:String) : Flow<ApiResult<RootResponse>>

    suspend fun getRecipesWithMealType(type:String) : Flow<ApiResult<RootResponse>>

    suspend fun getRecipeWihtId(id:Int) : Flow<ApiResult<RootResponse>>
}