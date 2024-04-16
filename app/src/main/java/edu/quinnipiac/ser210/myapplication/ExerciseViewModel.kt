package edu.quinnipiac.ser210.myapplication

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.quinnipiac.ser210.myapplication.APIData.ExerciseItem
import edu.quinnipiac.ser210.myapplication.APIData.ExerciseJSON
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/*
  * Gabby Pierce and Sam Woodburn
  * Final Project SER210
  * Gym Genie
  * Exercise view model: to monitor the live data changes to this list
 */
/*
class ExerciseViewModel (private val repository: ExerciseRepository) : ViewModel() {
    //live data variables
    private val _allExerciseList = MutableLiveData<ExerciseJSON>()
    var allExerciseList: LiveData<ExerciseJSON> = _allExerciseList

    //calls the repository to fetch job listings
    fun getExercises(bodyPart: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getExercises(bodyPart)
                Log.d("VIEW_MODEL: ", "API call success")

                // Switch to Main thread to update UI components
                withContext(Dispatchers.Main) {
                    if (response != null) {
                        Log.d("VIEW_MODEL:", "Posting ${response.size} hits to LiveData")
                        _allExerciseList.value = response
                    } else {
                        Log.d("VIEW_MODEL:", "No exercises found")
                    }
                }
            } catch (e: Exception) {
                Log.d("VIEW_MODEL", "API call failure: ${e.message}")
                withContext(Dispatchers.Main) {
                    _allExerciseList.value = listOf() // Posting empty list or handling error state
                }
            }
        }
    }

}

 */

class ExerciseViewModel(private val repository: ExerciseRepository) : ViewModel() {
    private val _allExerciseList = MutableLiveData<List<ExerciseItem>>()
    val allExerciseList: LiveData<List<ExerciseItem>> = _allExerciseList

    // Optionally, add LiveData for error or loading states
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getExercises(bodyPart: String) {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getExercises(bodyPart)
                Log.d("VIEW_MODEL: ", "API call success")

                withContext(Dispatchers.Main) {
                    _isLoading.value = false
                    _allExerciseList.postValue(response)
                    //_allExerciseList.value = response ?: listOf()
                }
            } catch (e: Exception) {
                Log.d("VIEW_MODEL", "API call failure: ${e.message}")
                withContext(Dispatchers.Main) {
                    _isLoading.value = false
                    _error.value = "Failed to load exercises: ${e.message}"
                    _allExerciseList.value = listOf() // Indicate no data
                }
            }
        }
    }
}
