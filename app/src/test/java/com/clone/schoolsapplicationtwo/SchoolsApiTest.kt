package com.clone.schoolsapplicationtwo

import com.clone.schoolsapplicationtwo.data.SchoolsApi
import com.clone.schoolsapplicationtwo.data.SchoolsItem
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class SchoolsApiTest {

    @Mock
    private lateinit var schoolsApi: SchoolsApi

    @Test
    fun `getSchools returns successful response`() = runBlocking {
        val mockResponse = Response.success(listOf<SchoolsItem>())
        Mockito.`when`(schoolsApi.getSchools()).thenReturn(mockResponse)

        val response = schoolsApi.getSchools()

        Assert.assertTrue(response.isSuccessful)
        Assert.assertNotNull(response.body())
    }

    @Test
    fun `getSchools returns error response`() = runBlocking {
        val mockResponse: Response<List<SchoolsItem>> = Response.error(404, "".toResponseBody(null))
        Mockito.`when`(schoolsApi.getSchools()).thenReturn(mockResponse)

        val response = schoolsApi.getSchools()

        Assert.assertFalse(response.isSuccessful)
    }
}

