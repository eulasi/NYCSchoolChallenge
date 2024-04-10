package com.clone.schoolsapplicationtwo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.clone.schoolsapplicationtwo.data.SchoolsItem
import com.clone.schoolsapplicationtwo.repository.SchoolsRepository
import com.clone.schoolsapplicationtwo.ui.vm.SchoolsViewModel
import com.clone.schoolsapplicationtwo.util.getOrAwaitValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SchoolsViewModelTest {

    @Mock
    private lateinit var schoolsRepository: SchoolsRepository

    private lateinit var viewModel: SchoolsViewModel

    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = SchoolsViewModel(schoolsRepository, testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `schools live data is updated on successful repository response`() =
        runBlocking(testDispatcher) {
            val mockSchools = listOf(SchoolsItem())

            Mockito.`when`(schoolsRepository.getSchools()).thenReturn(Result.success(mockSchools))

            viewModel.schools.getOrAwaitValue()

            delay(500)  // Allow some time for the coroutine to complete

            val schools = viewModel.schools.value
            Assert.assertEquals(mockSchools, schools)
        }

    @Test
    fun `schools live data is not updated on repository failure`() = runBlocking(testDispatcher) {
        val exception = IOException("Network error")
        Mockito.`when`(schoolsRepository.getSchools()).thenReturn(Result.failure(exception))

        viewModel.schools.getOrAwaitValue()

        val schools = viewModel.schools.value
        if (schools != null) {
            Assert.assertTrue(schools.isEmpty())
        }
    }
}
