package com.example.taskStreak

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(val context: Context, val tasks: ArrayList<Task>) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    // Specify layout file to use on creation
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_task, parent, false)
        return ViewHolder(view)
    }

    // Bind task to created view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = tasks.get(position)
        holder.bind(task)
    }

    // Get size of data set
    override fun getItemCount(): Int {
        return tasks.size
    }

    // Clear all elements of the recycler
    fun clear() {
        tasks.clear()
        notifyDataSetChanged()
    }

    // Add a list of tasks
    fun addAll(tasksList: ArrayList<Task>) {
        tasks.addAll(tasksList)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        val tvDescription: TextView

        init {
            tvDescription = itemView.findViewById(R.id.//Fill tv id)
        }

        fun bind(task: Task) {
            tvDescription.text = task.getDescription()
        }

    }

}