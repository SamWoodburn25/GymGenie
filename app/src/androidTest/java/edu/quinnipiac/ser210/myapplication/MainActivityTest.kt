package edu.quinnipiac.ser210.myapplication
/*
    INSTRUMENTED TEST CLASS (i really tried)
 */

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.idling.CountingIdlingResource
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.espresso.contrib.RecyclerViewActions
import org.hamcrest.Matcher
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.CoreMatchers.`is`
import org.junit.Rule
import org.junit.Before
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private val idlingResource = CountingIdlingResource("Network_Call")

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    // Helper method to perform click on specific child view within RecyclerView item
    private fun clickChildViewWithId(id: Int): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View>? {
                return null
            }

            override fun getDescription(): String {
                return "Click on a child view with specified ID."
            }

            override fun perform(uiController: UiController, view: View) {
                val v: View = view.findViewById(id)
                v.performClick()
            }
        }
    }

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(idlingResource)
    }

    @Test
    fun mainActivityTest() {

        // Ensure the spinner opens and then select an item by its text
        onView(withId(R.id.bodyPartSpinner)).perform(click())
        onData(allOf(`is`(instanceOf(String::class.java)), `is`("Shoulders")))
            .perform(click())

        // Assume network call starts
        idlingResource.increment()


        // Wait for data to load
        onView(withId(R.id.recyclerView))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))

        // Mock network response, then:
        // onData loads, decrement after data is set to the adapter
        // This is normally handled where your data is actually being set to the adapter
        // For demonstration, assume data is now loaded:
        idlingResource.decrement()

        // Interact with a button
        onView(withId(R.id.recyclerView))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, clickChildViewWithId(R.id.deleteButton)));


        // Interact with a floating action button
        onView(allOf(withId(R.id.FABSavedWorkoutsButton), withText("Save Workout"), isDisplayed()))
            .perform(click())


        // Replace text in an EditText and close the keyboard
        onView(allOf(withId(androidx.appcompat.R.id.custom), isDisplayed()))
            .perform(replaceText("shoulders"), closeSoftKeyboard())

        // Click a button to save the text
        onView(allOf(withId(android.R.id.button1), withText("Save"), isDisplayed()))
            .perform(scrollTo(), click())

        // Another interaction with a floating action button to navigate back
        onView(allOf(withId(R.id.savedBackToHome), withText("BACK TO HOME"), isDisplayed()))
            .perform(click())
    }
}

