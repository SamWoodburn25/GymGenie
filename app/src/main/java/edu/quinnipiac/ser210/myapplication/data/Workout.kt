package edu.quinnipiac.ser210.myapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(tableName = "workouts")
data class Workout(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "body_part") val bodyPart: String,
    @ColumnInfo(name = "exercises") val exercises: String // Storing List<Exercise> as a JSON string
)
