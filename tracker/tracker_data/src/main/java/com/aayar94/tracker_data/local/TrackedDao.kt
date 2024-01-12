package com.aayar94.tracker_data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aayar94.tracker_data.local.entity.TrackedFoodEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrackedFood(trackedFoodEntity: TrackedFoodEntity)

    @Delete
    suspend fun deleteFood(entity: TrackedFoodEntity)

    @Query("SELECT * " +
            "FROM trackedfoodentity " + //TODO check if not work
            "WHERE dayOfMonth= :day AND month= :month AND year= :year")
    fun getFoodsForDate(day: Int, month: Int, year:Int): Flow<List<TrackedFoodEntity>>


}