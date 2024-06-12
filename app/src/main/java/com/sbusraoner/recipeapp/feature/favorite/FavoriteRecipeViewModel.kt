package com.sbusraoner.recipeapp.feature.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sbusraoner.recipeapp.data.SpoonacularRepository
import com.sbusraoner.recipeapp.data.source.network.DetailRootResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


data class FavoriteState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null,
    val recipeModel: List<DetailRootResponse?>? = null
)

@HiltViewModel
class FavoriteRecipeViewModel @Inject constructor(
    private val repository: SpoonacularRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(FavoriteState())
    val uiState: StateFlow<FavoriteState> = _uiState

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

     fun getAllFavoriteRecipes() {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllFavoriteRecipes().collect {
                _uiState.value = FavoriteState(recipeModel = it)
                _isLoading.value = false
            }
        }
    }

    fun deleteFavoriteRecipe(id: Int) {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFavoriteRecipe(id)
            _isLoading.value = false
        }
    }



}
