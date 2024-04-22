package edu.quinnipiac.ser210.myapplication.APIData

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

    @Headers(
        "X-RapidAPI-Host: gym-fit.p.rapidapi.com",
        "X-RapidAPI-Key: 1ed9d7cc95mshde0f3be1c7ed937p103577jsn1d49282e63bd"
    )
    //using the search exercises endpoint

    @GET("exercises/search")
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

        //implement api
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
