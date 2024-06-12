package com.sbusraoner.recipeapp.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sbusraoner.recipeapp.data.source.network.DetailRootResponse


@Database(entities = [DetailRootResponse::class], version = 1, exportSchema = false)
@TypeConverters(ExtendedIngredientConverter::class,AnalyzedInstructionsConverter::class,StepConverter::class)
abstract class RecipesDataBase : RoomDatabase() {
    abstract fun recipesDao(): RecipesDao
}