package edu.quinnipiac.ser210.myapplication.databaseInfo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(ExerciseInfo::class), version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun exerciseDao(): ExerciseDAO

    private class AppDatabaseCallback(private val scope: CoroutineScope): RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let{database -> scope.launch {
                var exerciseDAO = database.exerciseDao()

                //delete all content here
                exerciseDAO.deleteAll()

                //add sample exercises
                var exercise = ExerciseInfo(0, "name", "body part", true)
                exerciseDAO.insert(exercise)
            } }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "exercise_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

