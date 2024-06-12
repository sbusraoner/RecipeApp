package com.sbusraoner.recipeapp.data.source.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sbusraoner.recipeapp.models.detailModel.AnalyzedInstructions
import com.sbusraoner.recipeapp.models.detailModel.ExtendedIngredient
import com.sbusraoner.recipeapp.models.detailModel.Step
import java.util.Collections

class Converter {
/*
    @TypeConverter
    fun fromExtendedIngredientList(value: List<ExtendedIngredient?>?): String {
        val gson = Gson()
        val type = object : TypeToken<List<ExtendedIngredient?>?>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toExtendedIngredientList(value: String): List<ExtendedIngredient?>? {
        val gson = Gson()
        val type = object : TypeToken<List<ExtendedIngredient?>?>() {}.type
        return gson.fromJson(value, type)
    }

 */
/*
    @TypeConverter
    fun fromAnalyzedInstructionsList(value: List<AnalyzedInstructions?>?): String {
        val gson = Gson()
        val type = object : TypeToken<List<AnalyzedInstructions?>?>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toAnalyzedInstructionsList(value: String): List<AnalyzedInstructions?>? {
        val gson = Gson()
        val type = object : TypeToken<List<AnalyzedInstructions?>?>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromStepList(value: List<Step?>?): String {
        val gson = Gson()
        val type = object : TypeToken<List<Step?>?>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toStepList(value: String): List<Step?>? {
        val gson = Gson()
        val type = object : TypeToken<List<Step?>?>() {}.type
        return gson.fromJson(value, type)
    }


 */

}

class ExtendedIngredientConverter {
    private val gson = Gson()
    @TypeConverter
    fun stringToList(data: String?): List<ExtendedIngredient> {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<ExtendedIngredient>>() {

        }.type

        return gson.fromJson<List<ExtendedIngredient>>(data, listType)
    }

    @TypeConverter
    fun listToString(someObjects: List<ExtendedIngredient>): String {
        return gson.toJson(someObjects)
    }
}

class AnalyzedInstructionsConverter {
    private val gson = Gson()
    @TypeConverter
    fun stringToListt(data: String?): List<AnalyzedInstructions> {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<AnalyzedInstructions>>() {

        }.type

        return gson.fromJson<List<AnalyzedInstructions>>(data, listType)
    }

    @TypeConverter
    fun listtToString(someObjects: List<AnalyzedInstructions>): String {
        return gson.toJson(someObjects)
    }
}

class StepConverter {
    private val gson = Gson()
    @TypeConverter
    fun stringToListtt(data: String?): List<Step> {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<Step>>() {

        }.type

        return gson.fromJson<List<Step>>(data, listType)
    }

    @TypeConverter
    fun listttToString(someObjects: List<Step>): String {
        return gson.toJson(someObjects)
    }
}