package com.sbusraoner.recipeapp.data.network

import com.sbusraoner.recipeapp.utils.ApiResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SpoonacularRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource,
) : SpoonacularRepository {
    override suspend fun getSearchRecipe(
        query: String
    ): Flow<ApiResult<RootResponse>> {
        val searchRecipeResponse = networkDataSource.getSearchRecipe(query)
        searchRecipeResponse.collect{ value ->
            when(value){
                is ApiResult.Success -> {
                    // İlerde locale verıler burada kaydedılecek
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
                    // İlerde locale veriler burada kaydedilecek
                    //  localDataSource.insertCharacters(value.data?.toLocal().orEmpty())
                }

                else -> {
                }
            }
        }

        return getRecipesWithMealTypeResponse
    }

    override suspend fun getRecipeWihtId(
        id: Int) : Flow<ApiResult<RootResponse>>{
        val getRecipeWithIdResponse = networkDataSource.getRecipesWithId(id)
        getRecipeWithIdResponse.collect {value ->
            when (value) {
                is ApiResult.Success -> {
                    // İlerde locale veriler burada kaydedilecek
                    //  localDataSource.insertCharacters(value.data?.toLocal().orEmpty())
                }
                else -> {}
            }
        }
        return getRecipeWithIdResponse
    }

}
