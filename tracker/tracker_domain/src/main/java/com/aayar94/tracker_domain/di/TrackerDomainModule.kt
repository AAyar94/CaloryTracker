package com.aayar94.tracker_domain.di

import com.aaayar94.core.domain.preferences.Preferences
import com.aayar94.tracker_domain.repository.TrackerRepository
import com.aayar94.tracker_domain.use_case.CalculateMealNutrients
import com.aayar94.tracker_domain.use_case.DeleteTrackedFoodUseCase
import com.aayar94.tracker_domain.use_case.GetFoodsForDateUseCase
import com.aayar94.tracker_domain.use_case.SearchFoodUseCase
import com.aayar94.tracker_domain.use_case.TrackFoodUseCase
import com.aayar94.tracker_domain.use_case.TrackerUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object TrackerDomainModule {

    @ViewModelScoped
    @Provides
    fun provideTrackerUseCases(
        repository: TrackerRepository,
        preferences: Preferences
    ): TrackerUseCases {
        return TrackerUseCases(
            trackFoodUseCase = TrackFoodUseCase(
                repository = repository
            ),
            searchFoodUseCase = SearchFoodUseCase(
                repository = repository
            ),
            getFoodsForDateUseCase = GetFoodsForDateUseCase(
                repository = repository
            ),
            deleteTrackedFoodUseCase = DeleteTrackedFoodUseCase(
                repository = repository
            ),
            calculateMealNutrients = CalculateMealNutrients(
                preferences = preferences
            )
        )
    }

}