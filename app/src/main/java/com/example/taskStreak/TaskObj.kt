package com.example.taskStreak

import com.parse.ParseClassName
import com.parse.ParseObject
import com.parse.ParseUser

@ParseClassName("Task")
class TaskObj : ParseObject() {

    fun getDescription(): String? {
        return getString(KEY_DESCRIPTION)
    }

    fun setDescription(description: String) {
        put (KEY_DESCRIPTION, description)
    }

    fun getUser(): ParseUser? {
        return getParseUser(KEY_USER)
    }

    fun setUser(user: ParseUser) {
        put (KEY_USER, user)
    }

    fun getCompleted(): Boolean {
        return getBoolean(KEY_DESCRIPTION)
    }

    fun setCompleted(completed: Boolean) {
        put (KEY_COMPLETED, completed)
    }

    companion object {
        const val KEY_DESCRIPTION = "description"
        const val KEY_USER = "user"
        const val KEY_COMPLETED = "completed"
    }

}