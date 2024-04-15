package edu.quinnipiac.ser210.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import edu.quinnipiac.ser210.myapplication.R
import edu.quinnipiac.ser210.myapplication.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()

        val bodyParts = arrayOf("Head", "Arm", "Leg", "Chest")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, bodyParts)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.bodyPartSpinner.adapter = adapter
        binding.bodyPartSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedBodyPart = parent.getItemAtPosition(position).toString()
                makeApiCall(selectedBodyPart)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Optionally handle the case where nothing is selected
            }
        }
//        binding.buttonToSavedWorkouts.setOnClickListener {
//            navController.navigate(R.id.action_HomeFragment_to_SavedWorkoutsFragment)
//        }

    }

    private fun makeApiCall(bodyPart: String) {
        // Here, insert your API call logic using Retrofit, Volley, or another HTTP library
        // Example:
        // ApiService.create().getBodyPartData(bodyPart).enqueue(/* Your callback here */)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
