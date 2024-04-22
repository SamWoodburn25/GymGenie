package edu.quinnipiac.ser210.myapplication.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WorkoutDao {
    @Insert
    suspend fun insertWorkout(workout: Workout): Long

    @Query("SELECT * FROM workouts")
    fun getAllWorkouts(): LiveData<List<Workout>>
    //suspend fun getAllWorkouts(): List<Workout>
//    @Delete
//    fun delete(workouts: Workout)
}