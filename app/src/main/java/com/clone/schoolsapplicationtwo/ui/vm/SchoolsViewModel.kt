package com.clone.schoolsapplicationtwo.ui.vm
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clone.schoolsapplicationtwo.data.SchoolsApi
import com.clone.schoolsapplicationtwo.data.SchoolsItem
import com.clone.schoolsapplicationtwo.repository.SchoolsRepositoryImpl
import kotlinx.coroutines.launch

class SchoolsViewModel : ViewModel() {
    private val api = SchoolsApi.service
    private val repository = SchoolsRepositoryImpl(api)

    private val _schools = MutableLiveData<List<SchoolsItem>>()
    val schools: LiveData<List<SchoolsItem>> = _schools

    init {
        viewModelScope.launch {
            try {
                val result = repository.getSchools()
                result.onSuccess { schoolsList ->
                    _schools.postValue(schoolsList)
                }
                result.onFailure { error ->
                    // Handle error
                }
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}
