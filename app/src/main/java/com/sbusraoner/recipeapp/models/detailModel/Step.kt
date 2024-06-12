package com.sbusraoner.recipeapp.models.detailModel

import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.sbusraoner.recipeapp.data.source.local.Converter

data class Step(
    @SerializedName("number")
    val number:Int?,
    @SerializedName("step")
    val step:String?,
)
