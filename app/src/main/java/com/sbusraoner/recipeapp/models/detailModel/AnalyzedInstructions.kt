package com.sbusraoner.recipeapp.models.detailModel

import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.sbusraoner.recipeapp.data.source.local.StepConverter

data class AnalyzedInstructions(
    @TypeConverters(StepConverter::class)
    @SerializedName("steps")
    val steps:List<Step?>?
)
