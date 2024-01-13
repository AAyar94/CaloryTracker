package com.aayar94.tracker_presentation.components

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Canvas
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import com.aayar94.core_ui.CarbColor
import com.aayar94.core_ui.FatColor
import com.aayar94.core_ui.ProteinColor

@Composable
fun NutrientsBar(
    carbs: Int,
    protein: Int,
    fat: Int,
    calories: Int,
    caloriesGoal: Int,
    modifier: Modifier = Modifier
) {
    val background = MaterialTheme.colors.background
    val caloriesExceedColor = MaterialTheme.colors.error
    val carbWithRatio = remember {
        Animatable(0f)
    }
    val proteinWithRatio = remember {
        Animatable(0f)
    }
    val fatWithRatio = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = carbs) {
        carbWithRatio.animateTo(
            targetValue = ((carbs * 4f) / caloriesGoal)
        )
    }
    LaunchedEffect(key1 = protein) {
        proteinWithRatio.animateTo(
            targetValue = ((protein * 4f) / caloriesGoal)
        )
    }
    LaunchedEffect(key1 = fat) {
        fatWithRatio.animateTo(
            targetValue = ((fat * 9f) / caloriesGoal)
        )
    }
    Canvas(modifier = modifier) {
        if (calories <= caloriesGoal) {
            val carbsWidth = carbWithRatio.value * size.width
            val proteinWidth = proteinWithRatio.value * size.width
            val fatWidth = fatWithRatio.value * size.width

            drawRoundRect(color = background, size = size, cornerRadius = CornerRadius(100f))
            drawRoundRect(
                color = FatColor,
                size = Size(width = carbsWidth + proteinWidth + fatWidth, height = size.height),
                cornerRadius = CornerRadius(100f)
            )
            drawRoundRect(
                color = ProteinColor,
                size = Size(width = carbsWidth + proteinWidth, height = size.height),
                cornerRadius = CornerRadius(100f)
            )
            drawRoundRect(
                color = CarbColor,
                size = Size(width = carbsWidth, height = size.height),
                cornerRadius = CornerRadius(100f)
            )
        } else {
            drawRoundRect(
                color = caloriesExceedColor,
                size = size,
                cornerRadius = CornerRadius(100f)
            )
        }
    }
}