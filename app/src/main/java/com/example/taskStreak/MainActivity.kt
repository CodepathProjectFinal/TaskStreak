package com.example.taskStreak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.parse.ParseUser
import com.parse.boltsinternal.Task

class MainActivity : AppCompatActivity() {

    lateinit var adapter: TaskAdapter
    lateinit var feed: RecyclerView
    lateinit var btnAdd: Button
    lateinit var etAddTask: EditText
    var tasks: ArrayList<TaskObj> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRV()
        setupBtn()
    }

    fun setupRV() {
        feed = findViewById(R.id.main_rvTaskFeed)
        adapter =  TaskAdapter(tasks)
        feed.adapter = adapter
        feed.layoutManager = LinearLayoutManager(this@MainActivity)
    }

    fun setupBtn() {
        btnAdd = findViewById(R.id.main_btnAdd)
        etAddTask = findViewById(R.id.main_etAddTask)
        btnAdd.setOnClickListener {
            val userInput = etAddTask.text.toString()
            val user = ParseUser.getCurrentUser()
            createTask(userInput, user)
        }
    }

    fun createTask(description: String, user: ParseUser) {
        val task = TaskObj()
        task.setDescription(description)
        task.setUser(user)
        task.saveInBackground { exception ->
            if (exception != null) {
                Log.e(MainActivity.TAG, "Error while saving task")
                exception.printStackTrace()
                Toast.makeText(this, "Error while submitting task", Toast.LENGTH_SHORT).show()
            } else {
                Log.i(MainActivity.TAG, "Successfully saved task")
            }
        }
    }

    companion object {
        const val TAG = "MainActivity"
    }

}