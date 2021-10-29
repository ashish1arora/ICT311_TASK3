package com.usc.workoutapp.utils

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.usc.workoutapp.database.DBHandler

/*
* POJO class which defines an Activity.
* It also represents DB Schema for this APP.
*
* */
@Entity(tableName = DBHandler.TABLE_NAME)
data class ActivityDetails(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id") var _id: Int,
    @ColumnInfo(name = "act_title") var title: String,
    @ColumnInfo(name = "act_date") var date: String,
    @ColumnInfo(name = "act_place") var place: String,
    @ColumnInfo(name = "act_start_time") var starttime: String,
    @ColumnInfo(name = "act_end_time") var endtime: String,
    @ColumnInfo(name = "act_type") var type: String
)