package com.sbusraoner.recipeapp.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sbusraoner.recipeapp.models.RecipeModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


data class HomeState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val recipeModel: List<RecipeModel> = emptyList()
)

@HiltViewModel
class HomeViewModel @Inject constructor(
) : ViewModel() {

    fun getRecipeWithMealType(mealType: ArrayList<String>)  {
        val internetOnline = true


        viewModelScope.launch {

        }

    }
}