package edu.quinnipiac.ser210.myapplication
/*
  * Gabby Pierce and Sam Woodburn
  * Final Project SER210
  * Gym Genie
  * saved workout adapter: for the saved workouts fragment to display the exercise names; xml saved_list_item
 */

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import edu.quinnipiac.ser210.myapplication.data.Workout
import edu.quinnipiac.ser210.myapplication.databinding.ListItemBinding
import edu.quinnipiac.ser210.myapplication.databinding.SavedListItemBinding


class SavedWorkoutAdapter(private var dataSet: List<Workout>) : RecyclerView.Adapter<SavedWorkoutAdapter.SavedViewHolder>() {

    //list of workouts
    lateinit var fullWorkoutList: List<Workout>

    //view holder class, bind to saved list item
    class SavedViewHolder(private val binding: SavedListItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(workout: Workout) {
            binding.workoutName.text = "Title: ${workout.title}"
            binding.savedWorkout.text = "Exercise Name: ${workout.exercises}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedWorkoutAdapter.SavedViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.saved_list_item, parent, false)
        //update full workout list
        fullWorkoutList = dataSet
        val binding = SavedListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SavedViewHolder(binding)
    }


    override fun onBindViewHolder(holder: SavedViewHolder, position: Int) {
        //bind workout to holder
        val workout = dataSet[position]
        holder.bind(workout)
        holder.bind(dataSet[position])
    }

    override fun getItemCount() = dataSet.size


    //load a list of workouts to the dataset variable
    fun submitList(newWorkout: List<Workout>) {
        val diffCallback = object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = dataSet.size
            override fun getNewListSize(): Int = newWorkout.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                // This depends on whether ExerciseItem has an ID or some unique property; If it does not have a unique identifier, you may need to compare names or another field
                return dataSet[oldItemPosition] == newWorkout[newItemPosition]
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return dataSet[oldItemPosition] == newWorkout[newItemPosition]
            }
        }

        val diffResult = DiffUtil.calculateDiff(diffCallback)
        //update dataset
        dataSet = newWorkout
        diffResult.dispatchUpdatesTo(this)
    }
}
