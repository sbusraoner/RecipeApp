package com.sbusraoner.recipeapp.data.network

import com.google.gson.annotations.SerializedName
import com.sbusraoner.recipespoonacular.models.Result

data class RootResponse(
    @SerializedName("number")
    val number: Int?,
    @SerializedName("offset")
    val offset: Int?,
    @SerializedName("results")
    val results: List<Result?>?,
    @SerializedName("totalResults")
    val totalResults: Int?
)
