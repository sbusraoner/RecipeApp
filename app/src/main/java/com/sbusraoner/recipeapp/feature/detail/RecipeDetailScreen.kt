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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.sharp.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.sbusraoner.recipeapp.R
import com.sbusraoner.recipeapp.feature.components.CustomTopBar
import kotlinx.coroutines.launch
import org.jsoup.Jsoup


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailScreen(
    viewModel: RecipeDetailViewModel = hiltViewModel(),
    onFavoriteClick: () -> Unit,
    onRecipeClick: (Int) -> Unit,
    id: Int,
    onBack : () -> Unit,
) {

    val state = viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.getRecipeWithID(id ?: 0)

    }

    Scaffold(

        topBar = {
            CustomTopBar(
                text = "Recipe Detail",
                navigationIcon = Icons.Default.ArrowBack,
                onNavigationClick = onBack,
                onFavoriteClick = { onFavoriteClick() },
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        floatingActionButton = {
            if(!state.value.isFavorite) {
                IconButton(onClick = {
                    state.value.recipeModel?.let {
                        viewModel.insertRecipes(it)
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Recipe added to favorites")
                        }
                    }
                },
                    modifier = Modifier.size(56.dp) ) {
                    Icon(imageVector =
                    Icons.Filled.AddCircle,contentDescription = null)
                }
            }
        },
        floatingActionButtonPosition = androidx.compose.material3.FabPosition.End,

    ) {
        Box(modifier = Modifier.padding(it)) {
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
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        AsyncImage(
                            model = recipe.image,
                            contentDescription = null,
                            modifier = Modifier
                                .height(200.dp)
                                .fillMaxSize()
                                .clip(MaterialTheme.shapes.medium),
                            contentScale = ContentScale.Crop )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Ingredients",
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                        )

                        Column(modifier = Modifier.fillMaxWidth()) {

                            Spacer(modifier = Modifier.height(8.dp))
                            state.value.recipeModel?.ingredients?.forEach { ingredient ->
                                Row(){
                                    Icon(imageVector = Icons.Sharp.KeyboardArrowRight,contentDescription = null)
                                    Text(text = ingredient.aisle ?: "",
                                        style = MaterialTheme.typography.bodyLarge)

                                }
                            }
                        }
                        Text(text = "Instructions",
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold))
                        Spacer(modifier = Modifier.height(8.dp))

                        //Text(text = recipe.instructions ?: "No instructions available" ,
                        //    style = MaterialTheme.typography.bodyLarge)

                        state.value.recipeModel?.analyzedInstructions?.forEach { instruction ->
                            instruction?.steps?.forEach { step ->
                                Row(modifier = Modifier
                                    .padding(8.dp)
                                    .fillMaxWidth()) {
                                    Text(
                                        text = "${step?.number}." ?: "",
                                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold))
                                    Text(
                                        text = step?.step ?: "",
                                        style = MaterialTheme.typography.bodyLarge)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
