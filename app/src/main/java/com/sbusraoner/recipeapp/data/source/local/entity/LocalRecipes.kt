package com.sbusraoner.recipeapp.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.sbusraoner.recipeapp.models.detailModel.AnalyzedInstructions
import com.sbusraoner.recipeapp.models.detailModel.ExtendedIngredient

@Entity(
    tableName = "recipes"
)
data class LocalRecipes(
    @PrimaryKey
    val id :Int?,
    val image: String?,
    val imageType: String?,
    val summary: String,
    val title: String,

)