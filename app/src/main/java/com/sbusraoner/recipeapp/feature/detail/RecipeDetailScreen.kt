package com.sbusraoner.recipeapp.feature.detail

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailScreen(
    viewModel: RecipeDetailViewModel = hiltViewModel(),
    onRecipeClick: (Int) -> Unit,
    id: Int
) {

    val state = viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getRecipeWithID(id ?: 0)

    }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = state.value.recipeModel?.firstOrNull()?.title ?: "Title")
            }
            )
        }

    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()

        ) {
            if (state.value.isLoading) {
                Text(
                    text = "Loading...",
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                if (state.value.isError) {
                    Text(
                        text = "Error",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {

                    state.value.recipeModel?.forEach { recipe ->
                        Card(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(0.8f)
                                .clickable {
                                    Log.e("BusraResponse", (recipe?.id ?: "bos").toString())

                                }
                        ) {
                            Box(modifier = Modifier.padding(8.dp)) {

                                Text(text = recipe?.title ?: "")
                                Spacer(modifier = Modifier.height(8.dp))
                                AsyncImage(model = recipe?.image ?: "", contentDescription = "image")



                            }
                        }
                    }
                    state.value.errorMessage?.let{
                        Log.e("BusraResponse",it ?: "error bos")
                    }
                }
            }
        }
    }

}
