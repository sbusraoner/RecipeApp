package com.sbusraoner.recipeapp.data.source.network

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.sbusraoner.recipeapp.data.source.local.AnalyzedInstructionsConverter
import com.sbusraoner.recipeapp.data.source.local.ExtendedIngredientConverter
import com.sbusraoner.recipeapp.models.detailModel.AnalyzedInstructions
import com.sbusraoner.recipeapp.models.detailModel.ExtendedIngredient

@Entity(tableName = "recipes")
data class DetailRootResponse(
    @TypeConverters(ExtendedIngredientConverter::class)
    @SerializedName("extendedIngredients")
    val ingredients: List<ExtendedIngredient>,
    @PrimaryKey
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
    val title: String,
    @TypeConverters(AnalyzedInstructionsConverter::class)
    @SerializedName("analyzedInstructions")
    val analyzedInstructions: List<AnalyzedInstructions>,
)
