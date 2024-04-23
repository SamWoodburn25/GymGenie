package edu.quinnipiac.ser210.myapplication.data
/*
  * Gabby Pierce and Sam Woodburn
  * Final Project SER210
  * Gym Genie
  * Database builder: uses room database builder to make an instance of gym genies database
 */

import android.content.Context
import androidx.room.Room

// Singleton instance of the Room database
object DatabaseBuilder {
    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        if (INSTANCE == null) {
            synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "gym_genie_database"
                )
                    // Migration strategies would go here for production apps
                    .fallbackToDestructiveMigration()
                    .build()
            }
        }
        return INSTANCE!!
    }
}
