package com.sbusraoner.recipeapp.models


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("imageType")
    val imageType: String?,
    @SerializedName("title")
    val title: String?
)