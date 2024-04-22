package edu.quinnipiac.ser210.myapplication.APIData
/*
  * Gabby Pierce and Sam Woodburn
  * Final Project SER210
  * Gym Genie
  * Exercise repository: makes api client, retrieves and holds data
 */

import android.util.Log
import edu.quinnipiac.ser210.myapplication.APIData.ApiInterface
import edu.quinnipiac.ser210.myapplication.APIData.ExerciseJSON

class ExerciseRepository {
    //api instance
    private val apiClient = ApiInterface.ApiClient.instance

    suspend fun getExercises(bodyPart: String): ExerciseJSON {
        //check if api call is a successfull list, not null
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
}