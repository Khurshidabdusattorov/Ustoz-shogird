package com.example.ustozshogird.model.`interface`

import com.example.ustozshogird.model.data.DoneTask
import com.example.ustozshogird.model.data.Task
import com.example.ustozshogird.model.data.User
import kotlinx.coroutines.flow.Flow

interface RealtimeDBRepositoryInterface {
    fun signUp(user: User): Flow<Boolean>

    fun checkUser(user: User):Flow<Boolean>

    fun getTasks(id: String): Flow<List<Task>>

    fun getDoneTasks(id: String): Flow<List<DoneTask>>

    fun doneTask(task: DoneTask): Flow<Boolean>
}