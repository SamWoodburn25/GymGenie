package edu.quinnipiac.ser210.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.quinnipiac.ser210.myapplication.APIData.ExerciseRepository
import edu.quinnipiac.ser210.myapplication.databaseInfo.ExerciseDAO

class ExerciseViewModelFactory(private val repository: ExerciseRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExerciseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ExerciseViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}