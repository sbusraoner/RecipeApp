package com.sbusraoner.recipeapp.feature.components

import com.sbusraoner.recipeapp.R

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CustomSearchBar(onClick : () -> Unit) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .background(Color(0xFFEBE0F2), shape = RoundedCornerShape(8.dp)) // Light purple background with rounded corners
            .border(1.dp, Color(0xFF9575CD), shape = RoundedCornerShape(8.dp)) // Purple border
            .fillMaxWidth()
            .height(48.dp)
            .clickable {
                onClick()
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxHeight()
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = Color(0xFF5E5E5E) // Dark gray icon color
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Search",
                color = Color(0xFF5E5E5E), // Dark gray text color
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
            )
        }
    }
}

@Composable
fun CategoryItem(name:String, imageRes:Int, onCategoryClick: (String) -> Unit) {
    Column(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()
        .clickable {
            onCategoryClick(name)
        }) {
        Image(painter = painterResource(id = imageRes),
            contentDescription = "categoryImage",
            modifier = Modifier
                .height(100.dp)
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop)

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = name,fontSize = 16.sp,fontWeight = FontWeight.Bold)

    }
}

@Composable
fun CategoryGrid(onCategoryClick: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "All the recipes you are looking for are here!",modifier = Modifier.padding(10.dp))
        Spacer(modifier = Modifier.height(16.dp))
        val categories = listOf(
            "Breakfast" to R.drawable.breakfast,
            "Finger Food" to R.drawable.fingerfood,
            "Snack" to R.drawable.snack,
            "Dessert" to R.drawable.dessert,
            "Salad" to R.drawable.salad,
            "Drink" to R.drawable.drink,
            "Sauce" to R.drawable.sauce,
            "Soup" to R.drawable.soup,
            )

        LazyVerticalGrid(columns = GridCells.Fixed(2), contentPadding = PaddingValues(8.dp)) {
            items(categories){category ->
                CategoryItem(name = category.first, imageRes = category.second,onCategoryClick = onCategoryClick)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(
    text: String,
    navigationIcon: ImageVector,
    onNavigationClick: () -> Unit,
    onFavoriteClick : () -> Unit) {
    TopAppBar(
        modifier = Modifier.padding(10.dp),
        title = { Text(text = text,fontSize = 24.sp,fontWeight = FontWeight.Bold) },
        colors = TopAppBarDefaults.topAppBarColors(Color.White),
        navigationIcon = {
            IconButton(onClick = { onNavigationClick() }) {
                Icon(imageVector = navigationIcon,
                    contentDescription = null)
            }
        },
        actions = {
            IconButton(onClick = { onFavoriteClick()}) {
                Icon(Icons.Default.Favorite, contentDescription ="Favorite")
            }
            //Spacer(modifier = Modifier.height(0.dp))
            IconButton(onClick = { }) {
                Icon(Icons.Default.Notifications, contentDescription ="Search")
            }
        }
    )
}

@Composable
fun RecipeListItem(recipe: Recipe) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 16.dp),
        //elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(8.dp))) {
                Image(painter = painterResource(id = recipe.imageRes),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = recipe.name,fontSize = 18.sp,fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun RecipeList() {

    val recipeList = listOf(
        Recipe("Dessert",R.drawable.dessert),
        Recipe("Dessert",R.drawable.dessert),
    )

    LazyColumn(modifier = Modifier.padding(10.dp)) {
        items(recipeList) { recipe ->
            RecipeListItem(recipe)
        }
    }
}

data class Recipe(val name:String,val imageRes:Int)

@Preview
@Composable
private fun Prev() {
}
