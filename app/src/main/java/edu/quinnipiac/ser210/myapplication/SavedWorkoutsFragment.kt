package edu.quinnipiac.ser210.myapplication
/*
  * Gabby Pierce and Sam Woodburn
  * Final Project SER210
  * Gym Genie
  * Saved workouts frag: displays the workouts saved by the user, stored in the database
 */

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs

class SavedWorkoutsFragment : Fragment() {

    private val navigationArgs: AllWorkoutsFragmentArgs by navArgs()
    /*
    private val viewModel: ExerciseViewModel by activityViewModels {
        ExerciseViewModelFactory(
            (activity?.application as GymGenieApp).database.exerciseDao()
        )
    }

     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saved_workouts, container, false)
    }

}