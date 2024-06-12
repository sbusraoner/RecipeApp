package com.sbusraoner.recipeapp.navigation

import android.util.Log
import androidx.navigation.NavHostController


object RecipeAppDestination {
    const val HOME = "home"
    const val RECIPE_DETAIL = "recipe_detail/{id}"
    const val RECIPE_LIST = "recipe_list/{type}"
    const val FAVORITE_SCREEN = "favorite_screen"
    const val SEARCH_SCREEN = "search_screen"

}

class RecipeSpoonacularNavigationActions(private val navController: NavHostController) {


    fun navigateToRecipeDetail(id: Int) {
        Log.v("SpoonacularNavigationActions", "navigateToRecipeDetail: $id")
        navController.navigate(
            RecipeAppDestination.RECIPE_DETAIL.replace("{id}", id.toString()),
        ) {
            popUpTo(RecipeAppDestination.HOME) {
                saveState = true
            }
        }
    }

    fun navigateToRecipeList(type: String) {
        navController.navigate(
            RecipeAppDestination.RECIPE_LIST.replace("{type}", type)
        ) {
            popUpTo(RecipeAppDestination.HOME) {
                saveState = true
            }
        }
    }

    fun navigateToSearchScreen() {
        navController.navigate(RecipeAppDestination.SEARCH_SCREEN)
    }

    fun navigateToFavScreen() {
        navController.navigate(RecipeAppDestination.FAVORITE_SCREEN)
    }
}