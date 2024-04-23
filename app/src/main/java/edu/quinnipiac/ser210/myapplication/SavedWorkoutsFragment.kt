package edu.quinnipiac.ser210.myapplication
/*
  * Gabby Pierce and Sam Woodburn
  * Final Project SER210
  * Gym Genie
  * Saved workouts frag: displays the workouts saved by the user, stored in the database
 */

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import edu.quinnipiac.ser210.myapplication.APIData.ApiInterface
import edu.quinnipiac.ser210.myapplication.APIData.ExerciseItem
import edu.quinnipiac.ser210.myapplication.data.DatabaseBuilder
import edu.quinnipiac.ser210.myapplication.data.Workout
import edu.quinnipiac.ser210.myapplication.data.WorkoutDao
import edu.quinnipiac.ser210.myapplication.databinding.FragmentSavedWorkoutsBinding


class SavedWorkoutsFragment : Fragment() {
    //binding
    private var _binding: FragmentSavedWorkoutsBinding? = null
    private val binding get() = _binding!!

    //observe database viewmodel
    private lateinit var viewModel: ExerciseViewModel
    private lateinit var workoutName: String
    private val gson = Gson()
    private lateinit var workoutDao: WorkoutDao

    //adapter
    private lateinit var savedWorkoutAdapter: SavedWorkoutAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSavedWorkoutsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        //get workout database access object
        workoutDao = DatabaseBuilder.getDatabase(context).workoutDao()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //workout title from args
        val args = SavedWorkoutsFragmentArgs.fromBundle(requireArguments())
        workoutName = args.workoutName.toString()

        //make the view model adn repository
        val repository = ExerciseRepository(
            ApiInterface.ApiClient.instance,
            DatabaseBuilder.getDatabase(requireContext()).workoutDao()
        )
        val factory = ExerciseViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(ExerciseViewModel::class.java)  // Ensure ViewModel is correctly implemented

        //set up recycler
        setupRecyclerView()

        //observe view model
        observeViewModel()

        //clear button
        binding.clearDatabaseButton.setOnClickListener{
            viewModel.deleteAllWorkouts()
        }

        //back button
        binding.savedBackToHome.setOnClickListener{
            val action = SavedWorkoutsFragmentDirections.actionSavedWorkoutsFragmentToHomeFragment()
            findNavController().navigate(action)
        }

    }


    private fun setupRecyclerView() {
        //set up recycler view with saved workout adapter
        savedWorkoutAdapter = SavedWorkoutAdapter(listOf())
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = SavedWorkoutAdapter(listOf())  // Initialize your adapter here
    }


    //observe view model, get all saved workouts, submit list to the recycler view
    private fun observeViewModel() {
        viewModel.getAllSavedWorkouts().observe(viewLifecycleOwner) { workouts ->
            val workoutText = workouts.joinToString("\n") { workout ->
                "${workout.bodyPart}: ${Gson().fromJson(workout.exercises, Array<ExerciseItem>::class.java).joinToString(", ") { it.name }}"
            }
            (binding.recyclerView.adapter as? SavedWorkoutAdapter)?.submitList(workouts)
            if (workouts != null) {
                //log for info
                Log.d("SAVED_WORKOUT_FRAGMENT", "saving from database ${workouts.size} exercises")
                savedWorkoutAdapter.submitList(workouts)
            } else {
                //log for failure to observe
                Log.d("SAVED_WORKOUT_FRAGMENT", "observer received null")
            }
        }
    }

    /*
        this is gonna help with changing it from JSON
     */
    private fun updateUI(workouts: List<Workout>) {
        // Assuming you have a TextView or any other UI component to display the saved workouts
        val workoutText = workouts.joinToString("\n") { workout ->
            "${workout.bodyPart}: ${Gson().fromJson(workout.exercises, Array<ExerciseItem>::class.java).joinToString(", ") { it.name }}"
        }
        binding.savedWorkouts.text = workoutText
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null  // Avoid memory leaks
    }

}



