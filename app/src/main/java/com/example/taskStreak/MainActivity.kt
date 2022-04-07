package com.example.taskStreak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery
import com.parse.ParseUser
import com.parse.boltsinternal.Task

class MainActivity : AppCompatActivity() {

    lateinit var adapter: TaskAdapter
    lateinit var feed: RecyclerView
    lateinit var btnAdd: Button
    lateinit var etAddTask: EditText
    lateinit var tvStreak: TextView
    lateinit var btnLogOut: Button
    var tasks: ArrayList<TaskObj> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupStreak()
        setupLogOut()
        setupRV()
        setupAdd()
        queryTasks()
    }

    override fun onResume() {
        super.onResume()
        if (feed.itemAnimator?.isRunning == true) {
            setupStreak()
        }
    }

    fun setupStreak() {
        tvStreak = findViewById(R.id.main_tvStreak)
        val query: ParseQuery<TaskObj> = ParseQuery.getQuery(TaskObj::class.java)
        query.include(TaskObj.KEY_USER)
        query.whereEqualTo(TaskObj.KEY_USER, ParseUser.getCurrentUser())
        query.whereEqualTo(TaskObj.KEY_COMPLETED, true)
        query.findInBackground(object: FindCallback<TaskObj> {
            override fun done(foundTasks: MutableList<TaskObj>?, e: ParseException?) {
                if (e != null) {
                    Log.e(TAG, "Error fetching streak")
                    e.printStackTrace()
                } else {
                    if (foundTasks != null) {
                        val completed = foundTasks.size
                        tvStreak.setText("Streak: $completed")
                    }
                }
            }

        })
    }

    fun setupLogOut() {
        btnLogOut = findViewById(R.id.main_btnLogOut)
        btnLogOut.setOnClickListener {
            ParseUser.logOut()
            val intent = Intent(this@MainActivity, StartActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun setupRV() {
        feed = findViewById(R.id.main_rvTaskFeed)
        adapter =  TaskAdapter(tasks)
        feed.adapter = adapter
        feed.layoutManager = LinearLayoutManager(this@MainActivity)
    }

    fun setupAdd() {
        btnAdd = findViewById(R.id.main_btnAdd)
        etAddTask = findViewById(R.id.main_etAddTask)
        btnAdd.setOnClickListener {
            val userInput = etAddTask.text.toString()
            val user = ParseUser.getCurrentUser()
            createTask(userInput, user)
            etAddTask.setText("")
            queryTasks()
        }
    }

    fun createTask(description: String, user: ParseUser) {
        val task = TaskObj()
        task.setDescription(description)
        task.setUser(user)
        task.setCompleted(false)
        task.saveInBackground { exception ->
            if (exception != null) {
                Log.e(TAG, "Error while saving task")
                exception.printStackTrace()
                Toast.makeText(this, "Error while submitting task", Toast.LENGTH_SHORT).show()
            } else {
                Log.i(TAG, "Successfully saved task")
            }
        }
    }

    fun queryTasks() {
        val query: ParseQuery<TaskObj> = ParseQuery.getQuery(TaskObj::class.java)
        query.include(TaskObj.KEY_USER)
        query.whereEqualTo(TaskObj.KEY_USER, ParseUser.getCurrentUser())
        query.whereEqualTo(TaskObj.KEY_COMPLETED, false)
        query.addDescendingOrder("created_at")
        query.findInBackground(object: FindCallback<TaskObj> {
            override fun done(foundTasks: MutableList<TaskObj>?, e: ParseException?) {
                if (e != null) {
                    Log.e(TAG, "Error fetching tasks")
                    e.printStackTrace()
                } else {
                    if (foundTasks != null) {
                        for (task in foundTasks) {
                            Log.i(TAG, "Task: " + task.getDescription() +
                                    ", username: " + task.getUser()?.username)
                        }

                        // update dataset and adapter
                        adapter.clear()
                        tasks.addAll(foundTasks)
                        adapter.notifyDataSetChanged()
                    }
                }
            }

        })
    }

    companion object {
        const val TAG = "MainActivity"
    }

}