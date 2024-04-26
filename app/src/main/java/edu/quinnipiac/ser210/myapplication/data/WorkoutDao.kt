package edu.quinnipiac.ser210.myapplication.data
/*
  * Gabby Pierce and Sam Woodburn
  * Final Project SER210
  * Gym Genie
  * Workout DAO
 */

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import retrofit2.http.DELETE

@Dao
interface WorkoutDao {
    //add a workout
    @Insert
    suspend fun insertWorkout(workout: Workout): Long

    //get list  of all workouts
    @Query("SELECT * FROM workouts")
    fun getAllWorkouts(): LiveData<MutableList<Workout>>

    //delete a workout
    @Delete
    fun delete(workouts: Workout)

    //delete all workouts
    @Query("DELETE FROM workouts")
    suspend fun deleteAll()
}