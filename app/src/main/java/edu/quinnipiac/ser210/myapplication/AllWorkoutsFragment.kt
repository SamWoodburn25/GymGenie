package edu.quinnipiac.ser210.myapplication
/*
  * Gabby Pierce and Sam Woodburn
  * Final Project SER210
  * Gym Genie
  * All workouts frag: cals the API to display a recycler view list of the exercises
 */

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import edu.quinnipiac.ser210.myapplication.APIData.ApiInterface
import edu.quinnipiac.ser210.myapplication.data.DatabaseBuilder
import edu.quinnipiac.ser210.myapplication.databinding.FragmentAllWorkoutsBinding

class AllWorkoutsFragment : Fragment() {
    //binding and navigation
    private var _binding: FragmentAllWorkoutsBinding? = null
    private val binding get() = _binding!!
    //variables for recycler view
    //job view model
    private lateinit var navController: NavController

    var isFirstSelection = true

    private lateinit var viewModel: ExerciseViewModel
    private lateinit var viewModelFactory: ExerciseViewModelFactory
    private lateinit var selectedMuscle: String
    lateinit var recyclerAdapter: RecyclerAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAllWorkoutsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()
        isFirstSelection = true


        //different than before
        //val repository = ExerciseRepository()
        val repository = ExerciseRepository(ApiInterface.ApiClient.instance, DatabaseBuilder.getDatabase(requireContext()).workoutDao())
//        viewModel = ViewModelProvider(requireActivity()).get(ExerciseViewModel::class.java) // try this instead of requireActivity()
//        viewModelFactory = ExerciseViewModelFactory(repository)
        viewModelFactory = ExerciseViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ExerciseViewModel::class.java)
        binding.saveWorkoutButton.setOnClickListener {
            saveCurrentWorkout()
        }

        binding.back.setOnClickListener {
            navController.navigate(R.id.action_AllWorkoutsFragment_to_HomeFragment)
        }
        //added
        binding.saveWorkoutButton.setOnClickListener {
            saveCurrentWorkout()
        }

        // Retrieve the chosen location from arguments
        val args = AllWorkoutsFragmentArgs.fromBundle(requireArguments())
        selectedMuscle = args.bodyPart

        // Setup ViewModel

        viewModelFactory = ExerciseViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ExerciseViewModel::class.java)

        // Setup RecyclerView with Adapter and click listener to navigate
        setupRecyclerView()

        // Observe ViewModel LiveData for jobs
        observeViewModel()
        viewModel.getExercises(selectedMuscle)
        //log to monitor data
        Log.d("BODY PART", "search: ${selectedMuscle}")
    }

    //added
    private fun saveCurrentWorkout() {
        val currentExercises = recyclerAdapter.getCurrentList()
        if (currentExercises.isNotEmpty()) {
            viewModel.saveWorkout(selectedMuscle, currentExercises)
        } else {
            Toast.makeText(context, "No exercises to save", Toast.LENGTH_SHORT).show()
        }
    }

    //setting up recycler view with recycler adapter
    private fun setupRecyclerView() {
        recyclerAdapter = RecyclerAdapter(listOf())
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = recyclerAdapter
    }

    //observe live data changes
    private fun observeViewModel() {
        //observing the live data list of hits from the view model
        viewModel.allExerciseList.observe(viewLifecycleOwner) { exercises ->
            (binding.recyclerView.adapter as? RecyclerAdapter)?.submitList(exercises)
            if (exercises != null) {
                //log for info
                Log.d("ALL_WORKOUT_FRAGMENT", "LiveData observer received ${exercises.size} jobs")
                recyclerAdapter.submitList(exercises)
            } else {
                Log.d("ALL_WORKOUT_FRAGMENT", "LiveData observer received null")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Avoid memory leak
    }

}
