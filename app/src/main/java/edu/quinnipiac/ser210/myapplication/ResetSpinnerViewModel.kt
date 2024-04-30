package edu.quinnipiac.ser210.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ResetSpinnerViewModel : ViewModel() {
    private val _resetEvent = MutableLiveData<Boolean>()
    val resetEvent: LiveData<Boolean> = _resetEvent

    fun triggerReset() {
        _resetEvent.value = true
    }

    fun resetComplete() {
        _resetEvent.value = false
    }
}
