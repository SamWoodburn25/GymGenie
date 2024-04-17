package edu.quinnipiac.ser210.myapplication

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import edu.quinnipiac.ser210.myapplication.APIData.ExerciseItem

//list of jobs variable, to be accessed by other classes
lateinit var exerciseItemList: List<ExerciseItem>
class RecyclerAdapter(var dataSet:  List<ExerciseItem>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    //view holder class
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //text view variables
        val textViewName: TextView = view.findViewById(R.id.exerciseName)
        fun bind(exercise: ExerciseItem) {
            textViewName.text = "Exercise Name: "+exercise.name
        }
    }

    //make view holder, list_item layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        //dataset parameter is equal to hitList
        exerciseItemList = dataSet

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //bind hit to holder
        val exercise = dataSet[position]
        holder.bind(exercise)
    }

    override fun getItemCount() = dataSet.size


    //load a list of Hit to the dataset variable
    fun submitList(newExercises: List<ExerciseItem>) {
        //log to track the info
        Log.d("RECYCLER_ADAPTER", "Submitting list with size: ${newExercises.size}")
        val diffCallback = object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = dataSet.size
            override fun getNewListSize(): Int = newExercises.size
            //check if the items or contents are the same
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return dataSet[oldItemPosition].id == newExercises[newItemPosition].id
            }
            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return dataSet[oldItemPosition] == newExercises[newItemPosition]
            }
        }
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        //update dataset variable
        dataSet = newExercises
        diffResult.dispatchUpdatesTo(this)
    }

}