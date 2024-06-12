package com.sbusraoner.recipeapp.models.detailModel


import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class ExtendedIngredient(
    @PrimaryKey(autoGenerate = true)
    val ingredientId: Int = 0,
    @SerializedName("aisle")
    val aisle: String?,
    @SerializedName("amount")
    val amount: Double?,
    @SerializedName("consistency")
    val consistency: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("nameClean")
    val nameClean: String?,
    @SerializedName("original")
    val original: String?,
    @SerializedName("originalName")
    val originalName: String?,
    @SerializedName("unit")
    val unit: String?
)