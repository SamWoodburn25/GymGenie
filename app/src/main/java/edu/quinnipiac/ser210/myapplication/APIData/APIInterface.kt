package edu.quinnipiac.ser210.myapplication.APIData
/*
  * Gabby Pierce and Sam Woodburn
  * Final Project SER210
  * Gym Genie
  * API interface: connect to rapid api host
 */

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

//interface
interface ApiInterface {

    //headers
    @Headers(
        "X-RapidAPI-Host: gym-fit.p.rapidapi.com",
        "X-RapidAPI-Key: 1ed9d7cc95mshde0f3be1c7ed937p103577jsn1d49282e63bd"
    )

    //using the search exercises endpoint
    @GET("exercises/search")
    //get exercises from api method, using body part query
    suspend fun getExercises(
        @Query("bodyPart") bodyPart: String
    ): Response<ExerciseJSON>


    object ApiClient {
        //log the info
        private val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        private val httpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        //implement api with retrofit
        val instance: ApiInterface by lazy {
            Retrofit.Builder()
                .baseUrl("https://gym-fit.p.rapidapi.com/")
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiInterface::class.java)
        }

    }
}
