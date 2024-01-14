package com.aayar94.calorytracker.repository

import com.aayar94.tracker_domain.model.TrackableFood
import com.aayar94.tracker_domain.model.TrackedFood
import com.aayar94.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import java.time.LocalDate
import kotlin.random.Random

class TrackerRepositoryFake : TrackerRepository {

    val shouldReturnError: Boolean = false
    private val trackedFood = mutableListOf<TrackedFood>()
    var searchResults = listOf<TrackableFood>()
    private val getFoodsForDate = MutableSharedFlow<List<TrackedFood>>(replay = 1)
    override suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>> {
        return if (shouldReturnError) {
            Result.failure(Throwable())
        } else {
            Result.success(searchResults)
        }
    }

    override suspend fun insertTrackedFood(food: TrackedFood) {
        trackedFood.add(food.copy(id = Random.nextInt()))
        getFoodsForDate.emit(trackedFood)
    }

    override suspend fun deleteTrackedFood(food: TrackedFood) {
        trackedFood.remove(food)
        getFoodsForDate.emit(trackedFood)
    }

    override fun getFoodsForDate(localDate: LocalDate): Flow<List<TrackedFood>> {
        return getFoodsForDate
    }
}