package com.sbusraoner.recipeapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sbusraoner.recipeapp.feature.detail.RecipeDetailScreen
import com.sbusraoner.recipeapp.feature.home.HomeScreen
import com.sbusraoner.recipeapp.feature.recipe_list.RecipeListScreen
import kotlinx.coroutines.CoroutineScope

@Composable
fun RecipeSpoonacularNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    startDestination: String = RecipeAppDestination.HOME,
    navActions: RecipeSpoonacularNavigationActions = remember(navController) {
        RecipeSpoonacularNavigationActions(navController)
    }
){
    val currentNavigationBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentNavigationBackStackEntry?.destination?.route ?: startDestination

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {

        composable(
            route = RecipeAppDestination.SPLASH
        ) {

        }

        composable(
            route = RecipeAppDestination.HOME
        ) {
            HomeScreen {
                navActions.navigateToRecipeList(it)
            }
        }

        composable(
            route = RecipeAppDestination.RECIPE_LIST
        ) {
            val type = it.arguments?.getString("type") ?: "sauce"
            RecipeListScreen(
                type = type,
            )
        }

        composable(
            route = RecipeAppDestination.RECIPE_DETAIL
        ) {arguments ->
            val id = arguments.arguments?.getInt("id") ?: 0
            if(id != 0) {
                RecipeDetailScreen(id = id, onRecipeClick = {
                    navActions.navigateToRecipeDetail(id = it)
                })
            }

        }
    }
}