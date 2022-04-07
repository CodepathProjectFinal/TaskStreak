package com.example.taskStreak

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(private val tasks: ArrayList<TaskObj>) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_task,
            parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = tasks[position]

        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    fun clear() {
        tasks.clear()
        notifyDataSetChanged()
    }

    fun addAll(tasksList: List<TaskObj>) {
        tasks.addAll(tasksList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDescription: TextView
        val cbComplete: CheckBox

        init {
            tvDescription = itemView.findViewById(R.id.task_tvDescription)
            cbComplete = itemView.findViewById(R.id.task_cbComplete)
        }

        fun bind(task: TaskObj) {
            tvDescription.text = task.getDescription()
            cbComplete.isChecked = false
            cbComplete.setOnCheckedChangeListener { buttonView, isChecked ->
                task.setCompleted(true)
                task.saveInBackground()
                tasks.remove(task)
                notifyDataSetChanged()
                /*
                var streakCount = tvStreakCount.text.toString().toInt()
                streakCount += 1;
                var streakString = streakCount.toString()
                tvStreakCount.setText(streakString)
                 */
            }
        }

    }
}