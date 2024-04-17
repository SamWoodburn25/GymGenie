package edu.quinnipiac.ser210.myapplication

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://gym-fit.r.apidapi.com/") // Use the base URL of your API
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: GymApiService = retrofit.create(GymApiService::class.java)
}