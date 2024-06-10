package com.sbusraoner.recipeapp.feature.detail

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.sbusraoner.recipeapp.R
import com.sbusraoner.recipeapp.feature.components.CustomTopBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailScreen(
    viewModel: RecipeDetailViewModel = hiltViewModel(),
    onRecipeClick: (Int) -> Unit,
    id: Int,
    onBack : () -> Unit
) {

    val state = viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getRecipeWithID(id ?: 0)

    }

    Scaffold(
        topBar = {
            CustomTopBar(
                text = state.value.recipeModel?.title ?: "Recipe Detail",
                navigationIcon = Icons.Default.ArrowBack,
                onNavigationClick = onBack
            )
        }
    ) {
        Box {
            if (state.value.isLoading) {
                Text(text = "Loading...",
                    modifier = Modifier.align(Alignment.Center))
            }
            else {
                if (state.value.isError) {
                    Text(text = "Error",
                        modifier = Modifier.align(Alignment.Center))
                    Log.e("RecipeDetailScreen", "Error: ${state.value.errorMessage}")
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    state.value.recipeModel?.let { recipe ->
                        //Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = recipe.title ?: "Recipe Detail",
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                        )
                        Text(
                            text = "CuisinesType",
                            style = MaterialTheme.typography.bodyLarge
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        AsyncImage(
                            model = recipe.image,
                            contentDescription = null,
                            modifier = Modifier
                                .height(200.dp)
                                .fillMaxWidth(),
                            contentScale = ContentScale.Crop )

                        Spacer(modifier = Modifier.height(16.dp))

                        Column {
                            Text(
                                text = "Ingredients",
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            state.value.recipeModel?.ingredients?.forEach { ingredient ->
                            }
                            IngredientItem("Butter", "50g")
                            IngredientItem("Peanut Cookies", "175g")

                        }
                        Text(text = "Instructions:", style = MaterialTheme.typography.titleLarge)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = recipe.summary ?: "No summary available" ,
                            style = MaterialTheme.typography.bodyLarge)
                    }
                }
            }
        }
    }



}


@Composable
fun IngredientItem(name: String, quantity: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = name, style = MaterialTheme.typography.bodyLarge)
        Text(text = quantity, style = MaterialTheme.typography.bodyLarge)
    }
}
