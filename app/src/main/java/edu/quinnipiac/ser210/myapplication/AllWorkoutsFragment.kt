package edu.quinnipiac.ser210.myapplication
/*
  * Gabby Pierce and Sam Woodburn
  * Final Project SER210
  * Gym Genie
  * All workouts frag: cals the API to display a recycler view list of the exercises
 */

import android.os.Bundle
import android.text.InputType
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import edu.quinnipiac.ser210.myapplication.APIData.ExerciseRepository
import edu.quinnipiac.ser210.myapplication.databinding.FragmentAllWorkoutsBinding

class AllWorkoutsFragment : Fragment() {
    //binding and navigation
    private var _binding: FragmentAllWorkoutsBinding? = null
    private val binding get() = _binding!!
    //variables for recycler view
    lateinit var recyclerAdapter: RecyclerAdapter
    //job view model
    private lateinit var viewModel: ExerciseViewModel
    private lateinit var viewModelFactory: ExerciseViewModelFactory
    private lateinit var selectedMuscle: String
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAllWorkoutsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve the chosen location from arguments
        val args = AllWorkoutsFragmentArgs.fromBundle(requireArguments())
        selectedMuscle = args.bodyPart

        binding.muscleTextView.setText("Muscle: $selectedMuscle")

        // Setup ViewModel
        val repository = ExerciseRepository()
        viewModelFactory = ExerciseViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ExerciseViewModel::class.java)

        // Setup RecyclerView with Adapter and click listener to navigate
        setupRecyclerView()

        // Observe ViewModel LiveData for jobs
        observeViewModel()
        viewModel.getExercises(selectedMuscle)
        //log to monitor data
        Log.d("BODY PART", "search: ${selectedMuscle}")

        /*
          *Saved Workouts button
         */
        val button = binding.FABSavedWorkoutsButton
        button.setOnClickListener{
            showNameWorkoutDialogue()
        }
    }

    private fun showNameWorkoutDialogue() {
        val editText = EditText(context)
        editText.inputType = InputType.TYPE_CLASS_TEXT
        AlertDialog.Builder(requireContext())
            .setTitle("Name Your Workout")
            .setView(editText)
            .setPositiveButton("Save") { dialog, which ->
                val workoutName = editText.text.toString()
                val action = AllWorkoutsFragmentDirections.actionAllWorkoutsFragmentToSavedWorkoutsFragment()
                findNavController().navigate(action)
            }
            .setNegativeButton("Cancel", null)
            .create()
            .show()
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