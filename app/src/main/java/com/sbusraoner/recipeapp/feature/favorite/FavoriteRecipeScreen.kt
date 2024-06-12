package com.sbusraoner.recipeapp.feature.favorite

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteRecipeScreen(
    navController: NavController,
    viewModel: FavoriteRecipeViewModel = hiltViewModel(),
    onBack: () -> Unit,
) {
    val state = viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getAllFavoriteRecipes()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(10.dp),
                title = {
                    Text(
                        text = "Favorite Recipe List",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(Color.White),
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
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
                        .padding(8.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    state.value.recipeModel?.forEach { recipe ->
                        Card(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth()
                                .clickable {
                                    Log.e("BusraResponse", (recipe?.id ?: "bos").toString())
                                    navController.navigate("RECIPE_DETAIL/${recipe?.id}")
                                },
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .clip(RoundedCornerShape(8.dp))

                                ) {
                                    AsyncImage(
                                        model = recipe?.image ?: "",
                                        contentDescription = "image",
                                    )
                                }
                                Column(modifier = Modifier.fillMaxWidth()) {
                                    Text(
                                        text = recipe?.title ?: "",
                                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Light),
                                        modifier = Modifier.align(Alignment.CenterHorizontally)
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))

                                    Icon(imageVector = Icons.Default.Delete,
                                        contentDescription = "Delete",
                                        modifier = Modifier
                                            .clickable {
                                                viewModel.deleteFavoriteRecipe(recipe?.id ?: 0)
                                            }
                                            .align(Alignment.End))

                                }
                            }


                        }
                    }
                    state.value.errorMessage?.let {
                        Log.e("BusraResponse", it ?: "error bos")
                    }
                }
            }
        }
    }


}