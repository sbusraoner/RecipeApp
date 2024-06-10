package com.sbusraoner.recipeapp.models.detailModel


import com.google.gson.annotations.SerializedName

data class Metric(
    @SerializedName("amount")
    val amount: Double?,
    @SerializedName("unitLong")
    val unitLong: String?,
    @SerializedName("unitShort")
    val unitShort: String?
)