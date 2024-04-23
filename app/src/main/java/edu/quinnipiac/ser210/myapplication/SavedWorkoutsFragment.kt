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

    //adapter
    private lateinit var savedWorkoutAdapter: SavedWorkoutAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSavedWorkoutsBinding.inflate(inflater, container, false)
        return binding.root
    }

    private lateinit var workoutDao: WorkoutDao

    override fun onAttach(context: Context) {
        super.onAttach(context)
        workoutDao = DatabaseBuilder.getDatabase(context).workoutDao()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //workout title
        val args = SavedWorkoutsFragmentArgs.fromBundle(requireArguments())
        workoutName = args.workoutName.toString()


        val repository = ExerciseRepository(
            ApiInterface.ApiClient.instance,
            DatabaseBuilder.getDatabase(requireContext()).workoutDao()
        )
        val factory = ExerciseViewModelFactory(repository)

        //making the view model
        viewModel = ViewModelProvider(this, factory).get(ExerciseViewModel::class.java)  // Ensure ViewModel is correctly implemented

        //set up recycler
        setupRecyclerView()

        //observe view model
        observeViewModel()
        //observeWorkouts()

        //clear button
        binding.clearDatabaseButton.setOnClickListener{
            viewModel.deleteAllWorkouts()
        }

    }



    private fun setupRecyclerView() {
        savedWorkoutAdapter = SavedWorkoutAdapter(listOf(), workoutName)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = SavedWorkoutAdapter(listOf(), workoutName)  // Initialize your adapter here
    }

    private fun observeWorkouts() {
        viewModel.getAllSavedWorkouts().observe(viewLifecycleOwner) { workouts ->
            updateUI(workouts)
       }
    }

    //observe live data changes
    private fun observeViewModel() {
        viewModel.getAllSavedWorkouts().observe(viewLifecycleOwner) { workouts ->
            (binding.recyclerView.adapter as? SavedWorkoutAdapter)?.submitList(workouts)
            if (workouts != null) {
                //log for info
                Log.d("SAVED_WORKOUT_FRAGMENT", "saving from database ${workouts.size} exercises")
                savedWorkoutAdapter.submitList(workouts)
            } else {
                Log.d("SAVED_WORKOUT_FRAGMENT", "observer received null")
            }
        }
    }

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



