package com.sbusraoner.recipeapp.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("recipes/complexSearch")
    //Endpoint
    suspend fun getSearchRecipe(apiKey:String): Response<RootResponse>

    @GET("recipes/complexSearch")
    suspend fun getRecipesWithMealType(
        @Query("apiKey") apiKey: String,
        @Query("type") type:String,
    ): Response<RootResponse>

    //https://api.spoonacular.com/recipes/{id}/information
    @GET("recipes/{id}/information")
    suspend fun getRecipesWithId(
        @Query("apiKey") apiKey: String,
        @Query("id") id:Int,
    ): Response<RootResponse>

}