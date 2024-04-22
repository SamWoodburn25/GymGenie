package edu.quinnipiac.ser210.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.quinnipiac.ser210.myapplication.data.Workout

class SavedWorkoutAdapter : ListAdapter<Workout, SavedWorkoutAdapter.ViewHolder>(ExerciseComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.exercises)
    }


    //view holder class
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //text view variables
        val textViewName: TextView = view.findViewById(R.id.savedWorkoutItem)
        fun bind(exercise: String) {
            textViewName.text = "Exercise Name: "+exercise
        }

        companion object {
            fun create(parent: ViewGroup): ViewHolder {
                val view: View = LayoutInflater.from(parent.context).inflate(R.layout.saved_list_item, parent, false)
                return ViewHolder(view)
            }
        }
    }

    class ExerciseComparator: DiffUtil.ItemCallback<Workout>() {
        override fun areItemsTheSame(oldItem: Workout, newItem: Workout): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Workout, newItem: Workout): Boolean {
            return oldItem.exercises == newItem.exercises
        }

    }






}