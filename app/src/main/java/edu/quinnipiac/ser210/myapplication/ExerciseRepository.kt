package edu.quinnipiac.ser210.myapplication
/*
  * Gabby Pierce and Sam Woodburn
  * Final Project SER210
  * Gym Genie
  * Exercise repository: makes api client, retrieves and holds data; takes a workout dao object to access workout database
 */

import android.util.Log
import androidx.lifecycle.LiveData
import edu.quinnipiac.ser210.myapplication.APIData.ApiInterface
import edu.quinnipiac.ser210.myapplication.APIData.ExerciseJSON
import edu.quinnipiac.ser210.myapplication.data.Workout
import edu.quinnipiac.ser210.myapplication.data.WorkoutDao

class ExerciseRepository(private val apiClient: ApiInterface, private val workoutDao: WorkoutDao) {

    suspend fun getExercises(bodyPart: String): ExerciseJSON {//List<ExerciseItem> {
        //check if api call is a successful list, not null
        val response = apiClient.getExercises(bodyPart)
        if (response.isSuccessful && response.body() != null) {
            //log result
            Log.d("REPOSITORY", "API Response success: ${response.body()}")
            return response.body()!!
        } else {
            //log result
            Log.d("REPOSITORY", "API Response failure:")
            throw Exception("Failed to fetch exercises: ")
        }
    }

    //add workout to database
    suspend fun insertWorkout(workout: Workout) {
        workoutDao.insertWorkout(workout)
    }

    //get list of all currently saved workouts
    fun getAllSavedWorkouts(): LiveData<MutableList<Workout>> {
        return workoutDao.getAllWorkouts()
    }

    //clear database, delete everything
    suspend fun deleteAll(){
        workoutDao.deleteAll()
    }

    //delete an item
    suspend fun delete(workout: Workout){
        workoutDao.delete(workout)
    }
}