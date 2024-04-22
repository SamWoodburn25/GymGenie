package edu.quinnipiac.ser210.myapplication

import androidx.annotation.WorkerThread
import edu.quinnipiac.ser210.myapplication.databaseInfo.ExerciseDAO
import edu.quinnipiac.ser210.myapplication.databaseInfo.ExerciseInfo
import kotlinx.coroutines.flow.Flow

class SavedWorkoutRepository(private val exerciseDAO: ExerciseDAO) {

    val allExercises: Flow<List<ExerciseInfo>> = exerciseDAO.getExcersiceInfo()

    @Suppress
        ("RedudantSuspendModifier")
    @WorkerThread
    suspend fun insert (exerciseInfo: ExerciseInfo){
        exerciseDAO.insert(exerciseInfo)
    }
}