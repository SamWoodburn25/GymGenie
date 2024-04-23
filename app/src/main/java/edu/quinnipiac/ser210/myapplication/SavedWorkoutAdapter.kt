package edu.quinnipiac.ser210.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import edu.quinnipiac.ser210.myapplication.data.Workout
import edu.quinnipiac.ser210.myapplication.databinding.ListItemBinding
import edu.quinnipiac.ser210.myapplication.databinding.SavedListItemBinding


class SavedWorkoutAdapter(private var dataSet: List<Workout>, name: String) : RecyclerView.Adapter<SavedWorkoutAdapter.SavedViewHolder>() {

    lateinit var fullWorkoutList: List<Workout>
    var name = name

    //view holder class
    class SavedViewHolder(private val binding: SavedListItemBinding, title: String): RecyclerView.ViewHolder(binding.root) {
            var title = title
        fun bind(workout: Workout) {
            binding.workoutName.text = "Title: ${title}"
            binding.savedWorkout.text = "Exercise Name: ${workout.exercises}"
        }
    }

    fun getCurrentList(): List<Workout> {
        return dataSet
    }

    //make view holder, list_item layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedWorkoutAdapter.SavedViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.saved_list_item, parent, false)
        fullWorkoutList = dataSet
        val binding = SavedListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return SavedViewHolder(binding, name)
    }


    override fun onBindViewHolder(holder: SavedViewHolder, position: Int) {
        //bind hit to holder
        val workout = dataSet[position]
        holder.bind(workout)

        holder.bind(dataSet[position])
    }

    override fun getItemCount() = dataSet.size


    //load a list of Hit to the dataset variable
    fun submitList(newWorkout: List<Workout>) {
        val diffCallback = object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = dataSet.size
            override fun getNewListSize(): Int = newWorkout.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                // This depends on whether ExerciseItem has an ID or some unique property
                // If it does not have a unique identifier, you may need to compare names or another field
                return dataSet[oldItemPosition] == newWorkout[newItemPosition]
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return dataSet[oldItemPosition] == newWorkout[newItemPosition]
            }
        }

        val diffResult = DiffUtil.calculateDiff(diffCallback)
        dataSet = newWorkout
        diffResult.dispatchUpdatesTo(this)
    }
}
