package com.example.ustozshogird.model.`interface`

import com.example.ustozshogird.model.data.DoneTask
import com.example.ustozshogird.model.data.Task
import com.example.ustozshogird.model.data.User
import kotlinx.coroutines.flow.Flow

interface RealtimeDBRepositoryInterface {
    suspend fun signUp(user: User): Flow<Boolean>

    suspend fun checkUser(user: User):Flow<Boolean>

    suspend fun getTasks(id: String): Flow<List<Task>>

    suspend fun getDoneTasks(id: String): Flow<List<DoneTask>>

    suspend fun doneTask(task: DoneTask): Flow<Boolean>
}