package com.aayar94.tracker_presentation.tracker_overview

import androidx.annotation.DrawableRes
import com.aaayar94.core.util.UiText
import com.aayar94.tracker_domain.model.MealType

data class Meal(
    val name: UiText,
    @DrawableRes val drawableRes: Int,
    val mealType: MealType,
    var carbs: Int = 0,
    var protein: Int = 0,
    var fat: Int = 0,
    var calories: Int = 0,
    val isExpanded: Boolean = false
)


val defaultMeals = listOf(
    Meal(
        name = UiText.StringResource(com.aayar94.core.R.string.breakfast),
        drawableRes = com.aayar94.core.R.drawable.breakfast,
        mealType = MealType.Breakfast
    ),
    Meal(
        name = UiText.StringResource(com.aayar94.core.R.string.lunch),
        drawableRes = com.aayar94.core.R.drawable.lunch,
        mealType = MealType.Lunch
    ),
    Meal(
        name = UiText.StringResource(com.aayar94.core.R.string.dinner),
        drawableRes = com.aayar94.core.R.drawable.dinner,
        mealType = MealType.Dinner
    ),
    Meal(
        name = UiText.StringResource(com.aayar94.core.R.string.snacks),
        drawableRes = com.aayar94.core.R.drawable.snack,
        mealType = MealType.Snack
    )
)