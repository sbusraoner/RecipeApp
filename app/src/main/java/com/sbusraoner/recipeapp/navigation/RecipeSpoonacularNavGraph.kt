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
import com.sbusraoner.recipeapp.feature.favorite.FavoriteRecipeScreen
import com.sbusraoner.recipeapp.feature.home.HomeScreen
import com.sbusraoner.recipeapp.feature.recipe_list.RecipeListScreen
import com.sbusraoner.recipeapp.feature.search_screen.SearchScreen
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
            HomeScreen (
                onCategoryClick = {
                    navActions.navigateToRecipeList(it)
                },
                onClick = {
                    navActions.navigateToSearchScreen()
                },
                onFavoriteClick = {
                    navActions.navigateToFavScreen()
                }
            )
        }

        composable(
            route = RecipeAppDestination.SEARCH_SCREEN
        ) {
            SearchScreen(navController = navController)
        }

        composable(
            route = RecipeAppDestination.FAVORITE_SCREEN
        ) {
            FavoriteRecipeScreen(navController = navController) {
                navController.popBackStack()
            }
        }

        composable(
            route = RecipeAppDestination.RECIPE_LIST
        ) {
            val type = it.arguments?.getString("type") ?: "sauce"
            RecipeListScreen(
                navController = navController,
                type = type,
                onBack= { navController.popBackStack() },
                onFavoriteClick = {
                    navActions.navigateToFavScreen()
                })

        }


        composable(
            route = RecipeAppDestination.RECIPE_DETAIL
        ) {arguments ->
            val id = arguments.arguments?.getString("id") ?: "0"

            if(id.toInt() != 0) {

                RecipeDetailScreen(id = id.toInt(),
                    onFavoriteClick = {
                        navActions.navigateToFavScreen()
                    },
                    onRecipeClick = {
                    navActions.navigateToRecipeDetail(id = id.toInt())
                }) {
                    navController.popBackStack()
                }
            }

        }
    }
}