package com.sbusraoner.recipeapp.feature.recipe_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sbusraoner.recipeapp.data.network.SpoonacularRepository
import com.sbusraoner.recipeapp.models.Result
import com.sbusraoner.recipeapp.utils.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


data class RecipeListState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null,
    val recipeModel: List<Result?>? = null,
)


@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val repository: SpoonacularRepository,
) : ViewModel() {


    private val _uiState = MutableStateFlow(RecipeListState())
    val uiState: StateFlow<RecipeListState> = _uiState

    fun getRecipeWithMealType(type: String) {
        val internetOnline = true

        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(isLoading = true)
            }
            if (internetOnline) {
                repository.getRecipesWithMealType(type).collect { networkData ->
                    when (networkData) {
                        is ApiResult.Success -> {
                            // Do something with the data
                            _uiState.value = RecipeListState(
                                isLoading = false,
                                isError = false,
                                errorMessage = null,
                                recipeModel = networkData.data?.results

                            )
                        }

                        is ApiResult.Error -> {
                            // Handle error
                            _uiState.value = RecipeListState(
                                isLoading = false,
                                isError = true,
                                errorMessage = networkData.message,
                                recipeModel = null
                            )
                        }

                        ApiResult.Loading -> {
                            _uiState.value = RecipeListState(
                                isLoading = true,
                                isError = false,
                                errorMessage = null,
                                recipeModel = null
                            )
                        }
                    }
                }
            }

        }

    }

}