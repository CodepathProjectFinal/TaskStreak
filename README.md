# Task Streak App

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description

An app that helps the user organize and remind them of their tasks. 

### App Evaluation
[Evaluation of your app across the following attributes]
- **Category:** Productivity
- **Mobile:** notification? sound?
- **Story:** Allows users to manage their tasks. The app displays a daily streak for task completion, allowing users a sense of achievement
- **Market:** Anyone that has an abundance of daily tasks will find this app useful.
- **Habit:** Users can open open the app daily to add and complete tasks. Users are incentivized to keep completing tasks every day to maintain their streak.
- **Scope:** This app has a relatively narrow scope, focusing on task management and completion. 

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**

- [x] User can log in and sign up
- [ ] User can view a list of tasks
- [ ] User can add and delete tasks
- [ ] User can mark a task as completed
- [ ] Users can view a daily streak for task completion

**Optional Nice-to-have Stories**

- [ ] Tasks can be viewed in a calendar format
- [ ] Audio effects are played (ie: congratulatory sound effect when user completes a tasks or sets a new streak record) during certain events


### 2. Screen Archetypes

* Start Screen
   * User can navigate to login and signup
* Login Screen
   * User can login
* Signup Screen
    * User can sign up
* Home Screen
    * User can view a list of tasks
    * User can view their streak of task completion
    * User can navigate to task creation
    * User can tap the logo to view the sidebar
* Creation Screen
    * User can create a new task

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Current Tasks
* Completed Tasks
* Stats
* About

**Flow Navigation** (Screen to Screen)

* Start Screen
    => Login
    => Signup
* Login Screen
    => Home
* Signup Screen
    => Home
* Home Screen
    => Creation
* Creation Screen
    => Home (after creating a task)

## Wireframes

<img src="https://i.imgur.com/kWDn2oI.png" width=350>
<img src="https://i.imgur.com/DHPCZM2.png" width=350>
<img src="https://i.imgur.com/eIzy7ZR.png" width=350>
<img src="https://i.imgur.com/Wcf2YG9.png" width=450>
<img src="https://i.imgur.com/qf9L48A.png" width=350>
<img src="https://i.imgur.com/9zQ6qYp.png" width=350>


## Schema 
### Models
User
   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | objectId      | String   | unique id for the user (default field) |
   | username      | String   | username |
   | password      | String   | password |
   | tasks         | Array    | array of pointers to task objects |
   | createdAt     | DateTime | date when user is created (default field) |
   | updatedAt     | DateTime | date when user is last updated (default field) |
   
Task
   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | objectId      | String   | unique id for the task (default field) |
   | user          | Pointer  | pointer to user who created task |
   | description   | String   | description for the task |
   | completed     | Boolean  | task has been completed or not |
   | createdAt     | DateTime | date when user is created (default field) |
   | updatedAt     | DateTime | date when user is last updated (default field) |
### Networking
#### List of network requests by screen
- Home Screen
        - (Read/GET) Query all user tasks
        ```
        val query: ParseQuery<Task> = ParseQuery.getQuery(Task::class.java)
        query.include(Task.KEY_USER)
        query.whereEqualTo(Task.KEY_USER, ParseUser.getCurrentUser()) 
        query.findInBackground(object: FindCallback<Task>) {
            override fun done(tasks: MutableList<Task>?, e: ParseException?) {...}
        }
        ```
- Creation Screen
        - (Create/POST) Create a new task object
        ```
        val task = Task()
        task.setDescription(description)
        task.setUser(user)
        task.setCompleted(false)
        task.saveInBackground {...}
        ```
