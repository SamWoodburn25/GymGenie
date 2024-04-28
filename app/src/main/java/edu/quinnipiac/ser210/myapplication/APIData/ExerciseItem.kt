package edu.quinnipiac.ser210.myapplication.APIData
/*
  * Gabby Pierce and Sam Woodburn
  * Final Project SER210
  * Gym Genie
  * data class exercise item: defines items from the api
 */

data class ExerciseItem(
    val bodyParts: List<String>,
    val id: String,
    val name: String,
    var reps: String?
)