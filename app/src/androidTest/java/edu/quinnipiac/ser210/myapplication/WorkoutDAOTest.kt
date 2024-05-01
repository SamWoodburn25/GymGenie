package edu.quinnipiac.ser210.myapplication.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import edu.quinnipiac.ser210.myapplication.APIData.ExerciseItem
import org.junit.runner.RunWith

import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class WorkoutDAOTest {



    private lateinit var workoutDao: WorkoutDao
    private lateinit var inventoryDatabase: AppDatabase
    private var item1 = Workout(1, "lunges", "legs", "lunges", "4x10")
    private var item2 = Workout(2, "push-up", "arms", "push-up", "3x10")

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        inventoryDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        workoutDao = inventoryDatabase.workoutDao()
    }
    private suspend fun addOneItemToDb() {
        workoutDao.insertWorkout(item1)
    }

    private suspend fun addTwoItemsToDb() {
        workoutDao.insertWorkout(item1)
        workoutDao.insertWorkout(item2)
    }

    @Test
    @Throws(Exception::class)
    fun daoInsert_insertsItemIntoDB() = runBlocking {
        addOneItemToDb()
        val allItems = workoutDao.getAllWorkouts()
        assertEquals(allItems, item1)
    }
    @Test
    @Throws(Exception::class)
    fun daoGetAllItems_returnsAllItemsFromDB() = runBlocking {
        addTwoItemsToDb()
        val allItems = workoutDao.getAllWorkouts()
        Assert.assertEquals(allItems, item1)
        Assert.assertEquals(allItems, item2)
    }
    @After
    @Throws(IOException::class)
    fun closeDb() {
        inventoryDatabase.close()
    }

}

