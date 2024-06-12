package com.sbusraoner.recipeapp.feature.recipe_list

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.sbusraoner.recipeapp.feature.components.CustomTopBar



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeListScreen(
    navController: NavController,
    viewModel: RecipeListViewModel = hiltViewModel(),
    type : String,
    onBack : () -> Unit,
    onFavoriteClick : () -> Unit)
{
    val state = viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getRecipeWithMealType(type)
    }

    Scaffold(
        topBar = {
            CustomTopBar(
                text = "Recipe List",
                navigationIcon = Icons.Default.ArrowBack,
                onNavigationClick = onBack,
                onFavoriteClick = onFavoriteClick
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
                                .fillMaxSize()
                                .clickable {
                                    Log.e("BusraResponse", (recipe?.id ?: "bos").toString())
                                    navController.navigate("RECIPE_DETAIL/${recipe?.id}")

                                },
                            //elevation = CardDefaults.cardElevation(8.dp),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically){
                                Box(
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .clip(RoundedCornerShape(8.dp))) {
                                    AsyncImage(model = recipe?.image ?: "", contentDescription = "image")
                                }
                                Text(text = recipe?.title ?: "",
                                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Light))
                                Spacer(modifier = Modifier.height(8.dp))
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
