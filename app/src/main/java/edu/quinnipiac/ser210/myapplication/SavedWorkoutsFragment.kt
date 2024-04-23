package edu.quinnipiac.ser210.myapplication
/*
  * Gabby Pierce and Sam Woodburn
  * Final Project SER210
  * Gym Genie
  * Saved workouts frag: displays the workouts saved by the user, stored in the database
 */

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import edu.quinnipiac.ser210.myapplication.APIData.ApiInterface
import edu.quinnipiac.ser210.myapplication.APIData.ExerciseItem
import edu.quinnipiac.ser210.myapplication.data.DatabaseBuilder
import edu.quinnipiac.ser210.myapplication.data.Workout
import edu.quinnipiac.ser210.myapplication.data.WorkoutDao
import edu.quinnipiac.ser210.myapplication.databinding.FragmentSavedWorkoutsBinding


class SavedWorkoutsFragment : Fragment() {
    private var _binding: FragmentSavedWorkoutsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ExerciseViewModel
    private val gson = Gson()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSavedWorkoutsBinding.inflate(inflater, container, false)
        // return binding.root
        // Inflate the layout for this fragment
        return binding.root
    }

    private lateinit var workoutDao: WorkoutDao

    override fun onAttach(context: Context) {
        super.onAttach(context)
        workoutDao = DatabaseBuilder.getDatabase(context).workoutDao()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = ExerciseRepository(
            ApiInterface.ApiClient.instance,
            DatabaseBuilder.getDatabase(requireContext()).workoutDao()
        )
        val factory = ExerciseViewModelFactory(repository)
        viewModel = ViewModelProvider(
            this,
            factory
        ).get(ExerciseViewModel::class.java)  // Ensure ViewModel is correctly implemented
        //setupRecyclerView()
        observeWorkouts()

    }


//    private fun setupRecyclerView() {
//        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
//        binding.recyclerView.adapter = RecyclerAdapter(listOf())  // Initialize your adapter here
//    }

    private fun observeWorkouts() {
        viewModel.getAllSavedWorkouts().observe(viewLifecycleOwner) { workouts ->
            updateUI(workouts)
//        workoutDao.getAllWorkouts().observe(viewLifecycleOwner) { workouts ->
//            updateUI(workouts)
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