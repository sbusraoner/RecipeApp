package com.sbusraoner.recipeapp.feature.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.sbusraoner.recipeapp.feature.components.CategoryGrid
import com.sbusraoner.recipeapp.feature.components.CustomTopBar
import com.sbusraoner.recipeapp.feature.components.Header
import com.sbusraoner.recipeapp.feature.components.MySearchBar

@Composable
fun HomeScreen(
    viewModel : HomeViewModel = hiltViewModel(),
    onCategoryClick: (String) -> Unit,
    onClick : () -> Unit,
    onFavoriteClick : () -> Unit
){

    Scaffold(
        topBar = {
            CustomTopBar(
                text = "Hi Food Lovers",
                navigationIcon = Icons.Filled.Face,
                onNavigationClick = {},
                onFavoriteClick = onFavoriteClick)
        }
    ) {
        Column(modifier = Modifier
            .padding(it)
            .fillMaxSize()) {
            //Header()
            MySearchBar(onClick = onClick)
            CategoryGrid(onCategoryClick = onCategoryClick)
        }
    }

}
