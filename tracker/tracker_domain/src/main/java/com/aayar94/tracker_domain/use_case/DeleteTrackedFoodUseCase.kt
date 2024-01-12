package com.aayar94.tracker_domain.use_case

import com.aayar94.tracker_domain.model.TrackedFood
import com.aayar94.tracker_domain.repository.TrackerRepository

class DeleteTrackedFoodUseCase(
    private val repository: TrackerRepository
) {

    suspend operator fun invoke(
        trackedFood: TrackedFood
    ) {
        return repository.deleteTrackedFood(trackedFood)
    }

}