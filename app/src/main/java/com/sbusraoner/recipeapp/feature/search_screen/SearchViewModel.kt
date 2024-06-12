package com.sbusraoner.recipeapp.feature.search_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sbusraoner.recipeapp.data.SpoonacularRepository
import com.sbusraoner.recipeapp.models.Result
import com.sbusraoner.recipeapp.utils.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SearchState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null,
    val recipeModel: List<Result?>? = null
)

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SpoonacularRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchState())
    val uiState: StateFlow<SearchState> = _uiState

    fun getSearchRecipe(query: String) {
        val internetOnline = true
        viewModelScope.launch {

            _uiState.update { state ->
                state.copy(isLoading = true)
            }
            if (internetOnline) {

                repository.getSearchRecipe(query).collect { networkData ->
                    when (networkData) {
                        is ApiResult.Success -> {
                            // Do something with the data
                            _uiState.value = SearchState(
                                isLoading = false,
                                isError = false,
                                errorMessage = null,
                                recipeModel = networkData.data?.results
                            )
                        }

                        is ApiResult.Error -> {
                            // Handle error
                            _uiState.value = SearchState(
                                isLoading = false,
                                isError = true,
                                errorMessage = networkData.message,
                                recipeModel = null
                            )
                        }

                        ApiResult.Loading -> {
                            _uiState.value = SearchState(
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