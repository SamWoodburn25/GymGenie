package edu.quinnipiac.ser210.myapplication
/*
  * Gabby Pierce and Sam Woodburn
  * Final Project SER210
  * Gym Genie
  * Home frag: displays the home screen, allows user to either view their saved workouts or
  * view all exercises based on a selected body part
 */

import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import edu.quinnipiac.ser210.myapplication.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    //binding and navigation
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController

    //stay on home frag
    var isFirstSelection = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        isFirstSelection = true

        //spinner selection for body part
        val bodyParts = arrayOf("Select a Body Part", "Back", "Arms", "Legs", "Chest", "Shoulders", "Core")
        val adapter = ArrayAdapter(requireContext(), R.layout.spinner_item, bodyParts)
        adapter.setDropDownViewResource(R.layout.spinner_item)
        binding.bodyPartSpinner.adapter = adapter

        //item selected listener, navigate to all workouts frag
        binding.bodyPartSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                //log to keep track of data
                Log.d("HomeFragment", "onItemSelected: position = $position")

                //stay on home frag, don't navigate right away
                if (isFirstSelection) {
                    isFirstSelection = false    //next selection
                    return
                }

                    //set item selected to selected body part, pass to all workouts frag & navigate there, log info
                    val selectedBodyPart = parent.getItemAtPosition(position).toString()
                    Log.d("HomeFragment", "Selected body part: $selectedBodyPart")
                    val action = HomeFragmentDirections.actionHomeFragmentToAllWorkoutsFragment(
                        selectedBodyPart
                    )
                    findNavController().navigate(action)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                //stay on home frag
                isFirstSelection = true
            }
        }

        //save workouts button
        binding.SavedWorkouts.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToSavedWorkoutsFragment(null)
            navController.navigate(action)
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        isFirstSelection = true  // Reset the flag so that automatic selection is ignored!!
    }

    fun resetSpinner(){
        Log.d("HOMEEEE", "callin the method")
        isFirstSelection = true
    }


}
