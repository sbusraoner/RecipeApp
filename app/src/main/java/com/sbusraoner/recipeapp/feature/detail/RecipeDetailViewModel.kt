package com.sbusraoner.recipeapp.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sbusraoner.recipeapp.data.network.DetailRootResponse
import com.sbusraoner.recipeapp.data.network.SpoonacularRepository
import com.sbusraoner.recipeapp.models.Result
import com.sbusraoner.recipeapp.utils.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


data class RecipeDetailState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null,
    val recipeModel: DetailRootResponse? = null
)

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val repository: SpoonacularRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(RecipeDetailState())
    val uiState: StateFlow<RecipeDetailState> = _uiState

    fun getRecipeWithID(id:Int) {
        val internetOnline = true

        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(isLoading = true)
            }
            if (internetOnline) {
                repository.getRecipeWihtId(id).collect { networkData ->
                    when (networkData) {
                        is ApiResult.Success -> {
                            // Do something with the data
                            _uiState.value = RecipeDetailState(
                                isLoading = false,
                                isError = false,
                                errorMessage = null,
                                recipeModel = networkData.data
                            )
                        }

                        is ApiResult.Error -> {
                            // Handle error
                            _uiState.value = RecipeDetailState(
                                isLoading = false,
                                isError = true,
                                errorMessage = networkData.message,
                                recipeModel = null
                            )
                        }

                        ApiResult.Loading -> {
                            _uiState.value = RecipeDetailState(
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