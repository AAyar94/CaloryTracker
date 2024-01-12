package com.aayar94.tracker_domain.use_case

import com.aayar94.tracker_domain.model.TrackedFood
import com.aayar94.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class GetFoodsForDateUseCase(
    private val repository: TrackerRepository
) {

    operator fun invoke(
        date: LocalDate
    ): Flow<List<TrackedFood>> {
        return repository.getFoodsForDate(date)
    }

}