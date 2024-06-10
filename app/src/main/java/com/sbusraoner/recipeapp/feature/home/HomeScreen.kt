package com.sbusraoner.recipeapp.feature.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.sbusraoner.recipeapp.feature.components.CategoryGrid
import com.sbusraoner.recipeapp.feature.components.Header
import com.sbusraoner.recipeapp.feature.components.MySearchBar

@Composable
fun HomeScreen(
    viewModel : HomeViewModel = hiltViewModel(),
    onCategoryClick: (String) -> Unit,
    onClick : () -> Unit
){


    Scaffold {
        Column(modifier = Modifier
            .padding(it)
            .fillMaxSize()) {
            Log.d("HomeScreen", "HomeScreen:  ")
            Header()
            MySearchBar(onClick = onClick)
            CategoryGrid(onCategoryClick = onCategoryClick)
        }
    }

}