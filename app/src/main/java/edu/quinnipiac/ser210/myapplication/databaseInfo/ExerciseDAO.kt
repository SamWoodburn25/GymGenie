package edu.quinnipiac.ser210.myapplication.databaseInfo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDAO {
    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(exerciseInfo: ExerciseInfo)

    @Update
    suspend fun update(exerciseInfo: ExerciseInfo)

    @Delete
    suspend fun delete(exerciseInfo: ExerciseInfo)

    @Query("DELETE FROM exerciseinfotable")
    suspend fun deleteAll()

    @Query("SELECT * FROM ExerciseInfoTable ORDER BY name ASC")
    public abstract fun getExcersiceInfo(): Flow<List<ExerciseInfo>>

}