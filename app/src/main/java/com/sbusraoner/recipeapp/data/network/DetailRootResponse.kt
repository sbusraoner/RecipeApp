package com.sbusraoner.recipeapp.data.network

import com.google.gson.annotations.SerializedName
import com.sbusraoner.recipeapp.models.detailModel.ExtendedIngredient

data class DetailRootResponse(
    @SerializedName("extendedIngredients")
    val ingredients: List<ExtendedIngredient>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("instructions")
    val instructions: String,
    @SerializedName("readyInMin")
    val readyInMin: Int,
    @SerializedName("serving")
    val servings: Int,
    @SerializedName("summary")
    val summary: String,
    @SerializedName("title")
    val title: String

)
