package edu.quinnipiac.ser210.myapplication.databaseInfo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName= "ExerciseInfoTable")
data class ExerciseInfo (
    @PrimaryKey(autoGenerate = true)
    val _id: Int = 0,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "bodyPart")
    val bodyPart: String,

    @ColumnInfo(name = "savedStatus")
    val isSaved: Boolean
)