package edu.quinnipiac.ser210.myapplication.data
/*
  * Gabby Pierce and Sam Woodburn
  * Final Project SER210
  * Gym Genie
  * AppDatabase: gym genies database containing a workout entity
 */

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Workout::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun workoutDao(): WorkoutDao
    
}