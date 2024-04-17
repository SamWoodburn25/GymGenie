package edu.quinnipiac.ser210.myapplication

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GymApiService {
    @GET("exercises/search")
    fun getExercisesByBodyPart(@Query("bodyPart") bodyPart: String): Call<List<Exercise>>
    //if not working try deleting call import and importing a new one
}