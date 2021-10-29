package com.usc.workoutapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.usc.workoutapp.utils.ActivityDetails

/*
* This class is responsible for Database Handling.
* It defines DB/TABLE Names & provides DB Instance.
* It maintains a Single DB Object.
*
* */
@Database(entities = [ActivityDetails::class], version = 1, exportSchema = false)
abstract class DBHandler : RoomDatabase() {

    abstract fun daoAccess(): DaoAccess?

    companion object {

        const val DB_NAME = "local_db"
        const val TABLE_NAME = "activities"

        private var INSTANCE: DBHandler? = null

        // Singleton Instance
        fun getInstance(context: Context): DBHandler? {
            if (INSTANCE == null) {
                synchronized(DBHandler::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        DBHandler::class.java,
                        DB_NAME
                    ).allowMainThreadQueries()
                        .build()
                }
            }

            return INSTANCE
        }
    }
}