package edu.quinnipiac.ser210.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val selectedWorkouts = MutableLiveData<List<Exercise>>()

    fun selectWorkouts(workouts: List<Exercise>) {
        selectedWorkouts.value = workouts
    }

    fun getSelectedWorkouts(): LiveData<List<Exercise>> = selectedWorkouts

}