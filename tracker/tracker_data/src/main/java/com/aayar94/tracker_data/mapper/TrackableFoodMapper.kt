package com.aayar94.tracker_data.mapper

import com.aayar94.tracker_domain.model.TrackableFood
import com.plcoding.tracker_data.remote.dto.Product
import kotlin.math.roundToInt


fun Product.toTrackableFood(): TrackableFood? {
    val carbsPer100g = nutriments.carbohydrates100g.roundToInt()
    val proteinPer100g = nutriments.proteins100g.roundToInt()
    val fatPer100g = nutriments.fat100g.roundToInt()
    val calories = nutriments.energyKcal100g.roundToInt()

    return TrackableFood(
        name = productName ?: return null,
        imageUrl = imageFrontThumbUrl,
        carbsPer100g = carbsPer100g,
        proteinPer100g = proteinPer100g,
        fatPer100g = fatPer100g,
        caloriesPer100g = calories
    )
}