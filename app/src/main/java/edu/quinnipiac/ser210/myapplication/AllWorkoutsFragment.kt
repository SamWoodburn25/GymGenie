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
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import edu.quinnipiac.ser210.myapplication.APIData.ApiInterface
import edu.quinnipiac.ser210.myapplication.APIData.ExerciseItem
import edu.quinnipiac.ser210.myapplication.data.DatabaseBuilder
import edu.quinnipiac.ser210.myapplication.databinding.FragmentAllWorkoutsBinding

class AllWorkoutsFragment : Fragment(),OnItemRemoved {
    //binding and navigation
    private var _binding: FragmentAllWorkoutsBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController

    //open to home fragment
    var isFirstSelection = true

    //observe api view model
    private lateinit var viewModel: ExerciseViewModel
    private lateinit var viewModelFactory: ExerciseViewModelFactory

    //recycler view
    private lateinit var selectedMuscle: String
    lateinit var recyclerAdapter: RecyclerAdapter

    //name workout to save to database
    private lateinit var workoutName: String

    //delete an item
    var isDeleted: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAllWorkoutsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //navigation
        navController = findNavController()
        isFirstSelection = true

        //default title (nothing entered)
        workoutName = "New Workout"

        //observe database and api through the exercise repository and exercise view model
        val repository = ExerciseRepository(
            ApiInterface.ApiClient.instance,
            DatabaseBuilder.getDatabase(requireContext())
                .workoutDao())
        viewModelFactory = ExerciseViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ExerciseViewModel::class.java)

        //save workouts button
        binding.FABSavedWorkoutsButton.setOnClickListener {
            showNameWorkoutDialogue()
        }

        //back button
        binding.FABback.setOnClickListener {
            navController.navigate(R.id.action_AllWorkoutsFragment_to_HomeFragment)
        }

        // Retrieve the chosen body part from args and set text
        val args = AllWorkoutsFragmentArgs.fromBundle(requireArguments())
        selectedMuscle = args.bodyPart
        binding.muscleTextView.setText("Muscle: $selectedMuscle")


        // Setup RecyclerView with RecyclerAdapter
        setupRecyclerView()

        // Observe ViewModel LiveData for exercise names
        observeViewModel()
        viewModel.getExercises(selectedMuscle)
        //log to monitor data
        Log.d("BODY PART", "search: ${selectedMuscle}")
    }

    //name your workout pop-up
    private fun showNameWorkoutDialogue() {
        val editText = EditText(context)
        editText.inputType = InputType.TYPE_CLASS_TEXT
        //alert dialog window
        AlertDialog.Builder(requireContext())
            .setTitle("Name Your Workout")
            .setView(editText)
            //on save clicked
            .setPositiveButton("Save") { dialog, which ->
                //save the name in a variable
                workoutName = editText.text.toString()
                // Call saveCurrentWorkout here after the name is set
                saveCurrentWorkout(workoutName)
                //navigate to the saved workouts frag
                val action = AllWorkoutsFragmentDirections.actionAllWorkoutsFragmentToSavedWorkoutsFragment(workoutName)
                findNavController().navigate(action)
            }
            .setNegativeButton("Cancel", null)
            .create()
            .show()
    }


    //save workout
    private fun saveCurrentWorkout(workoutName: String) {
        //the current list on the recycler adapter is being saved
        val currentExercises = recyclerAdapter.getCurrentList()
        val currentReps = recyclerAdapter.getCurrentReps()
        if (currentExercises.isNotEmpty()) {
            //call save workout from view model and pass in info
            Log.d("REPS","$currentReps")
            viewModel.saveWorkout(workoutName, selectedMuscle, currentExercises, currentReps)
        } else {
            Toast.makeText(context, "No exercises to save", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupRecyclerView() {
        //set up recycler view with recycler adapter
        recyclerAdapter = RecyclerAdapter(mutableListOf(), this)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = recyclerAdapter
    }

    //observe live data changes
    private fun observeViewModel() {
        //observing the live data list of exercises from the view model
        viewModel.allExerciseList.observe(viewLifecycleOwner) { exercises ->
            (binding.recyclerView.adapter as? RecyclerAdapter)?.submitList(exercises)
            if (exercises != null) {
                //log for info
                Log.d("ALL_WORKOUT_FRAGMENT", "LiveData observer received ${exercises.size} jobs")
                //submit list
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

    override fun onItemRemove(exerciseitem: ExerciseItem){
        Log.d("ITEM REMOVE", "item: $exerciseitem")
        Log.d("ITEM REMOVE", "oldlist: ${recyclerAdapter.getCurrentList()}")
        val newList = recyclerAdapter.getCurrentList().toMutableList()
        newList.remove(exerciseitem)
        recyclerAdapter.submitList(newList)
        Log.d("ITEM REMOVE", "newlist: ${recyclerAdapter.getCurrentList()}")


    }

}
