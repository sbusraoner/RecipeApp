package com.sbusraoner.recipeapp.feature.search_screen

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.sbusraoner.recipeapp.feature.components.MySearchBar

@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.getSearchRecipe("bread")
    }

    Scaffold {
        var text by remember { mutableStateOf("") }

        TextField(
            value = text,
            onValueChange = { text = it
                   viewModel.getSearchRecipe(it)
                            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .clip(RoundedCornerShape(10.dp)),
            label = { Text("Search") }
        )

        Box(
            modifier = Modifier
                .padding(it)
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
                                    navController.navigate("RECIPE_DETAIL/${recipe?.id}")

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