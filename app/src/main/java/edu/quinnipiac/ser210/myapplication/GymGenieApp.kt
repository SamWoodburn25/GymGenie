package edu.quinnipiac.ser210.myapplication

import android.app.Application
import edu.quinnipiac.ser210.myapplication.databaseInfo.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class GymGenieApp: Application() {
    val appScope = CoroutineScope(SupervisorJob())
    val database: AppDatabase by lazy {AppDatabase.getDatabase(this, appScope)}
    val repository: SavedWorkoutRepository by lazy { SavedWorkoutRepository(database.exerciseDao()) }
}