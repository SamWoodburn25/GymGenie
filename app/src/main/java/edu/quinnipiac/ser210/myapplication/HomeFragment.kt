package edu.quinnipiac.ser210.myapplication
/*
  * Gabby Pierce and Sam Woodburn
  * Final Project SER210
  * Gym Genie
  * Home frag: displays the home screen, allows user to either view their saved workouts or
  * view all exercises based on a selected body part
 */

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import edu.quinnipiac.ser210.myapplication.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    //binding and navigation
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController

    var isFirstSelection = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        isFirstSelection = true


        val bodyParts = arrayOf("Back", "Arms", "Legs", "Chest", "Shoulders", "Core")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, bodyParts)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.bodyPartSpinner.adapter = adapter
        binding.bodyPartSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                Log.d("HomeFragment", "onItemSelected: position = $position")
                if (isFirstSelection) {
                    isFirstSelection = false
                    return
                }
                val selectedBodyPart = parent.getItemAtPosition(position).toString()
                Log.d("HomeFragment", "Selected body part: $selectedBodyPart")
                val action = HomeFragmentDirections.actionHomeFragmentToAllWorkoutsFragment(selectedBodyPart)
                findNavController().navigate(action)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Optionally handle the case where nothing is selected
            }
        }

        binding.SavedWorkouts.setOnClickListener {
            navController.navigate(R.id.action_HomeFragment_to_SavedWorkoutsFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        isFirstSelection = true  // Reset the flag so that automatic selection is ignored
    }

}
