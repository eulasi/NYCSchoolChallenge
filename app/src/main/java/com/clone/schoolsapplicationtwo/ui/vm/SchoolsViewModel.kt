package com.clone.schoolsapplicationtwo.ui.vm
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clone.schoolsapplicationtwo.data.SchoolsItem
import com.clone.schoolsapplicationtwo.repository.SchoolsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SchoolsViewModel(
    private val repository: SchoolsRepository,
    dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    private val _schools = MutableLiveData<List<SchoolsItem>>()
    val schools: LiveData<List<SchoolsItem>> = _schools

    init {
        viewModelScope.launch(dispatcher) {
            try {
                val result = repository.getSchools()

                result.onSuccess { schoolsList ->
                    _schools.postValue(schoolsList)
                }
                result.onFailure { error ->
                    error.printStackTrace()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
