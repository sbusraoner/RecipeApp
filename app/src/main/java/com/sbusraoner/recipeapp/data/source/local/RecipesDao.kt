package com.sbusraoner.recipeapp.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sbusraoner.recipeapp.data.source.network.DetailRootResponse
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipesDao {

    // insert many
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipes(recipes: DetailRootResponse)

    // observe all
    @Query("SELECT * FROM recipes")
     fun getAllFavoriteRecipes(): Flow<List<DetailRootResponse>>

    @Query("DELETE FROM recipes WHERE id = :id")
    fun deleteDetailModelById(id: Int)

    @Query("SELECT * FROM recipes WHERE id = :id")
    fun getDetailModelById(id: Int): Flow<DetailRootResponse>

}