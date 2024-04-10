package com.clone.schoolsapplicationtwo.repository

import com.clone.schoolsapplicationtwo.data.Schools
import com.clone.schoolsapplicationtwo.data.SchoolsApi
import java.io.IOException

interface SchoolsRepository {
    suspend fun getSchools(): Result<Schools>
}

class SchoolsRepositoryImpl constructor(
    private val schoolsApi: SchoolsApi
) : SchoolsRepository {
    override suspend fun getSchools(): Result<Schools> {
        return try {
            val response = schoolsApi.getSchools()

            if (response.isSuccessful) {
                Result.success(response.body() ?: throw IllegalStateException("Response is null"))
            } else {
                Result.failure(IOException(response.message()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}