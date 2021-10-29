package com.usc.workoutapp.database

import androidx.room.*
import com.usc.workoutapp.utils.ActivityDetails

/*
* This Interface is used to interact Data in local DB.
* It inserts/updates/deletes/queries data from DB.
*
* */
@Dao
interface DaoAccess {

    @Insert
    fun insertWorkout(activity: ActivityDetails?): Long

    @Query("select * from " + DBHandler.TABLE_NAME)
    fun fetchActivities(): List<ActivityDetails?>?

    @Query("select * from " + DBHandler.TABLE_NAME + " where _id=:actPos")
    fun fetchActivity(actPos: Int): ActivityDetails

    @Update()
    fun updateActivity(activity: ActivityDetails?): Int

    @Delete
    fun deleteActivity(activity: ActivityDetails?): Int
}