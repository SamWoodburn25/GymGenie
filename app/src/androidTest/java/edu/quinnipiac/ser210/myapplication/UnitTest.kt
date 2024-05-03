package edu.quinnipiac.ser210.myapplication
/*
    UNIT TEST CLASS ATTEMPT 1
 */

import android.view.View
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.runner.RunWith
import org.mockito.Mockito
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import org.junit.Rule
import org.junit.Test
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.espresso.matcher.ViewMatchers.withId
import edu.quinnipiac.ser210.myapplication.APIData.ExerciseItem
import edu.quinnipiac.ser210.myapplication.data.Workout
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.Matcher
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(AndroidJUnit4::class)
class UnitTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var viewModel: ExerciseViewModel

    @Mock
    private lateinit var repository: ExerciseRepository


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }


    @Test
    fun testRecyclerViewInteraction() {
        // Prepare LiveData response
        val liveData = MutableLiveData<List<ExerciseItem>>()
        Mockito.`when`(viewModel.allExerciseList).thenReturn(liveData as LiveData<MutableList<ExerciseItem>>)

        // Launch fragment
        val scenario = launchFragmentInContainer<AllWorkoutsFragment>()

        scenario.onFragment { fragment ->
            // Inject mock ViewModel
            fragment.setViewModel(viewModel)
        }

        var list1: List<String> = listOf("Arms")
        var list2: List<String> = listOf("Core")

        // Mock data
        val mockData = mutableListOf(
            ExerciseItem(list1,"Push-ups", "Push-ups", "4x10"),
            ExerciseItem(list2,"Sit-ups", "Sit-ups", "3x10")
        )
        liveData.postValue(mockData)

        // Assuming RecyclerView is populated now, let's interact
        onView(withId(R.id.recyclerView))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, clickChildViewWithId(R.id.deleteButton)))

        // Verify the item is removed
        Mockito.verify(viewModel).delete(Mockito.any(Workout::class.java))
    }

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
}
