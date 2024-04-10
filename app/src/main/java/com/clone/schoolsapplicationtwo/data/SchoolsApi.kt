package com.clone.schoolsapplicationtwo.data

import com.google.gson.GsonBuilder
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// Base URL: https://data.cityofnewyork.us/resource/
// Endpoint: s3k6-pzi2.json

interface SchoolsApi {
    @GET("s3k6-pzi2.json")
    suspend fun getSchools(): Response<Schools>

    companion object {
        private const val BASE_URL = "https://data.cityofnewyork.us/resource/"

        val service: SchoolsApi by lazy {
            val gson = GsonBuilder().create()

            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(SchoolsApi::class.java)
        }
    }
}