package com.sbusraoner.recipeapp.data


import com.sbusraoner.recipeapp.data.source.network.DetailRootResponse
import com.sbusraoner.recipeapp.data.source.network.RootResponse
import com.sbusraoner.recipeapp.utils.ApiResult
import kotlinx.coroutines.flow.Flow

interface SpoonacularRepository {
    suspend fun getSearchRecipe(query:String) : Flow<ApiResult<RootResponse>>

    suspend fun getRecipesWithMealType(type:String) : Flow<ApiResult<RootResponse>>

    suspend fun getRecipeWihtId(id:Int) : Flow<ApiResult<DetailRootResponse>>

    fun getAllFavoriteRecipes() : Flow<List<DetailRootResponse>>
    suspend fun insertFavoriteRecipe(recipe: DetailRootResponse)
    fun deleteFavoriteRecipe(id:Int)
    suspend fun getDetailWithIdFromLocal(id: Int) : Flow<DetailRootResponse>


}