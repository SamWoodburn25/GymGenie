package edu.quinnipiac.ser210.myapplication.data
/*
  * Gabby Pierce and Sam Woodburn
  * Final Project SER210
  * Gym Genie
  * Workout Entity Data Class
 */

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(tableName = "workouts")
data class Workout(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    //info to save
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "body_part") val bodyPart: String,
    @ColumnInfo(name = "exercises") val exercises: String // Storing List<Exercise> as a JSON string
)
