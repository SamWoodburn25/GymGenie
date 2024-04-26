package edu.quinnipiac.ser210.myapplication

import edu.quinnipiac.ser210.myapplication.APIData.ExerciseItem

interface OnItemRemoved {
    fun onItemRemove(exerciseItem: ExerciseItem)
}