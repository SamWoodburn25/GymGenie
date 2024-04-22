package edu.quinnipiac.ser210.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import edu.quinnipiac.ser210.myapplication.APIData.ExerciseItem
import edu.quinnipiac.ser210.myapplication.databaseInfo.ExerciseDAO
import edu.quinnipiac.ser210.myapplication.databaseInfo.ExerciseInfo
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

/*
class SavedWorkoutsViewModel(private val exerciseDAO: ExerciseDAO): ViewModel(){
    fun addNewExercise(name: String, bodyPart: String, isSaved: Boolean){
        val newExercise = getNewExerciseEntry(name, bodyPart, isSaved)
        insertItem(newExercise)
    }

    private fun getNewExerciseEntry(name: String, bodyPart: String, isSaved: Boolean): ExerciseInfo{
        return (
            ExerciseInfo(
                name = name,
                bodyPart = bodyPart,
                isSaved = isSaved
            ))
    }

    private fun insertItem(newItem: ExerciseInfo){
        viewModelScope.launch {
            exerciseDAO.insert(newItem)
        }
    }
}

class SavedWorkoutsViewModelFactory(private val exerciseDAO: ExerciseDAO): ViewModelProvider.Factory {
    fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ExerciseViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return SavedWorkoutsViewModel(exerciseDAO) as T
        }
        throw IllegalArgumentException("Unknown view model class")
    }
}

 */


class SavedWorkoutsViewModel(private val repository: SavedWorkoutRepository): ViewModel() {
    val allExercises: LiveData<List<ExerciseInfo>> = repository.allExercises.asLiveData()

    fun insert(exerciseInfo: ExerciseInfo) = viewModelScope.launch {
        repository.insert(exerciseInfo)
    }
}

class SavedWorkoutsViewModelFactory(private val repository: SavedWorkoutRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ExerciseViewModel::class.java)){
            @Suppress("Unchecked cast")
            return SavedWorkoutsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown view model class")
    }
}












