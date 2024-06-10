package com.sbusraoner.recipeapp.models.detailModel


import com.google.gson.annotations.SerializedName

data class DetailModel(
    @SerializedName("analyzedInstructions")
    val analyzedInstructions: List<Any?>?,
    @SerializedName("cookingMinutes")
    val cookingMinutes: Any?,
    @SerializedName("creditsText")
    val creditsText: String?,
    @SerializedName("cuisines")
    val cuisines: List<Any?>?,
    @SerializedName("dairyFree")
    val dairyFree: Boolean?,
    @SerializedName("diets")
    val diets: List<Any?>?,
    @SerializedName("dishTypes")
    val dishTypes: List<String?>?,
    @SerializedName("extendedIngredients")
    val extendedIngredients: List<ExtendedIngredient?>?,
    @SerializedName("gaps")
    val gaps: String?,
    @SerializedName("glutenFree")
    val glutenFree: Boolean?,
    @SerializedName("healthScore")
    val healthScore: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("imageType")
    val imageType: String?,
    @SerializedName("instructions")
    val instructions: String?,
    @SerializedName("occasions")
    val occasions: List<Any?>?,
    @SerializedName("originalId")
    val originalId: Any?,
    @SerializedName("preparationMinutes")
    val preparationMinutes: Any?,
    @SerializedName("pricePerServing")
    val pricePerServing: Double?,
    @SerializedName("readyInMinutes")
    val readyInMinutes: Int?,
    @SerializedName("servings")
    val servings: Int?,
    @SerializedName("sourceName")
    val sourceName: String?,
    @SerializedName("sourceUrl")
    val sourceUrl: String?,
    @SerializedName("summary")
    val summary: String?,
    @SerializedName("sustainable")
    val sustainable: Boolean?,
    @SerializedName("title")
    val title: String?,
)