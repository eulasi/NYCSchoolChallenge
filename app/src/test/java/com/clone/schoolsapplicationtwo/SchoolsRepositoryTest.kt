package com.clone.schoolsapplicationtwo

import com.clone.schoolsapplicationtwo.data.SchoolsApi
import com.clone.schoolsapplicationtwo.data.SchoolsItem
import com.clone.schoolsapplicationtwo.repository.SchoolsRepository
import com.clone.schoolsapplicationtwo.repository.SchoolsRepositoryImpl
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import java.io.IOException


@RunWith(MockitoJUnitRunner::class)
class SchoolsRepositoryTest {

    @Mock
    private lateinit var schoolsApi: SchoolsApi

    private lateinit var schoolsRepository: SchoolsRepository

    @Before
    fun setUp() {
        schoolsRepository = SchoolsRepositoryImpl(schoolsApi)
    }


    @Test
    fun `getSchools returns success result`() = runBlocking {
        val mockSchools = listOf<SchoolsItem>()
        Mockito.`when`(schoolsApi.getSchools()).thenReturn(Response.success(mockSchools))

        val result = schoolsRepository.getSchools()

        Assert.assertTrue(result.isSuccess)
        Assert.assertEquals(mockSchools, result.getOrNull())
    }

    @Test
    fun `getSchools returns failure result on error response`() = runBlocking {
        val mockResponse: Response<List<SchoolsItem>> = Response.error(404, "".toResponseBody(null))
        Mockito.`when`(schoolsApi.getSchools()).thenReturn(mockResponse)

        val result = schoolsRepository.getSchools()

        Assert.assertTrue(result.isFailure)
    }

    @Test
    fun `getSchools returns failure result on exception`() = runBlocking {
        val exception = IOException("Network error")
        Mockito.`when`(schoolsApi.getSchools()).thenAnswer { throw exception }

        val result = schoolsRepository.getSchools()

        Assert.assertTrue(result.isFailure)
        Assert.assertEquals(exception, result.exceptionOrNull())
    }


}