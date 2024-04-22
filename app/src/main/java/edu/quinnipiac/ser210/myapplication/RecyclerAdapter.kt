package edu.quinnipiac.ser210.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import edu.quinnipiac.ser210.myapplication.APIData.ExerciseItem
import edu.quinnipiac.ser210.myapplication.databinding.ListItemBinding

//list of jobs variable, to be accessed by other classes
lateinit var exerciseItemList: List<ExerciseItem>

class RecyclerAdapter(private var dataSet: List<ExerciseItem>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    //view holder class
    class ViewHolder(private val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(exercise: ExerciseItem) {
            binding.exerciseName.text = "Exercise Name: ${exercise.name}"
        }
    }

    fun getCurrentList(): List<ExerciseItem> {
        return dataSet
    }

    //make view holder, list_item layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        exerciseItemList = dataSet
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
        //return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //bind hit to holder
        val exercise = dataSet[position]
        holder.bind(exercise)

        holder.bind(dataSet[position])
    }

    override fun getItemCount() = dataSet.size


    //load a list of Hit to the dataset variable
    fun submitList(newExercises: List<ExerciseItem>) {
        val diffCallback = object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = dataSet.size
            override fun getNewListSize(): Int = newExercises.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                // This depends on whether ExerciseItem has an ID or some unique property
                // If it does not have a unique identifier, you may need to compare names or another field
                return dataSet[oldItemPosition] == newExercises[newItemPosition]
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return dataSet[oldItemPosition] == newExercises[newItemPosition]
            }
        }

        val diffResult = DiffUtil.calculateDiff(diffCallback)
        dataSet = newExercises
        diffResult.dispatchUpdatesTo(this)
    }
}