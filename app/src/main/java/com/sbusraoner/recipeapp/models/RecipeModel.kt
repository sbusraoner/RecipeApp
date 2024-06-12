package com.sbusraoner.recipeapp.models


import com.google.gson.annotations.SerializedName

data class RecipeModel(
    @SerializedName("number")
    val number: Int?,
    @SerializedName("offset")
    val offset: Int?,
    @SerializedName("results")
    val results: List<Result?>?,
    @SerializedName("totalResults")
    val totalResults: Int?,
    @SerializedName("status")
    val status :String?,
    @SerializedName("code")
    val code :Int?,
    @SerializedName("message")
    val message:String?
)