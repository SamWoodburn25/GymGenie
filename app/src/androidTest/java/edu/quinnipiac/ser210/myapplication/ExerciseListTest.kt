package edu.quinnipiac.ser210.myapplication

/*
    UNIT TEST CLASS ATTEMPT 2
 */

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import edu.quinnipiac.ser210.myapplication.util.EspressoIdlingResource
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.instanceOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExerciseListTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    val LIST_ITEM_IN_TEST = 2
    val EXERCISE_IN_TEST = "Deadlift"

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        // Register your Idling Resource here
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun tearDown() {
        // Unregister your Idling Resource here
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun test_isListFragmentVisible_onAppLaunch() {
        // Navigate to the RecyclerView by selecting an item in the spinner
//        onView(withId(R.id.bodyPartSpinner)).perform(click())
//        onData(allOf(instanceOf(String::class.java), is("Arms"))).perform(click())

        // Check if RecyclerView is displayed after the selection
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
    }

    @Test
    fun test_selectItemAndCheckDetail() {
        // Navigate to the RecyclerView by selecting an item in the spinner
//        onView(withId(R.id.bodyPartSpinner)).perform(click())
//        onData(allOf(instanceOf(String::class.java), is("Arms"))).perform(click())

        // Click list item #LIST_ITEM_IN_TEST
        onView(withId(R.id.recyclerView))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(LIST_ITEM_IN_TEST, click()))

        // Confirm navigation to DetailFragment and display the exercise name
        onView(withId(R.id.exerciseName)).check(matches(withText(EXERCISE_IN_TEST)))

        // Optional: navigate back and check if RecyclerView is still visible
        pressBack()
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
    }
}