package com.aayar94.tracker_domain.use_case

class TrackerUseCases(
    val trackFoodUseCase: TrackFoodUseCase,
    val searchFoodUseCase: SearchFoodUseCase,
    val getFoodsForDateUseCase: GetFoodsForDateUseCase,
    val deleteTrackedFoodUseCase: DeleteTrackedFoodUseCase,
    val calculateMealNutrients: CalculateMealNutrients
) {
}