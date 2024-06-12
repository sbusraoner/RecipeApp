package com.sbusraoner.recipeapp.feature.search_screen

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
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.collectAsState()
    var text by remember { mutableStateOf("") }

    Scaffold { it

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(it))
        {
            TextField(
                value = text,
                onValueChange = { text = it
                    viewModel.getSearchRecipe(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .clip(RoundedCornerShape(10.dp)),
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = null)
                },
                label = { Text("Search") }
            )
            Spacer(modifier = Modifier.height(8.dp))

            if (state.value.isLoading) {
                Text(
                    text = "Loading...",
                    textAlign = TextAlign.Center,
                )
            } else {
                if (state.value.isError) {
                    Text(
                        text = "Error",
                        textAlign = TextAlign.Center,
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

                                }
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically){
                                Box(
                                    modifier = Modifier
                                        .padding(8.dp)
                                        //.size(100.dp)
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