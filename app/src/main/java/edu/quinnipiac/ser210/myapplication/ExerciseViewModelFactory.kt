package edu.quinnipiac.ser210.myapplication
/*
  * Gabby Pierce and Sam Woodburn
  * Final Project SER210
  * Gym Genie
  * Exercise view model factory: extends view model provider. Factory
 */

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ExerciseViewModelFactory(private val repository: ExerciseRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExerciseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ExerciseViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}