package com.aayar94.tracker_data.repository

import com.aayar94.tracker_data.local.TrackerDao
import com.aayar94.tracker_data.mapper.toTrackableFood
import com.aayar94.tracker_data.mapper.toTrackedFood
import com.aayar94.tracker_data.mapper.toTrackedFoodEntity
import com.aayar94.tracker_data.remote.OpenFoodApi
import com.aayar94.tracker_domain.model.TrackableFood
import com.aayar94.tracker_domain.model.TrackedFood
import com.aayar94.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

class TrackerRepositoryImpl(
    private val dao: TrackerDao,
    private val api: OpenFoodApi
) : TrackerRepository {
    override suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>> {
        return try {
            val searchDto = api.searchFood(
                query = query,
                page = page,
                pageSize = pageSize
            )
            Result.success(searchDto.products.mapNotNull {
                it.toTrackableFood()
            })
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun insertTrackedFood(food: TrackedFood) {
        dao.insertTrackedFood(food.toTrackedFoodEntity())
    }

    override suspend fun deleteTrackedFood(food: TrackedFood) {
        dao.deleteFood(food.toTrackedFoodEntity())
    }

    override fun getFoodsForDate(localDate: LocalDate): Flow<List<TrackedFood>> {
        return dao.getFoodsForDate(
            day = localDate.dayOfMonth,
            month = localDate.monthValue,
            year = localDate.year
        ).map { entities ->
            entities.map { it.toTrackedFood() }
        }
    }
}